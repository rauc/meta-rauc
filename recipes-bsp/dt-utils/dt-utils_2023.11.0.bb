DESCRIPTION = "device-tree and barebox-related tools"
HOMEPAGE = "http://git.pengutronix.de/?p=tools/dt-utils.git"
SECTION = "base"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=18d902a0242c37a4604224b47d02f802"
DEPENDS = "udev"

SRC_URI = "http://www.pengutronix.de/software/dt-utils/download/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "d224d941c076c143f43d59cd7c6e1c522926064a31ac34a67720632ddecb6b53"

inherit autotools pkgconfig gettext

PACKAGES =+ "${PN}-barebox-state ${PN}-fdtdump ${PN}-dtblint"

FILES:${PN}-barebox-state = "${bindir}/barebox-state"
FILES:${PN}-fdtdump = "${bindir}/fdtdump"
FILES:${PN}-dtblint = "${bindir}/dtblint"
