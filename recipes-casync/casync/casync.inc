SUMMARY = "Content-Addressable Data Synchronization Tool"
HOMEPAGE = "https://github.com/systemd/casync"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "xz curl openssl acl zstd"

SRCREV = "4ad9bcb94bc83ff36cfc65515107ea06a88c2dfc"
PV = "2+git${SRCPV}"

SRC_URI = " \
    git://github.com/systemd/casync.git;protocol=https \
    "

S = "${WORKDIR}/git"

inherit meson

EXTRA_OEMESON += "-Dselinux=false -Dman=false -Dudevrulesdir=${nonarch_base_libdir}/udev/rules.d/"

BBCLASSEXTEND = "native nativesdk"

PACKAGECONFIG_class-native = ""
PACKAGECONFIG_class-nativesdk = ""
PACKAGECONFIG ?= "fuse udev"

PACKAGECONFIG[fuse] = "-Dfuse=true,-Dfuse=false,fuse"
PACKAGECONFIG[udev] = "-Dudev=true,-Dudev=false,udev"

FILES_${PN} += "${datadir}/bash-completion"
