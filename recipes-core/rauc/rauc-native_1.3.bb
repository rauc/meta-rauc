require rauc-1.3.inc

inherit native deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0755 ${B}/rauc ${DEPLOYDIR}/rauc-${PV}
    ln -sf rauc-${PV} ${DEPLOYDIR}/rauc
}

addtask deploy before do_package after do_install
