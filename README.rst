.. list-table::
   :header-rows: 1

   * - master
     - whinlatter
     - scarthgap
     - kirkstone
   * - |gh_master|
     - |gh_whinlatter|
     - |gh_scarthgap|
     - |gh_kirkstone|

|MIT| |Matrix|

The meta-rauc Yocto/OpenEmbedded layer provides integration for
`RAUC <https://github.com/rauc/rauc>`_, a robust and secure **update framework
for embedded Linux systems**.

This layer integrates RAUC into Yocto builds for both **target usage (update
client)** and **host usage (bundle creation)**.

For a detailed description on steps necessary to integrate RAUC into your
project, refer https://rauc.readthedocs.io/en/latest/integration.html.


Layer Dependencies
==================

This layer depends on:

* `openembedded-core <https://github.com/openembedded/openembedded-core.git>`_

To use the optional casync feature with FUSE, you will also need:

* ``meta-filesystems`` from `meta-openembedded <https://github.com/openembedded/meta-openembedded.git>`_.

Migration Notes
===============

Since **wrynose**, the ``rauc-conf`` recipe installs the ``system.conf`` and
keyring file into ``/usr/lib/rauc`` to align with the 'hermetic ``/usr/``'
policy followed by other OE components (like systemd).
If you rely on the exact path where the ``system.conf`` is installed, you will
need to adjust your integration.

Since **scarthgap**, the platform configuration (system.conf, keyring, etc.) was
moved to a separate ``rauc-conf.bb`` recipe to allow building the rauc package
with ``TUNE_PKGARCH`` and have a clearer separation between the binary and the
configuration.

Thus when updating to scarthgap or newer, make sure to move all
configuration-specific adaptions from your ``rauc_%.bbappend`` to a
``rauc-conf.bbappend`` file.


Adding the RAUC Update Service to Your Device
=============================================

To prepare your device for using RAUC as its update handler,
at least the following steps are required:

1. Add ``rauc`` to ``DISTRO_FEATURES`` in your distro (or local) config::

     DISTRO_FEATURES += "rauc"

2. Provide a RAUC system configuration (and keyring) in your BSP layer by
   creating a ``rauc-conf.bbappend`` that fetches your custom ``system.conf``
   (and keyring) from your layer::

     FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

   The ``rauc-conf`` recipe will install these files into ``/usr/lib/rauc/`` by
   default.

   For information on how to write a proper RAUC system configuration, please
   refer to the RAUC user documentation [1]_.

Note: If you do not use packagegroup-base, you also need to manually add
the ``rauc`` package to your systems image recipe::

     IMAGE_INSTALL:append = " rauc"

Building a RAUC Update Bundle
=============================

1. Create a bundle recipe for your device by adding a recipe
   that inherits the ``bundle`` class and adds all desired
   configuration::

     inherit bundle

     RAUC_BUNDLE_FORMAT = "verity"

     RAUC_BUNDLE_SLOTS = "rootfs"
     RAUC_SLOT_rootfs = "my-rootfs-recipe"

     RAUC_KEY_FILE = "path/to/development-1.key.pem"
     RAUC_CERT_FILE = "path/to/development-1.cert.pem"

   For information on how to generate and use the key and certificate files,
   please refer to the RAUC user documentation [1]_.

   For a more detailed explanation on the required and available variables,
   read the notes in the bundle.bbclass file.

2. Build a bundle and the rootfs for your device::

     bitbake my-bundle-recipe


Building and Using the RAUC Host Tool
=====================================

To manually build and use RAUC as a host tool from your BSP (e.g. for
calling ``rauc info`` on your built bundle), run::

  bitbake rauc-native -caddto_recipe_sysroot
  oe-run-native rauc-native rauc info --keyring=/path/to/keyring.pem tmp/deploy/images/<machine>/<bundle-name>.raucb

To manually build and use the ``casync`` host tool, run::

  bitbake casync-native -caddto_recipe_sysroot
  oe-run-native casync-native casync --help

Building The RAUC hawkBit Client
================================

This layer offers support for ``rauc-hawkbit-updater``, a daemon that
interfaces between RAUC and the hawkBit deployment server.

To use ``rauc-hawkbit-updater`` in your system add to your image recipe::

    IMAGE_INSTALL:append = " rauc-hawkbit-updater"

Configure Custom Kernel
=======================

To use RAUC on your system, the kernel must support SquashFS and loop
mounts. For the standard yocto kernel, the meta-rauc layer provides a kernel
configuration fragment that enables the config options required for this.

If you build your own kernel with a full custom ``defconfig`` file, you have to
make sure that the options in ``recipes-kernel/linux/linux-yocto/rauc.cfg`` are
enabled in your configuration, too.

Build RAUC Development Version
==============================

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

Release Cycle
=============

We follow the Yocto Project release methodology, schedule, and stable/LTS
support timelines.
See https://docs.yoctoproject.org/ref-manual/release-process.html for more
information.

Contributing
============

To report bugs, file a new `issue <https://github.com/rauc/meta-rauc/issues>`_
on GitHub.

For fixing bugs, bumping recipes, or adding new features, open a `pull request
<https://github.com/rauc/meta-rauc/pulls>`_ on GitHub.

Add a ``Signed-off-by`` line to your commits according to the
`Developer’s Certificate of Origin
<https://github.com/rauc/meta-rauc/blob/master/DCO>`_.

Backporting
-----------

For backporting fixes or version bumps to a stable or LTS branch, two options exist:

a) drop a backport request in the original pull request
b) backport on your own and create a new pull request

When doing backports on your own, make sure to include a cherry-pick/backport note and
the original commit-ish in a line below the original Signed-off-by and add your
own Signed-off-by below.
When using git, this can be done automatically with::

  git cherry-pick -xs <commit-ish>

Note that backports will be accepted for `actively maintained Yocto releases
<https://wiki.yoctoproject.org/wiki/Releases>`_ only!

Maintainer(s)
-------------

* Enrico Joerns <ejo@pengutronix.de>

References
==========

.. [1] http://rauc.readthedocs.io/en/latest/


.. |MIT| image:: https://img.shields.io/badge/license-MIT-blue.svg
   :target: https://raw.githubusercontent.com/rauc/meta-rauc/master/COPYING.MIT
.. |gh_kirkstone| image:: https://github.com/rauc/meta-rauc/actions/workflows/build.yml/badge.svg?branch=kirkstone&event=workflow_dispatch
   :target: https://github.com/rauc/meta-rauc/actions?query=event%3Aworkflow_dispatch+branch%3Akirkstone++
.. |gh_scarthgap| image:: https://github.com/rauc/meta-rauc/actions/workflows/build.yml/badge.svg?branch=scarthgap&event=workflow_dispatch
   :target: https://github.com/rauc/meta-rauc/actions?query=event%3Aworkflow_dispatch+branch%3Ascarthgap++
.. |gh_whinlatter| image:: https://github.com/rauc/meta-rauc/actions/workflows/build.yml/badge.svg?branch=whinlatter&event=workflow_dispatch
   :target: https://github.com/rauc/meta-rauc/actions?query=event%3Aworkflow_dispatch+branch%3Awhinlatter++
.. |gh_master| image:: https://github.com/rauc/meta-rauc/actions/workflows/build.yml/badge.svg?branch=master&event=workflow_dispatch
   :target: https://github.com/rauc/meta-rauc/actions?query=event%3Aworkflow_dispatch+branch%3Amaster++
.. |Matrix| image:: https://img.shields.io/matrix/rauc:matrix.org?label=matrix%20chat
   :target: https://app.element.io/#/room/#rauc:matrix.org
