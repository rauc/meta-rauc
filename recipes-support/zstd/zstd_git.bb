SUMMARY = "Zstandard - Fast real-time compression algorithm"
HOMEPAGE = "http://www.zstd.net"
LICENSE = "BSD & GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c7f0b161edbe52f5f345a3d1311d0b32 \
                    file://contrib/linux-kernel/COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"
SRCREV = "f3a8bd553a865c59f1bd6e1f68bf182cf75a8f00"
PV = "1.33+git${SRCPV}"

SRC_URI = "git://github.com/facebook/zstd.git;protocol=https;nobranch=1"

S = "${WORKDIR}/git"

EXTRA_OECMAKE_append = " -DTHREADS_PTHREAD_ARG=0"

do_install() {
    oe_runmake DESTDIR=${D} install
}

BBCLASSEXTEND = "native nativesdk"
