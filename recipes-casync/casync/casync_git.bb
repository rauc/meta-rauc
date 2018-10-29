SUMMARY = "Content-Addressable Data Synchronization Tool"
HOMEPAGE = "https://github.com/systemd/casync"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "xz curl openssl acl fuse zstd"
DEPENDS_append_class-target = " udev"

SRCREV = "a755da21d3ba5d9cbb002dfc86a3ab0d46b82176"
PV = "2+git${SRCPV}"

SRC_URI = " \
    git://github.com/systemd/casync.git;protocol=https \
    "

S = "${WORKDIR}/git"

inherit meson

EXTRA_OEMESON += "-Dselinux=false -Dman=false"
EXTRA_OEMESON_append_class-native = " -Dudev=false"

BBCLASSEXTEND = "native"
