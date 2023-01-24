do_install:append() {
	rm -rf ${D}${nonarch_base_libdir}
	rm -rf ${D}${datadir}/dbus-1
}

inherit nativesdk
