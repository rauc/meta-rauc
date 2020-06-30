DESCRIPTION = "RAUC update controller for host and target"
HOMEPAGE = "https://github.com/rauc/rauc"
LICENSE = "LGPLv2.1"
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
PACKAGECONFIG[network] = "--enable-network,--enable-network=no,curl"
PACKAGECONFIG[json]    = "--enable-json,--enable-json=no,json-glib"

PACKAGES =+ "${PN}-service"
SYSTEMD_SERVICE_${PN}-service = "rauc.service"
SYSTEMD_PACKAGES = "${PN}-service"

FILES_${PN}-service = "\
  ${systemd_unitdir}/system/rauc.service \
  ${datadir}/dbus-1/system.d/de.pengutronix.rauc.conf \
  ${datadir}/dbus-1/system-services/de.pengutronix.rauc.service \
  "

FILES_${PN}-dev += "\
  ${datadir}/dbus-1/interfaces/de.pengutronix.rauc.Installer.xml \
  "
