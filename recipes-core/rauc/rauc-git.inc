SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.15+git${SRCPV}"

SRCREV = "db1e1e59c60385fa884781b8ab34a77a02d9b3e5"

RAUC_USE_DEVEL_VERSION[doc] = "Global switch to enable RAUC development (git) version."
RAUC_USE_DEVEL_VERSION ??= "-1"
DEFAULT_PREFERENCE ??= "${RAUC_USE_DEVEL_VERSION}"
