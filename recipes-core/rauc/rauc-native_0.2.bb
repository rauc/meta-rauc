require rauc-0.2.inc

inherit native deploy

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0755 ${B}/rauc ${DEPLOYDIR}/rauc-${PV}
    ln -sf rauc-${PV} ${DEPLOYDIR}/rauc
}

addtask deploy before do_package after do_install
