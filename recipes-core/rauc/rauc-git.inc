require rauc.inc

PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "059066ebcdb59524d1cd0fdd46862121a08426ef"

DEFAULT_PREFERENCE = "-1"
