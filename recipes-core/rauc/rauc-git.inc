SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.13+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "1183a396a2e10e92e428c396a510113edcd9037b"

RAUC_USE_DEVEL_VERSION[doc] = "Global switch to enable RAUC development (git) version."
RAUC_USE_DEVEL_VERSION ??= "-1"
DEFAULT_PREFERENCE ??= "${RAUC_USE_DEVEL_VERSION}"
