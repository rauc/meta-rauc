inherit native deploy

DEPENDS = "openssl-native glib-2.0-native"
RRECOMMENDS_${PN} = "squashfs-tools-native"

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

do_deploy() {
    install -d ${DEPLOY_DIR_TOOLS}
    install -m 0755 ${B}/rauc ${DEPLOY_DIR_TOOLS}/rauc-${PV}
    ln -sf rauc-${PV} ${DEPLOY_DIR_TOOLS}/rauc
}

addtask deploy before do_package after do_install
