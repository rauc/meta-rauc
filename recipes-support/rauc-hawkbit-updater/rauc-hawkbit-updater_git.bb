include rauc-hawkbit-updater.inc

SRC_URI = "git://github.com/rauc/rauc-hawkbit-updater.git;protocol=https;branch=master"
SRCREV = "e19bc259a615ea31584c4056f365db46237d70f9"
S = "${WORKDIR}/git"
PV = "1.3+git${SRCPV}"

DEFAULT_PREFERENCE = "-1"
