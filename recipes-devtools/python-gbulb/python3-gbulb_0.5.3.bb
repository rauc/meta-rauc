SUMMARY = "GLib event loop for tulip (PEP 3156)"
HOMEPAGE = "http://github.com/nathan-hoad/gbulb"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=ceb5c94bee9ee3df2a39952beeb0bc83"

SRC_URI[md5sum] = "424fea3695a8ff6bd39a8cbadbde008b"
SRC_URI[sha256sum] = "00e92fda2ed4ee8c1a48b423f4f7a387dbc288d4bc36f5a8d0c8ac2759c47ea5"

PYPI_PACKAGE = "gbulb"

# python3-misc is needed for the signal module
RDEPENDS_${PN} = "python3-pygobject python3-asyncio python3-misc"

inherit pypi setuptools3
