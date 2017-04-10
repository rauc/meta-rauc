require rauc.inc

PR = "r9"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "0.1+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "33fe5ab4bc8c364b666b7332c2f3ec6fc095b7a2"
