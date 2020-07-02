SUMMARY = "The RAUC hawkBit updater operates as an interface between the RAUC D-Bus API and the hawkBit DDI API."
HOMEPAGE = "https://github.com/rauc/rauc-hawkbit-updater"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1a6d268fd218675ffea8be556788b780"

SRC_URI = "git://github.com/rauc/rauc-hawkbit-updater.git;protocol=https"
SRCREV = "d909982e9e4b84cb76b98bf85f25a0a88472301a"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

inherit cmake pkgconfig systemd useradd

SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'rauc-hawkbit-updater.service', '', d)}"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"
PACKAGECONFIG[systemd] = "-DWITH_SYSTEMD=ON,,systemd"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home-dir / --no-create-home --shell /bin/false rauc-hawkbit"

DEPENDS = "curl glib-2.0-native json-glib"

do_install_append () {
	install -d ${D}${sysconfdir}/${PN}
	install -m 644 ${S}/config.conf.example ${D}${sysconfdir}/${PN}/config.conf
}
