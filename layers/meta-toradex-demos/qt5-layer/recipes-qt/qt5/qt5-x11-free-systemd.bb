SECTION = "x11/libs"
SUMMARY = "systemd qtapplication autostart"
# The license is meant for this recipe and the files it installs.
# RNDIS is part of the kernel, udhcpd is part of busybox
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd

SRC_URI = " \
    file://qt5-x11-demo.service \
    file://qt5-x11-demo-init \
"

do_install () {
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/qt5-x11-demo-init ${D}/${bindir}

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/qt5-x11-demo.service ${D}${systemd_unitdir}/system
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "qt5-x11-demo.service"
