DESCRIPTION = "device-tree and barebox-related tools"
HOMEPAGE = "http://git.pengutronix.de/?p=tools/dt-utils.git"
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=18d902a0242c37a4604224b47d02f802"
DEPENDS = "udev"

SRC_URI = "http://www.pengutronix.de/software/dt-utils/download/${BPN}-${PV}.tar.xz"

inherit autotools pkgconfig gettext

PACKAGES =+ "${PN}-barebox-state ${PN}-fdtdump ${PN}-dtblint"

FILES:${PN}-barebox-state = "${bindir}/barebox-state"
FILES:${PN}-fdtdump = "${bindir}/fdtdump"
FILES:${PN}-dtblint = "${bindir}/dtblint"
