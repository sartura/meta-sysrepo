# Recipe created by recipetool
SUMMARY = "YANG-based configuration and operational state data store for Unix/Linux applications."
DESCRIPTION = ""
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef345f161efb68c3836e6f5648b2312f"

SRC_URI = "git://github.com/sysrepo/sysrepo.git;protocol=https;branch=devel file://sysrepo"

PV = "2.2.6+git${SRCPV}"
SRCREV = "bdf67e0c0164b9a2e806105a719d139e7300bdea"

S = "${WORKDIR}/git"

DEPENDS = "libyang protobuf protobuf-c protobuf-c-native libredblack libev libnetconf2"

FILES:${PN} += "/usr/share/yang/* /usr/lib/sysrepo-plugind/*"

inherit cmake pkgconfig python3native python3-dir

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DBUILD_EXAMPLES:String=False -DENABLE_TESTS:String=False -DREPOSITORY_LOC:PATH=/etc/sysrepo  -DCALL_TARGET_BINS_DIRECTLY=False -DGEN_LANGUAGE_BINDINGS:String=False "

BBCLASSEXTEND = "native nativesdk" 

do_install:append () {
    install -d ${D}/etc/sysrepo/data/notifications
    install -d ${D}/etc/sysrepo/yang
    install -o root -g root ${S}/modules/ietf-netconf-notifications.yang ${D}/etc/sysrepo/yang/ietf-netconf-notifications@2012-02-06.yang
    install -o root -g root ${S}/modules/ietf-netconf-with-defaults.yang ${D}/etc/sysrepo/yang/ietf-netconf-with-defaults@2011-06-01.yang
    install -o root -g root ${S}/modules/ietf-netconf.yang ${D}/etc/sysrepo/yang/ietf-netconf@2011-06-01.yang
    install -d ${D}/etc/init.d
    install -m 0775 ${WORKDIR}/sysrepo ${D}/etc/init.d/
    install -d ${D}/usr/lib/sysrepo/plugins
}

