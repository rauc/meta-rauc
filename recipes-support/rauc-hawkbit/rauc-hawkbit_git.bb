LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SUMMARY = "hawkBit client for RAUC"

DEPENDS = "python3-setuptools-scm-native"

SRC_URI = "git://github.com/rauc/rauc-hawkbit.git;protocol=https"

PV = "0.2.0+git${SRCPV}"
SRCREV = "47bebb4a011768817f13f7796ec1b4e67edaffc5"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-aiohttp python3-gbulb"
