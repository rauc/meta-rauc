include rauc-hawkbit-updater.inc

SRC_URI = "git://github.com/rauc/rauc-hawkbit-updater.git;protocol=https;branch=master"
SRCREV = "c59cd13c2f7a8d6c1c1827f4282d11d0a9b8e465"
S = "${WORKDIR}/git"
PV = "1.0+git${SRCPV}"

DEFAULT_PREFERENCE = "-1"
