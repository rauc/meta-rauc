SUMMARY = "Timeout context manager for asyncio programs"
HOMEPAGE = "https://github.com/aio-libs/async_timeout/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

inherit pypi setuptools3

SRC_URI[md5sum] = "305c4fa529f2485c403d0dbe14390175"
SRC_URI[sha256sum] = "0c3c816a028d47f659d6ff5c745cb2acf1f966da1fe5c19c77a70282b25f4c5f"

DEPENDS = "python3-pytest-runner-native python3"
RDEPENDS_${PN} += "python3-typing"
