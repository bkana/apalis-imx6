LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# The verdin-imx8mm does hang during resume if etnaviv and/or hantro_vpu
# are loaded. As graphics currently anyway doesn't work anyway do not
# load these modules
do_install:append:verdin-imx8mm () {
    install -d ${D}${sysconfdir}/modprobe.d
    echo "blacklist etnaviv" > ${D}${sysconfdir}/modprobe.d/imx8mm-blacklist.conf
    echo "blacklist hantro_vpu" >> ${D}${sysconfdir}/modprobe.d/imx8mm-blacklist.conf
}

ALLOW_EMPTY:${PN} = "1"
FILES:${PN} = " ${sysconfdir}/modprobe.d"
