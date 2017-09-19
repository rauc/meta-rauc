require rauc-git.inc

inherit native deploy

RAUC_TOOL_BASENAME = "rauc-${PV}-${DATETIME}"
RAUC_TOOL_BASENAME[vardepsexclude] = "DATETIME"

do_deploy[sstate-outputdirs] = "${DEPLOY_DIR_TOOLS}"

do_deploy() {
    install -d ${DEPLOY_DIR_TOOLS}
    install -m 0755 ${B}/rauc ${DEPLOY_DIR_TOOLS}/${RAUC_TOOL_BASENAME}
    ln -sf ${RAUC_TOOL_BASENAME} ${DEPLOY_DIR_TOOLS}/rauc
}

addtask deploy before do_package after do_install
