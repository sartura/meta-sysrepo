# Recipe created by recipetool
SUMMARY = "YANG-based configuration and operational state data store for Unix/Linux applications."
DESCRIPTION = ""
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7df5a8706277b586ca000838046993d1"

SRC_URI = "git://github.com/sysrepo/sysrepo.git;protocol=https file://sysrepo"

PV = "1.0+git${SRCPV}"
SRCREV = "e01149730b043f8c8bf60f4a148ad79de0600a7d"

S = "${WORKDIR}/git"

DEPENDS = "libyang protobuf protobuf-c protobuf-c-native libredblack libev libnetconf2"

FILES_${PN} += "/usr/share/yang/* /run/sysrepo*"

inherit cmake pkgconfig pythonnative python-dir

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DBUILD_EXAMPLES:String=False -DENABLE_TESTS:String=False -DREPOSITORY_LOC:PATH=/etc/sysrepo  -DCALL_TARGET_BINS_DIRECTLY=False -DGEN_LANGUAGE_BINDINGS:String=False "

BBCLASSEXTEND = "native nativesdk" 

do_install_append () {
    rm -rf ${D}/var/run
    cp -a ${B}/install-yang.sh ${D}/usr/share/yang/
    sed -i -e '/shopt.*/d' ${D}/usr/share/yang/install-yang.sh
    install -d ${D}/etc/sysrepo/data/notifications
    install -d ${D}/etc/init.d
    install -m 0775 ${WORKDIR}/sysrepo ${D}/etc/init.d/
}

