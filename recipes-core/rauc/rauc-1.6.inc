require rauc.inc

SRC_URI = "https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz"

SRC_URI[sha256sum] = "86013788dd02321b7c3f913ad3a1f8802afbc784ec076fa278524a9e1ef9e7b0"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
