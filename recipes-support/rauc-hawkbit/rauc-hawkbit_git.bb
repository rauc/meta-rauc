LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SUMMARY = "hawkBit client for RAUC"

DEPENDS = "python3-setuptools-scm-native"

SRC_URI = " \
    git://github.com/rauc/rauc-hawkbit.git;protocol=https \
    file://rauc-hawkbit.service \
"

PV = "0.1.0-9+git${SRCPV}"
SRCREV = "6a1e79b0b7c83da866e0602201db49d6e74d7dd2"

S = "${WORKDIR}/git"

inherit setuptools3 systemd

PACKAGES =+ "${PN}-service"
SYSTEMD_SERVICE_${PN}-service = "rauc-hawkbit.service"
SYSTEMD_PACKAGES = "${PN}-service"

do_install_append() {
	install -d ${D}${sysconfdir}/${BPN}/
	install -m 0644 ${S}/rauc_hawkbit/config.cfg ${D}${sysconfdir}/${BPN}/config.cfg
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/rauc-hawkbit.service ${D}${systemd_unitdir}/system/
	sed -i -e 's!@BINDIR@!${bindir}!g' -e 's!@SYSCONFDIR@!${sysconfdir}!g' \
		${D}${systemd_unitdir}/system/rauc-hawkbit.service
}

RDEPENDS_${PN} += "python3-aiohttp python3-gbulb"
FILES_${PN}-service = " \
    ${bindir}/rauc-hawkbit-client \
    ${sysconfdir}/${BPN}/config.cfg \
    ${systemd_unitdir}/system/rauc-hawkbit.service \
"
