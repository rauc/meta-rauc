|MIT| |gh_action| |Matrix|

The meta-rauc layer provides support for integrating the RAUC update tool
into your device.

Please see the corresponding sections below for more information.
For a detailed description on steps necessary to integrate RAUC into your
project, refer https://rauc.readthedocs.io/en/latest/integration.html.


Dependencies
============

This layer depends on::

  URI: https://github.com/openembedded/bitbake.git
  branch: master

  URI: https://github.com/openembedded/openembedded-core.git
  layers: meta
  branch: master

For rauc-hawkbit client::

  URI: https://github.com/openembedded/meta-openembedded.git
  layers: meta-python
  branch: master

For fuse-support in casync (the default)::

  URI: https://github.com/openembedded/meta-openembedded.git
  layers: meta-filesystems
  branch: master

Patches
=======

Please submit patches via GitHub pull request on https://github.com/rauc/meta-rauc

Maintainer: Enrico Joerns <ejo@pengutronix.de>

Migration Notes
===============

Since **scarthgap**, the platform configuration (system.conf, keyring, etc.) was
moved to a separate ``rauc-conf.bb`` recipe to allow building the rauc package
with ``TUNE_PKGARCH`` and have a clearer separation between the binary and the
configuration.

Thus when updating to scarthgap or newer, make sure to move all
configuration-specific adaptions from your ``rauc_%.bbappend`` to a
``rauc-conf.bbappend`` file.


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


II. Building and Using RAUC Host Tool
=====================================

If you intend to build and use RAUC as a host tool from your BSP, e.g. for
calling ``rauc info`` on your built bundle, simply run::

  bitbake rauc-native -caddto_recipe_sysroot
  oe-run-native rauc-native rauc info --keyring=/path/to/keyring.pem tmp/deploy/images/<machine>/<bundle-name>.raucb

If you need to execute the ``casync`` host tool manually, you can do this by running::

  bitbake casync-native -caddto_recipe_sysroot
  oe-run-native casync-native casync --help

III. Adding the RAUC Update Service to Your Device
==================================================

To prepare your device for using RAUC as its update handler,
you have to follow at least the following steps:

1. Add `rauc` to `DISTRO_FEATURES` in your distro (or local) config::

     DISTRO_FEATURES += "rauc"

2. Add a ``rauc-conf.bbappend`` in your device-specific (BSP) layer
   that installs your RAUC system configuration file under
   ``/etc/rauc/system.conf``. For information on how to write the RAUC
   update file, please refer to the RAUC user documentation [1]_::

     FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

3. Create a bundle recipe for your device by adding a recipe
   that inherits the `bundle` class and adds all desired
   configuration::

     inherit bundle

     RAUC_BUNDLE_SLOTS = "rootfs"
     RAUC_SLOT_rootfs = "my-rootfs-recipe"

     RAUC_KEY_FILE = "path/to/development-1.key.pem"
     RAUC_CERT_FILE = "path/to/development-1.cert.pem"

   For information on how to generate and use the key and certificate files,
   please refer to the RAUC user documentation [1]_.

   For a more detailed explanation on the required and available variables,
   read the notes in the bundle.bbclass file.

4. Build a bundle and the rootfs for your device::

     bitbake my-bundle-recipe

Note: If you do not use packagegroup-base, you als need to manually add
the `rauc` package to your systems image recipe::

     IMAGE_INSTALL:append = " rauc"


IV. Building The RAUC hawkBit Clients
=====================================

This layer offers support for two clients that interface between RAUC and the
hawkBit deployment server:

* rauc-hawkbit (python implementation)
* rauc-hawkbit-updater (C implementation)

To use ``rauc-hawkbit`` as a standalone service add to your systems image
recipe::

    IMAGE_INSTALL:append = " rauc-hawkbit-service"

To use it as a python library in your demo application instead, simply add to
your recipe::

    DEPENDS += "rauc-hawkbit"

To use ``rauc-hawkbit-updater`` in your system add to your image recipe::

    IMAGE_INSTALL:append = " rauc-hawkbit-updater"

V. Configure Custom Kernel
==========================

In order to use RAUC on your system, the kernel must support SquashFS and loop
mounts. For the standard yocto kernel, the meta-rauc layer provides a kernel
configuration fragment that enables the config options required for this.

If you build your own kernel with a full custom ``defconfig`` file, you have to
make sure that the options in ``recipes-kernel/linux/linux-yocto/rauc.cfg`` are
enabled in your configuration, too.

VI. Build RAUC Development Version
==================================

Beside the standard release version recipes, the _git variants of RAUC recipes
allow to build RAUC from a master branch revision that is newer than the latest
release.

This is especially useful for early testing and adaption to upcoming features
in RAUC.

By default, the _git recipes are disabled. To enable them, you can set::

  RAUC_USE_DEVEL_VERSION = "1"

in your local.conf. Note that this has the same effect as setting
``DEFAULT_PREFERENCE = "1"`` for each recipe (target/native/nativesdk)
individually.

VII. Contributing
=================

To report bugs, file a new `issue <https://github.com/rauc/meta-rauc/issues>`_
on GitHub.

For fixing bugs, bumping recipes or adding new features, open a `pull request
<https://github.com/rauc/meta-rauc/pulls>`_ on GitHub.

Add a ``Signed-off-by`` line to your commits according to the
`Developer’s Certificate of Origin
<https://github.com/rauc/meta-rauc/blob/master/DCO>`_.

Backporting
-----------

For backporting changes to a stable or LTS branch, two options exist:

a) drop a backport request in the original pull request
b) backport on your own and create a new pull request

When doing backports on your own, make sure to include a cherry-pick note and
the original commit-ish in a line below the original Signed-off-by and add your
own Signed-off-by below.
When using git, this can be done automatically with::

  git cherry-pick -xs <commit-ish>

Note that backports will be acccepted for actively maintained `poky releases
<https://wiki.yoctoproject.org/wiki/Releases>`_ only!

VIII. References
================

.. [1] http://rauc.readthedocs.io/en/latest/


.. |MIT| image:: https://img.shields.io/badge/license-MIT-blue.svg
   :target: https://raw.githubusercontent.com/rauc/meta-rauc/master/COPYING.MIT
.. |gh_action| image:: https://github.com/rauc/meta-rauc/workflows/meta-rauc%20CI/badge.svg
   :target: https://github.com/rauc/meta-rauc/actions?query=workflow%3A%22meta-rauc+CI%22
.. |Matrix| image:: https://img.shields.io/matrix/rauc:matrix.org?label=matrix%20chat
   :target: https://app.element.io/#/room/#rauc:matrix.org
