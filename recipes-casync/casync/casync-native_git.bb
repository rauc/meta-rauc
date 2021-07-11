require casync.inc

inherit native deploy

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

do_deploy() {
    install -d ${DEPLOY_DIR_TOOLS}
    install -m 0755 ${B}/casync ${DEPLOY_DIR_TOOLS}/casync
}

addtask deploy before do_package after do_install
