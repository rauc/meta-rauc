require rauc.inc

SRC_URI = "https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz"

SRC_URI[md5sum] = "83d86821473102682cd1ad2a4a9a7a4c"
SRC_URI[sha256sum] = "5dfbc46e808240c5014d318cfe64f0431307c37aa79cb2b013caa12daaf96d9d"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
