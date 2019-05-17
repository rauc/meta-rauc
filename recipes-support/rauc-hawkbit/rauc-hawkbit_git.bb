LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SUMMARY = "hawkBit client for RAUC"

DEPENDS = "python3-setuptools-scm-native"

SRC_URI = "git://github.com/rauc/rauc-hawkbit.git;protocol=https"

PV = "0.1.0-9+git${SRCPV}"
SRCREV = "6a1e79b0b7c83da866e0602201db49d6e74d7dd2"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-aiohttp python3-gbulb"
