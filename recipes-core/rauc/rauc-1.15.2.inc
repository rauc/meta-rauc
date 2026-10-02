SRC_URI = "\
    https://github.com/rauc/rauc/releases/download/v${PV}/rauc-${PV}.tar.xz \
    file://0001-signature-use-ASN1_STRING_get0_data-and-ASN1_STRING_.patch \
    "

SRC_URI[sha256sum] = "127a24cde208c65b837ae978c695a00730f1094ee8b6c7d48cf58ef846eae340"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"
