SUMMARY = "YANG data modelling language parser and toolkit"
DESCRIPTION = "libyang is YANG data modelling language parser and toolkit written (and providing API) in C. The library is used e.g. in libnetconf2, Netopeer2 or sysrepo projects."
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f3916d7d8d42a6508d0ea418cfff10ad"

SRC_URI = "git://github.com/CESNET/libyang.git;protocol=https;branch=devel"

PV = "2.0.256+git${SRCPV}"
SRCREV = "56d4e07ef1cdeab3eb2e6700247f83ec9148edcc"

S = "${WORKDIR}/git"

DEPENDS = "libpcre2"

FILES:${PN} += "/usr/share/yang/modules/libyang/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release "

BBCLASSEXTEND = "native nativesdk"
