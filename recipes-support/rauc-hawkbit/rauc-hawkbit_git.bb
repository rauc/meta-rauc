LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SUMMARY = "hawkBit client for RAUC"

SRC_URI = "git://github.com/rauc/rauc-hawkbit.git;protocol=https"

PV = "0.1.0+git${SRCPV}"
SRCREV = "79421c1c0b1480da70c426f782585fa66416eed0"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-aiohttp python3-gbulb"
