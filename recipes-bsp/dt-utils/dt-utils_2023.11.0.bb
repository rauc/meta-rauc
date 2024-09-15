DESCRIPTION = "device-tree and barebox-related tools"
HOMEPAGE = "http://git.pengutronix.de/?p=tools/dt-utils.git"
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=18d902a0242c37a4604224b47d02f802"
DEPENDS = "udev"

SRC_URI = "http://www.pengutronix.de/software/dt-utils/download/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "d224d941c076c143f43d59cd7c6e1c522926064a31ac34a67720632ddecb6b53"

inherit autotools pkgconfig gettext

NOAUTOPACKAGEDEBUG = "1"

FILES:${PN}-dbg = "${libdir}/.debug/"

PACKAGES =+ "${PN}-barebox-state ${PN}-barebox-state-dbg"
FILES:${PN}-barebox-state = "${bindir}/barebox-state"
FILES:${PN}-barebox-state-dbg = "${bindir}/.debug/barebox-state"

PACKAGES =+ "${PN}-fdtdump ${PN}-fdtdump-dbg"
FILES:${PN}-fdtdump = "${bindir}/fdtdump"
FILES:${PN}-fdtdump-dbg = "${bindir}/.debug/fdtdump"

PACKAGES =+ "${PN}-dtblint ${PN}-dtblint-dbg"
FILES:${PN}-dtblint = "${bindir}/dtblint"
FILES:${PN}-dtblint-dbg = "${bindir}/.debug/dtblint"
