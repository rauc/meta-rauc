require rauc.inc

SRC_URI = "https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz"

SRC_URI[sha256sum] = "d4ea009ce702bcb083d942398ccfedec959c6bbb7adc0fd77ae9314ce11d9d91"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
