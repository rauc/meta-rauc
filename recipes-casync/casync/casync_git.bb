SUMMARY = "Content-Addressable Data Synchronization Tool"
HOMEPAGE = "https://github.com/systemd/casync"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "xz curl openssl acl zstd"

SRCREV = "0efa7abffe5fffbde8c457d3c8fafbdde0bb6e4f"
PV = "2+git${SRCPV}"

SRC_URI = " \
    git://github.com/systemd/casync.git;protocol=https;branch=main \
    "

S = "${WORKDIR}/git"

inherit meson pkgconfig

EXTRA_OEMESON += "-Dselinux=false -Dman=false -Dudevrulesdir=${nonarch_base_libdir}/udev/rules.d/"

BBCLASSEXTEND = "native nativesdk"

PACKAGECONFIG:class-native = ""
PACKAGECONFIG:class-nativesdk = ""
PACKAGECONFIG ?= "fuse udev"

PACKAGECONFIG[fuse] = "-Dfuse=true,-Dfuse=false,fuse"
PACKAGECONFIG[udev] = "-Dudev=true,-Dudev=false,udev"

FILES:${PN} += "${datadir}/bash-completion"
