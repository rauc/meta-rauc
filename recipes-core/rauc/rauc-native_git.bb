require rauc-git.inc

inherit native deploy

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

do_deploy() {
    install -d ${DEPLOY_DIR_TOOLS}
    install -m 0755 ${B}/rauc ${DEPLOY_DIR_TOOLS}/rauc-${PV}
    ln -sf rauc-${PV} ${DEPLOY_DIR_TOOLS}/rauc
}

addtask deploy before do_package after do_install
