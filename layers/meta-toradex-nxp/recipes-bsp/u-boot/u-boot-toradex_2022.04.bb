SUMMARY = "U-Boot bootloader with support for Toradex i.MX 8 series SoMs"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"
DEPENDS = "bc-native dtc-native flex-native bison-native python3-setuptools-native"
PROVIDES:append = " u-boot"

require recipes-bsp/u-boot/u-boot.inc

SRC_URI = "git://git.toradex.com/u-boot-toradex.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "4f3579707e340b61c631b0aca00fc93e6283037f"
SRCREV:use-head-next = "${AUTOREV}"
SRCBRANCH = "toradex_imx_lf_v2022.04"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

BOOT_TOOLS = "imx-boot-tools"

inherit toradex-u-boot-localversion

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "(mx8-generic-bsp)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_deploy:append:mx8m-generic-bsp() {
    # Deploy the u-boot-nodtb.bin and fsl-imx8m*-XX.dtb for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}/${UBOOT_DTB_NAME}-${type}
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${type}
                fi
            done
            unset  j
        done
        unset  i
    fi
}
