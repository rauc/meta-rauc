require rauc.inc

PR = "r9"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "0.1.1+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "4b6453e8678a48aa793c41871e3bfcfdbafce881"
