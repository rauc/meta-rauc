RAUC_KEYRING_FILE ??= "ca.cert.pem"
RAUC_KEYRING_URI ??= "file://${RAUC_KEYRING_FILE}"

RRECOMMENDS:${PN} += "virtual-rauc-conf"
RRECOMMENDS:${PN} += "squashfs-tools"

SRC_URI:append = " \
  file://rauc-mark-good.service \
  file://rauc-mark-good.init \
  "

SYSTEMD_PACKAGES += "${PN}-mark-good"
SYSTEMD_SERVICE:${PN}-mark-good = "rauc-mark-good.service"

INITSCRIPT_PACKAGES = "${PN}-mark-good"
INITSCRIPT_NAME:${PN}-mark-good = "rauc-mark-good"
INITSCRIPT_PARAMS:${PN}-mark-good = "start 99 5 2 . stop 20 0 1 6 ."

inherit systemd update-rc.d

do_install () {
	meson_do_install

        install -d ${D}${systemd_unitdir}/system/
        install -m 0644 ${WORKDIR}/rauc-mark-good.service ${D}${systemd_unitdir}/system/
        sed -i -e 's!@BINDIR@!${bindir}!g' ${D}${systemd_unitdir}/system/*.service

        install -d "${D}${sysconfdir}/init.d"
        install -m 755 "${WORKDIR}/rauc-mark-good.init" "${D}${sysconfdir}/init.d/rauc-mark-good"
}

PACKAGES =+ "${PN}-mark-good"

# some magic to get the tool to interact with bootloader storage
python __anonymous () {
    bootloader = d.getVar("PREFERRED_PROVIDER_virtual/bootloader")

    if not bootloader:
        return

    if "barebox" in bootloader:
        boothelper = " dt-utils-barebox-state"
    elif "u-boot" in bootloader:
        boothelper = " u-boot-fw-utils"
    elif "grub" in bootloader:
        boothelper = " grub-editenv"
    else:
        return

    d.appendVar("RDEPENDS:%s" % d.getVar('PN'), boothelper)
}

RRECOMMENDS:${PN}:append = " ${PN}-mark-good"

FILES:${PN}-mark-good = "${systemd_unitdir}/system/rauc-mark-good.service ${sysconfdir}/init.d/rauc-mark-good"

PACKAGES =+ "${PN}-service"
SYSTEMD_SERVICE:${PN}-service = "${@bb.utils.contains('PACKAGECONFIG', 'service', 'rauc.service', '', d)}"
SYSTEMD_PACKAGES += "${PN}-service"
RDEPENDS:${PN}-service += "dbus"

FILES:${PN}-service = "\
  ${systemd_unitdir}/system/rauc.service \
  ${datadir}/dbus-1/system.d/de.pengutronix.rauc.conf \
  ${datadir}/dbus-1/system-services/de.pengutronix.rauc.service \
  ${nonarch_libdir}/systemd/catalog \
  "

PACKAGECONFIG ??= "service network streaming json nocreate gpt"
