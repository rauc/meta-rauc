require rauc.inc

PR = "r9"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "0+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "3867722b8d64c43765e2d04f346dd612c02ad137"
