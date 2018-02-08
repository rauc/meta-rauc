require rauc.inc

PR = "r1"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "0.3+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "419387685f1e5fb0d59aa21d0eb47ddae5120600"
