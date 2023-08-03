PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.10.1+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "734bf8937ab154b5edf38bf82c18ca0d7ff1a631"

RAUC_USE_DEVEL_VERSION[doc] = "Global switch to enable RAUC development (git) version."
RAUC_USE_DEVEL_VERSION ??= "-1"
DEFAULT_PREFERENCE ??= "${RAUC_USE_DEVEL_VERSION}"
