SUMMARY = "GLib event loop for tulip (PEP 3156)"
HOMEPAGE = "http://github.com/nathan-hoad/gbulb"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=c19ea442ddc4f12594f5e77bb9d3dcd4"

SRC_URI[md5sum] = "b1b51b8548f34eb5649ebd98faf24955"
SRC_URI[sha256sum] = "119bcf9961a976a8240fee667722b41a23603119b7a0037d03f95650de062fc2"

PYPI_PACKAGE = "gbulb"

# python3-misc is needed for the signal module
RDEPENDS_${PN} = "python3-pygobject python3-asyncio python3-misc"

inherit pypi setuptools3
