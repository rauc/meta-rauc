PR = "r0"

SRC_URI = " \ 
  git://github.com/rauc/rauc.git;protocol=https;branch=master \
  "

PV = "1.6+git${SRCPV}"
S = "${WORKDIR}/git"

SRCREV = "3963103bac7dc872c5ee42a2924a39474278af39"

DEFAULT_PREFERENCE = "-1"

PACKAGECONFIG[streaming] = "--enable-streaming,--enable-streaming=no,libnl"

PACKAGECONFIG_class-target ??= "service network streaming json nocreate gpt"
