The meta-rauc layer provides support for integrating the RAUC update tool
into your device.

Please see the corresponding sections below for more information.
For a detailed description on steps necessary to integrate RAUC into your
project, refer https://rauc.readthedocs.io/en/latest/integration.html.


Dependencies
============

This layer depends on::

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

For rauc-hawkbit client::

  URI: git://git.yoctoproject.org/meta-oe
  layers: meta-python
  branch: master

For fuse-support in casync (the default)::

  URI: git://git.yoctoproject.org/meta-oe
  layers: meta-filesystems
  branch: master

Patches
=======

Please submit patches via GitHub pull request on https://github.com/rauc/meta-rauc

Maintainer: Enrico Joerns <ejo@pengutronix.de>


I. Adding the rauc Layer to Your Build
======================================

In order to use this layer, you need to make the build system aware of
it.

Assuming the rauc layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the rauc layer to bblayers.conf, along with any
other layers needed. e.g.::

  BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-poky \
    /path/to/yocto/meta-yocto-bsp \
    /path/to/yocto/meta-rauc \
    "


II. Building RAUC
=================

If you only intend to build the RAUC host tool, you may simply run::

  bitbake rauc-native

This will place the rauc binary at tmp/deploy/tools/rauc.


III. Adding the RAUC Update Framework to Your Device
====================================================

To prepare your device for using RAUC as its update handler,
you have to follow at least the following steps:

1. Add the `rauc` package to your systems image recipe::

     IMAGE_INSTALL_append = " rauc"

2. Add a ``rauc_%.bbappend`` in your device-specific (BSP) layer
   that installs your RAUC system configuration file under
   ``/etc/rauc/system.conf``. For information on how to write the RAUC
   update file, please refer to the RAUC user documentation [1]::

     FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
     SRC_URI_append := " file://system.conf"

3. Create a bundle recipe for your device by adding a recipe
   that inherits the `bundle` class and adds all desired
   configuration::

     inherit bundle

     RAUC_BUNDLE_SLOTS = "rootfs"
     RAUC_SLOT_rootfs = "my-rootfs-recipe"

     RAUC_KEY_FILE = "path/to/development-1.key.pem"
     RAUC_CERT_FILE = "path/to/development-1.cert.pem"

   For information on how to generate and use the key and certificate files,
   please refer to the RAUC user documentation [1].

   For a more detailed explanation on the required and available variables,
   read the notes in the bundle.bbclass file.

4. Build a bundle and the rootfs for your device::

     bitbake my-bundle-recipe

IV. Building The RAUC hawkBit Clients
=====================================

This layer offers support for two clients that interface between RAUC and the
hawkBit deployment server:

* rauc-hawkbit (python implementation)
* rauc-hawkbit-updater (C implementation)

To use ``rauc-hawkbit`` as a standalone service add to your systems image
recipe::

    IMAGE_INSTALL_append = " rauc-hawkbit-service"

To use it as a python library in your demo application instead, simply add to
your recipe::

    DEPENDS += "rauc-hawkbit"

To use ``rauc-hawkbit-updater`` in your system add to your image recipe::

    IMAGE_INSTALL_append = " rauc-hawkbit-updater"

V. Configure Custom Kernel
==========================

In order to use RAUC on your system, the kernel must support SquashFS and loop
mounts. For the standard yocto kernel, the meta-rauc layer provides a kernel
configuration fragment that enables the config options required for this.

If you build your own kernel with a full custom ``defconfig`` file, you have to
make sure that the options in ``recipes-kernel/linux/linux-yocto/rauc.cfg`` are
enabled in your configuration, too.


VI. References
==============

[1] RAUC user documentation http://rauc.readthedocs.io/en/latest/
