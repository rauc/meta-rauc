require dt-utils.inc

SRC_URI[md5sum] = "3ab2d646639bb10607716a5b57e2ddf8"
SRC_URI[sha256sum] = "fbb3393a76a63a0769135719ae3865e885efbbf4a44ba1d5ec7ebbadbcb3327a"

SRC_URI += "file://src-fix-compilation-for-glibc-version-2.27.9000-36.f.patch"
