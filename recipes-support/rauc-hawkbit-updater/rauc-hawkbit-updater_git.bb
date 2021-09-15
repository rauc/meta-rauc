include rauc-hawkbit-updater.inc

SRC_URI = "git://github.com/rauc/rauc-hawkbit-updater.git;protocol=https"
SRCREV = "28900b59859d80d2e5275ad03278b0b76e7f1b51"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

