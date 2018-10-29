SUMMARY = "Timeout context manager for asyncio programs"
HOMEPAGE = "https://github.com/aio-libs/async_timeout/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

inherit pypi setuptools3

SRC_URI[md5sum] = "05f59e88d72ba3da099f8785bf49e97c"
SRC_URI[sha256sum] = "983891535b1eca6ba82b9df671c8abff53c804fce3fa630058da5bbbda500340"

DEPENDS = "python3-pytest-runner-native python3"
