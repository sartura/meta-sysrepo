SUMMARY = "Netopeer2 is a set of tools implementing network configuration tools based on the NETCONF Protocol."
DESCRIPTION = "Netopeer2 is based on the new generation of the NETCONF and YANG libraries - libyang and libnetconf2. The Netopeer server uses sysrepo as a NETCONF datastore implementation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=41daedff0b24958b2eba4f9086d782e1"

SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https;branch=devel file://netopeer2-server"

PV = "2.1.38+git${SRCPV}"
SRCREV = "3007b6f4c3052e4ba39f1956a6eab49388a3cc9b"

S = "${WORKDIR}/git"

DEPENDS = "libyang libnetconf2 sysrepo curl"
RDEPENDS:${PN} += "bash curl"

FILES:${PN} += "/usr/share/yang* /usr/share/netopeer2/* /usr/lib/sysrepo-plugind/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES=OFF -DGENERATE_HOSTKEY=OFF -DMERGE_LISTEN_CONFIG=OFF"

do_install:append () {
    install -d ${D}/etc/netopeer2/scripts
    install -o root -g root ${S}/scripts/setup.sh ${D}/etc/netopeer2/scripts/setup.sh
    install -o root -g root ${S}/scripts/merge_hostkey.sh ${D}/etc/netopeer2/scripts/merge_hostkey.sh
    install -o root -g root ${S}/scripts/merge_config.sh ${D}/etc/netopeer2/scripts/merge_config.sh
    install -d ${D}/etc/netopeer2
    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/netopeer2-server ${D}/etc/init.d/
}

