require rauc.inc

PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https \
  "

PV = "0.4+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "f8b8bd2188d241c5ccdf71ad6b9e1186e56e6a38"

DEFAULT_PREFERENCE = "-1"
