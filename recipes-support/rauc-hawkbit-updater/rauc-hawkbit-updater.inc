SUMMARY = "The RAUC hawkBit updater operates as an interface between the RAUC D-Bus API and the hawkBit DDI API."
HOMEPAGE = "https://github.com/rauc/rauc-hawkbit-updater"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1a6d268fd218675ffea8be556788b780"

inherit meson pkgconfig systemd useradd

SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'rauc-hawkbit-updater.service', '', d)}"

PACKAGECONFIG ??= " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"
PACKAGECONFIG[systemd] = "-Dsystemd=enabled,-Dsystemd=disabled,systemd"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --home-dir / --no-create-home --shell /bin/false rauc-hawkbit"

DEPENDS = "curl glib-2.0-native json-glib"

do_install:append () {
	install -d ${D}${sysconfdir}/${PN}
	install -m 644 ${S}/config.conf.example ${D}${sysconfdir}/${PN}/config.conf
}

