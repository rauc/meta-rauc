SUMMARY = "GLib implementation of PEP 3156"
HOMEPAGE = "http://github.com/beeware/gbulb"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=edfe3d91d4b439ac8a8c23cebbf00501"

SRC_URI[sha256sum] = "da003c5b17d3a2ba15c7255bb174defaa0f6b77e8b23f229685eb2714ceaeeec"

PYPI_PACKAGE = "gbulb"

# python3-misc is needed for the signal module
RDEPENDS:${PN} = "python3-pygobject python3-asyncio python3-misc"

inherit pypi setuptools3
