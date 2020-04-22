require rauc.inc

SRC_URI = "https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz"

SRC_URI[md5sum] = "04ba029daa51e1f70fe53d62f2c5ebc3"
SRC_URI[sha256sum] = "4f6144447254753fcd19f2c5c70e79fa91d27253185b8928436f01528aa65e6c"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
