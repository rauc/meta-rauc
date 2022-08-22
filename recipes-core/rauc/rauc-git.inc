PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.7+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "b2ee22acd0480ce98a5ba8b231af064405ff4bee"

DEFAULT_PREFERENCE = "-1"
