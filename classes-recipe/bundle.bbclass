# Class for creating rauc bundles

LICENSE ?= "MIT"

inherit bundle-base

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit nopackages

PACKAGES = ""
INHIBIT_DEFAULT_DEPS = "1"

do_fetch[cleandirs] = "${S}"
do_patch[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
deltask do_populate_sysroot

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"
B = "${WORKDIR}/build"

inherit image-artifact-names

python do_configure() {
    import shutil
    import os
    import stat
    import subprocess

    bundledir = d.getVar("RAUC_BUNDLE_DIR")

    write_manifest(bundledir, d)

    hooks_varflags = d.getVar('RAUC_VARFLAGS_HOOKS').split()
    hooksflags = d.getVarFlags('RAUC_BUNDLE_HOOKS', expand=hooks_varflags) or {}
    if 'file' in hooksflags:
        hf = hooksflags.get('file')
        if not os.path.exists(d.expand("${UNPACKDIR}/%s" % hf)):
            bb.error("hook file '%s' does not exist in UNPACKDIR" % hf)
            return
        dsthook = d.expand("%s/%s" % (bundledir, hf))
        bb.note("adding hook file to bundle dir: '%s'" % hf)
        shutil.copy(d.expand("${UNPACKDIR}/%s" % hf), dsthook)
        st = os.stat(dsthook)
        os.chmod(dsthook, st.st_mode | stat.S_IEXEC)

    for file in (d.getVar('RAUC_BUNDLE_EXTRA_FILES') or "").split():
        destpath = d.expand("%s/%s") % (bundledir, file)

        searchpath = try_searchpath(file, d)
        if not searchpath:
            bb.error("extra file '%s' neither found in workdir nor in deploy dir!" % file)

        destdir = '.'
        # strip leading and trailing slashes to prevent installting into wrong location
        file = file.rstrip('/').lstrip('/')

        if file.find("/") != -1:
            destdir = file.rsplit("/", 1)[0] + '/'
            bb.utils.mkdirhier("%s/%s" % (bundledir, destdir))
        bb.note("Unpacking %s to %s/" % (file, bundledir))
        ret = subprocess.call('cp -fpPRH "%s" "%s"' % (searchpath, destdir), shell=True, cwd=bundledir)
}

addtask do_configure after do_prepare_recipe_sysroot before do_bundle

do_configure[cleandirs] = "${RAUC_BUNDLE_DIR}"

inherit deploy

SSTATE_SKIP_CREATION:task-deploy = '1'

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${B}/bundle.raucb ${DEPLOYDIR}/${BUNDLE_NAME}${BUNDLE_EXTENSION}
	ln -sf ${BUNDLE_NAME}${BUNDLE_EXTENSION} ${DEPLOYDIR}/${BUNDLE_LINK_NAME}${BUNDLE_EXTENSION}

	if [ ${RAUC_CASYNC_BUNDLE} -eq 1 ]; then
		install -m 0644 ${B}/casync-bundle.raucb ${DEPLOYDIR}/${CASYNC_BUNDLE_NAME}${CASYNC_BUNDLE_EXTENSION}
		cp -r ${B}/casync-bundle.castr ${DEPLOYDIR}/${CASYNC_BUNDLE_NAME}.castr
		ln -sf ${CASYNC_BUNDLE_NAME}${CASYNC_BUNDLE_EXTENSION} ${DEPLOYDIR}/${CASYNC_BUNDLE_LINK_NAME}${CASYNC_BUNDLE_EXTENSION}
		ln -sf ${CASYNC_BUNDLE_NAME}.castr ${DEPLOYDIR}/${CASYNC_BUNDLE_LINK_NAME}.castr
	fi
}

addtask deploy after do_bundle before do_build
