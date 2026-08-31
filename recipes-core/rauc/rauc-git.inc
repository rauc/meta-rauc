SRC_URI = " \
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.15.1+git"

SRCREV = "1a412fe80badb9cf91f5374ea2ef791de4db55a9"

RAUC_USE_DEVEL_VERSION[doc] = "Global switch to enable RAUC development (git) version."
RAUC_USE_DEVEL_VERSION ??= "-1"
DEFAULT_PREFERENCE ??= "${RAUC_USE_DEVEL_VERSION}"
