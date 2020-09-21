SUMMARY = "YANG data modelling language parser and toolkit"
DESCRIPTION = "libyang is YANG data modelling language parser and toolkit written (and providing API) in C. The library is used e.g. in libnetconf2, Netopeer2 or sysrepo projects."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2982272c97a8e417a844857ca0d303b1"

SRC_URI = "git://github.com/CESNET/libyang.git;protocol=https"

PV = "1.0.184+git${SRCPV}"
SRCREV = "6defee4cab9e76bdd736bb3f751de396b4bf5a4b"

S = "${WORKDIR}/git"

DEPENDS = "libpcre"

FILES_${PN} += "/usr/lib/libyang1/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release "

BBCLASSEXTEND = "native nativesdk"

