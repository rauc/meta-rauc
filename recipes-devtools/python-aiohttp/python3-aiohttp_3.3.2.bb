SUMMARY = "HTTP client/server for asyncio"
HOMEPAGE = "http://aiohttp.readthedocs.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=c76b717025e9f23e50092cd39a213d56"

SRC_URI[md5sum] = "dfaadf259d1f677a286d203e9470d2a0"
SRC_URI[sha256sum] = "f20deec7a3fbaec7b5eb7ad99878427ad2ee4cc16a46732b705e8121cbb3cc12"

# make clean calls python instead of python3, which makes setup.py fail
CLEANBROKEN = "1"

PYPI_PACKAGE = "aiohttp"

# python3-misc is needed for the operator module
# python3-netserver is needed for the cgi module
RDEPENDS_${PN} = "python3-asyncio python3-async-timeout python3-chardet python3-json python3-multiprocessing python3-misc python3-netserver python3-multidict python3-yarl python3-attrs python3-idna-ssl"

inherit pypi setuptools3

do_install_append() {
    rm ${D}${libdir}/python3.*/site-packages/aiohttp/__pycache__/*
    rmdir ${D}${libdir}/python3.*/site-packages/aiohttp/__pycache__
    rm ${D}${libdir}/python3.*/site-packages/aiohttp/*.c
}

PACKAGES =+ "${PN}-extensions"

FILES_${PN}-extensions = "\
    ${libdir}/python3.*/site-packages/aiohttp/*.pyx \
    ${libdir}/python3.*/site-packages/aiohttp/*.so \
"
