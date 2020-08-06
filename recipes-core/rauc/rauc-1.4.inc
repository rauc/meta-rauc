require rauc.inc

SRC_URI = "https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz"

SRC_URI[md5sum] = "c66e8bfb82d9d3838a270abaafeea044"
SRC_URI[sha256sum] = "85aabf214cd93a37f7ad0b3aaad89eb94facf0f3ebf6e2edca945acbca9b0967"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
