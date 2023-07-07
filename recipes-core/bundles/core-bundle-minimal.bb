# A minimal demo bundle
#
# Note: The created bundle will not contain RAUC itself yet!
# To add this, properly configure it for your specific system and add it to
# your image recipe you intend to build a bundle from:
#
# IMAGE_INSTALL:append = " rauc"
#
# Also note that you need to configure RAUC_KEY_FILE and RAUC_CERT_FILE to
# point to contain the full path to your key and cert.
# Depending on you requirements you can either set them via global
# configuration or from a bundle recipe bbappend.
#
# For testing purpose, you may use the scripts/openssl-ca.sh to create some.

inherit bundle

RAUC_BUNDLE_FORMAT = "verity"

RAUC_BUNDLE_COMPATIBLE ?= "Demo Board"

RAUC_BUNDLE_SLOTS ?= "rootfs"

RAUC_SLOT_rootfs ?= "core-image-minimal"
