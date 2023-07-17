DESCRIPTION = "RAUC update controller for host and target"
HOMEPAGE = "https://github.com/rauc/rauc"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "openssl glib-2.0 glib-2.0-native"

inherit meson pkgconfig gettext

EXTRA_OEMESON += "\
        -Dtests=false \
        -Dsystemdunitdir=${systemd_system_unitdir} \
        -Ddbuspolicydir=${datadir}/dbus-1/system.d \
        -Ddbussystemservicedir=${datadir}/dbus-1/system-services \
        -Ddbusinterfacesdir=${datadir}/dbus-1/interfaces \
        "

PACKAGECONFIG[nocreate]  = "-Dcreate=false,-Dcreate=true,"
PACKAGECONFIG[service] = "-Dservice=true,-Dservice=false,dbus,${PN}-service"
PACKAGECONFIG[streaming] = "-Dstreaming=true,-Dstreaming=false,libnl"
PACKAGECONFIG[network] = "-Dnetwork=true,-Dnetwork=false,curl"
PACKAGECONFIG[json]    = "-Djson=enabled,-Djson=disabled,json-glib"
PACKAGECONFIG[gpt]     = "-Dgpt=enabled,-Dgpt=disabled,util-linux"

FILES:${PN}-dev += "\
  ${datadir}/dbus-1/interfaces/de.pengutronix.rauc.Installer.xml \
  "
