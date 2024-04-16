SUMMARY = "RAUC system configuration & verification keyring"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RAUC_KEYRING_FILE ??= "ca.cert.pem"
RAUC_KEYRING_URI ??= "file://${RAUC_KEYRING_FILE}"

RPROVIDES:${PN} += "virtual-rauc-conf"

INHIBIT_DEFAULT_DEPS = "1"
do_compile[noexec] = "1"

SRC_URI = " \
  file://system.conf \
  ${RAUC_KEYRING_URI} \
  "

do_install () {
        # Create rauc config dir
        # Warn if system configuration was not overwritten
        if ! grep -q "^[^#]" ${WORKDIR}/system.conf; then
                bbwarn "Please overwrite example system.conf with a project specific one!"
        fi
        install -d ${D}${sysconfdir}/rauc
        install -m 0644 ${WORKDIR}/system.conf ${D}${sysconfdir}/rauc/

        # Warn if CA file was not overwritten
        if ! grep -q "^[^#]" ${WORKDIR}/${RAUC_KEYRING_FILE}; then
                bbwarn "Please overwrite example ca.cert.pem with a project specific one, or set the RAUC_KEYRING_FILE variable with your file!"
        fi
        install -d ${D}${sysconfdir}/rauc
        install -m 0644 ${WORKDIR}/${RAUC_KEYRING_FILE} ${D}${sysconfdir}/rauc/
}
