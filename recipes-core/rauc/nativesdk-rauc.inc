do_install:append() {
	rm -rf ${D}${datadir}
}

inherit nativesdk
