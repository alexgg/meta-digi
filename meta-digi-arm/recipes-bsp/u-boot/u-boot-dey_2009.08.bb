# Copyright (C) 2012 Digi International

DESCRIPTION = "Bootloader for Digi platforms"
require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PR = "r0"

SRCREV_external = ""
SRCREV_internal = "61ac25cbf81c10bda9e3a74005734f5e419aee5c"
SRCREV = "${@base_conditional('DIGI_INTERNAL_GIT', '1' , '${SRCREV_internal}', '${SRCREV_external}', d)}"

SRC_URI_external = "${DIGI_GITHUB_GIT}/yocto-uboot.git;protocol=git"
SRC_URI_internal = "${DIGI_LOG_GIT}u-boot-denx.git;protocol=git"
SRC_URI = "${@base_conditional('DIGI_INTERNAL_GIT', '1' , '${SRC_URI_internal}', '${SRC_URI_external}', d)}"

S = "${WORKDIR}/git"

DEPENDS_mxs += "elftosb-native imx-bootlets-dey"

EXTRA_OEMAKE += 'HOSTSTRIP=true'
EXTRA_OEMAKE_append_mxs = ' BOOTLETS_DIR=${STAGING_DIR_TARGET}/boot'

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(ccardimx28js|ccimx51js|ccimx53js|cpx2|wr21)"
