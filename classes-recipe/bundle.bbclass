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
    copy_hooks_and_extras(bundledir, d)
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
