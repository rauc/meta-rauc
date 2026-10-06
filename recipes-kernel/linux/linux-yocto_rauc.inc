FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI += "file://rauc.scc"

KERNEL_FEATURES += "rauc.scc"
