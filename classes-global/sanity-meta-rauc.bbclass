addhandler rauc_bbappend_distrocheck
rauc_bbappend_distrocheck[eventmask] = "bb.event.SanityCheck"
python rauc_bbappend_distrocheck() {
    skip_check = e.data.getVar('SKIP_META_RAUC_FEATURE_CHECK') == "1"
    if 'rauc' not in e.data.getVar('DISTRO_FEATURES').split() and not skip_check:
        bb.warn("You have included the meta-rauc layer, but \
'rauc' has not been enabled in your DISTRO_FEATURES. \
See the meta-rauc README.rst for details on enabling RAUC support for your \
platform.")
}

