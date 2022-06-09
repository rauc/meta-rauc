DESCRIPTION = "RAUC update controller for host and target"
HOMEPAGE = "https://github.com/rauc/rauc"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "openssl glib-2.0 glib-2.0-native"

inherit autotools pkgconfig gettext

EXTRA_OECONF += "\
        --with-systemdunitdir=${systemd_system_unitdir} \
        --with-dbuspolicydir=${datadir}/dbus-1/system.d \
        --with-dbussystemservicedir=${datadir}/dbus-1/system-services \
        "

PACKAGECONFIG[nocreate]  = "--disable-create,--enable-create,"
PACKAGECONFIG[service] = "--enable-service,--enable-service=no,dbus,${PN}-service"
PACKAGECONFIG[streaming] = "--enable-streaming,--enable-streaming=no,libnl"
PACKAGECONFIG[network] = "--enable-network,--enable-network=no,curl"
PACKAGECONFIG[json]    = "--enable-json,--enable-json=no,json-glib"
PACKAGECONFIG[gpt]     = "--enable-gpt,--enable-gpt=no,util-linux"

RDEPENDS:${PN}-service += "dbus"

PACKAGES =+ "${PN}-service"
SYSTEMD_SERVICE:${PN}-service = "rauc.service"
SYSTEMD_PACKAGES = "${PN}-service"

FILES:${PN}-service = "\
  ${systemd_unitdir}/system/rauc.service \
  ${datadir}/dbus-1/system.d/de.pengutronix.rauc.conf \
  ${datadir}/dbus-1/system-services/de.pengutronix.rauc.service \
  "

FILES:${PN}-dev += "\
  ${datadir}/dbus-1/interfaces/de.pengutronix.rauc.Installer.xml \
  "
