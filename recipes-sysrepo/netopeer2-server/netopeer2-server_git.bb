SUMMARY = "Netopeer2 is a set of tools implementing network configuration tools based on the NETCONF Protocol."
DESCRIPTION = "Netopeer2 is based on the new generation of the NETCONF and YANG libraries - libyang and libnetconf2. The Netopeer server uses sysrepo as a NETCONF datastore implementation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=41daedff0b24958b2eba4f9086d782e1"

SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https file://netopeer2-server"

PV = "1.1.1+git${SRCPV}"
SRCREV = "2c9a7c6395772b027fbb15f1a485a843eda43f38"

S = "${WORKDIR}/git/server"

DEPENDS = "libyang libnetconf2 sysrepo curl"
RDEPENDS_${PN} += "bash curl"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DINSTALL_MODULES:BOOL=OFF -DGENERATE_HOSTKEY:BOOL=OFF -DMERGE_LISTEN_CONFIG:BOOL=OFF"

do_install_append () {
    install -d ${D}/usr/share/netopeer2-server
    install -d ${D}/etc/sysrepo/yang
    install -d ${D}/etc/Netopeer2/modules
    install -o root -g root ${S}/../modules/ietf-netconf-acm@2018-02-14.yang ${D}/etc/Netopeer2/modules/ietf-netconf-acm@2018-02-14.yang
    install -o root -g root ${S}/../modules/ietf-netconf@2013-09-29.yang ${D}/etc/Netopeer2/modules/ietf-netconf@2013-09-29.yang
    install -o root -g root ${S}/../modules/ietf-netconf-monitoring@2010-10-04.yang ${D}/etc/Netopeer2/modules/ietf-netconf-monitoring@2010-10-04.yang
    install -o root -g root ${S}/../modules/ietf-datastores@2017-08-17.yang  ${D}/etc/Netopeer2/modules/ietf-datastores@2017-08-17.yang
    install -o root -g root ${S}/../modules/ietf-netconf-nmda@2019-01-07.yang ${D}/etc/Netopeer2/modules/ietf-netconf-nmda@2019-01-07.yang
    install -o root -g root ${S}/../modules/notifications@2008-07-14.yang ${D}/etc/Netopeer2/modules/notifications@2008-07-14.yang
    install -o root -g root ${S}/../modules/nc-notifications@2008-07-14.yang ${D}/etc/Netopeer2/modules/nc-notifications@2008-07-14.yang
    install -o root -g root ${S}/../modules/ietf-x509-cert-to-name@2014-12-10.yang ${D}/etc/Netopeer2/modules/ietf-x509-cert-to-name@2014-12-10.yang
    install -o root -g root ${S}/../modules/ietf-crypto-types@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-crypto-types@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-keystore@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-keystore@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-truststore@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-truststore@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-tcp-common@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-tcp-common@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-tcp-client@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-tcp-client@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-tcp-server@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-tcp-server@2019-07-02.yang
    install -o root -g root ${S}/../modules/iana-crypt-hash@2014-08-06.yang ${D}/etc/Netopeer2/modules/iana-crypt-hash@2014-08-06.yang
    install -o root -g root ${S}/../modules/ietf-ssh-server@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-ssh-server@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-ssh-common@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-ssh-common@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-tls-common@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-tls-common@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-tls-server@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-tls-server@2019-07-02.yang
    install -o root -g root ${S}/../modules/ietf-netconf-server@2019-07-02.yang ${D}/etc/Netopeer2/modules/ietf-netconf-server@2019-07-02.yang
    install -d ${D}/etc/Netopeer2/scripts
    install -o root -g root ${S}/setup.sh ${D}/etc/Netopeer2/scripts/setup.sh
    install -o root -g root ${S}/merge_hostkey.sh ${D}/etc/Netopeer2/scripts/merge_hostkey.sh
    install -o root -g root ${S}/merge_config.sh ${D}/etc/Netopeer2/scripts/merge_config.sh
    install -d ${D}/etc/netopeer2
    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/netopeer2-server ${D}/etc/init.d/
}

