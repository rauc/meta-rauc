LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=4fbd65380cdd255951079008b364516c \
    file://README.rst;beginline=114;endline=132;md5=aff2a45fabc5c8d959b72f97ffc77465 \
    "

SUMMARY = "hawkBit client for RAUC"

DEPENDS = "python3-setuptools-scm-native"

SRC_URI = " \
    git://github.com/rauc/rauc-hawkbit.git;protocol=https;branch=master \
    file://rauc-hawkbit.service \
"

PV = "0.2.0+git${SRCPV}"
SRCREV = "47bebb4a011768817f13f7796ec1b4e67edaffc5"

S = "${WORKDIR}/git"

inherit setuptools3 systemd

PACKAGES =+ "${PN}-service"
SYSTEMD_SERVICE:${PN}-service = "rauc-hawkbit.service"
SYSTEMD_PACKAGES = "${PN}-service"

do_install:append() {
	install -d ${D}${sysconfdir}/${BPN}/
	install -m 0644 ${S}/rauc_hawkbit/config.cfg ${D}${sysconfdir}/${BPN}/config.cfg
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/rauc-hawkbit.service ${D}${systemd_unitdir}/system/
	sed -i -e 's!@BINDIR@!${bindir}!g' -e 's!@SYSCONFDIR@!${sysconfdir}!g' \
		${D}${systemd_unitdir}/system/rauc-hawkbit.service
}

RDEPENDS:${PN} += "python3-aiohttp python3-gbulb"
FILES:${PN}-service = " \
    ${bindir}/rauc-hawkbit-client \
    ${sysconfdir}/${BPN}/config.cfg \
    ${systemd_unitdir}/system/rauc-hawkbit.service \
"
