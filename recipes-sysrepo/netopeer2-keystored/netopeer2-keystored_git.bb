SUMMARY = "Netopeer2 is a set of tools implementing network configuration tools based on the NETCONF Protocol."
DESCRIPTION = "Netopeer2 is based on the new generation of the NETCONF and YANG libraries - libyang and libnetconf2. The Netopeer server uses sysrepo as a NETCONF datastore implementation."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=b7cb0021418524c05c4e5b21041d9402"

SRC_URI = "git://github.com/CESNET/Netopeer2.git;protocol=https"

# Modify these as desired
PV = "0.3.75+git${SRCPV}"
SRCREV = "bdb8cd747e6573a102b416f9e0cda76455c25ee5"

S = "${WORKDIR}/git/keystored"

DEPENDS = "libyang libnetconf2 sysrepo coreutils openssh openssl"

FILES_${PN} += "/usr/lib/sysrepo/* /usr/share/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DSYSREPOCTL_EXECUTABLE=/usr/bin/sysrepoctl -DSYSREPOCFG_EXECUTABLE=/usr/bin/sysrepocfg -DCHMOD_EXECUTABLE=/bin/chmod "

do_install_append () {
    install -d ${D}/usr/share/netopeer2-keystored
    cp -r ${S}/stock_key_config.xml ${D}/usr/share/netopeer2-keystored/
    install -d ${D}/etc/sysrepo/yang
    cp -r ${S}/../modules/ietf-keystore.yang ${D}/etc/sysrepo/yang/
}
