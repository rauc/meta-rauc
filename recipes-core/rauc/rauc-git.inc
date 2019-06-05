require rauc.inc

PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "1.1+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "a1142006f01291b29bd5159edead178cb3be8ae8"

DEFAULT_PREFERENCE = "-1"
