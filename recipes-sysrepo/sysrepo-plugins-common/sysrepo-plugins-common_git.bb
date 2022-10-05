SUMMARY = "Set of utilities/functionalities which can be used for easier build of sysrepo plugins."
DESCRIPTION = ""
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f91d5dfaae99cc1943a8eca222cafa5c"

SRC_URI = "gitsm://github.com/telekom/sysrepo-plugins-common.git;protocol=https;branch=devel "
SRC_URI += " file://0001-so-version.patch "
FILESEXTRAPATHS:prepend := "${THISDIR}:"

PV = "dev+git${SRCPV}"
SRCREV = "6f98eaef59d4d197c2401b133e9b55105e382246"

S = "${WORKDIR}/git"

DEPENDS = "libyang sysrepo"

FILES:${PN} += ""

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr "

do_install:append () {
    true
}

