require ${@bb.utils.contains('DISTRO_FEATURES', 'rauc', '${BPN}_rauc.inc', '', d)}
