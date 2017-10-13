SUMMARY = "Yet another URL library"
HOMEPAGE = "https://github.com/aio-libs/yarl/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22522b8f7ca5b78025a4fb540c948907"

inherit pypi setuptools3

SRC_URI[md5sum] = "a5a21f6ba05b62d34a32b3bbe50493bf"
SRC_URI[sha256sum] = "51b92ef78e322cb2c93839c404de5ae33e852cab665fc99652fa89d7ab16e761"

RDEPENDS_${PN} = "${PYTHON_PN}-multidict"
