PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.8+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "bfbf94caf4e3deb3fcef69c8c14a3ac673707153"

DEFAULT_PREFERENCE = "-1"
