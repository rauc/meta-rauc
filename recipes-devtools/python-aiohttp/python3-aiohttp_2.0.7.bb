SUMMARY = "HTTP client/server for asyncio"
HOMEPAGE = "http://aiohttp.readthedocs.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=60dd5a575c9bd4339411bdef4a702d46"

SRC_URI[md5sum] = "1ae6e69655389cbd0c81346492267314"
SRC_URI[sha256sum] = "76bfd47ee7fbda115cff486c3944fcb237ecbf6195bf2943fae74052fb40c4fe"

# make clean calls python instead of python3, which makes setup.py fail
CLEANBROKEN = "1"

PYPI_PACKAGE = "aiohttp"

# python3-misc is needed for the operator module
# python3-netserver is needed for the cgi module
RDEPENDS_${PN} = "python3-asyncio python3-async-timeout python3-chardet python3-json python3-multiprocessing python3-misc python3-netserver python3-multidict python3-yarl"

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
