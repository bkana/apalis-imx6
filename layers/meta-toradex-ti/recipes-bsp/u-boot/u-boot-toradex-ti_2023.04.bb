SUMMARY = "U-Boot bootloader with support for Toradex AM62 series SoMs"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"

require recipes-bsp/u-boot/u-boot-ti.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRC_URI = "git://git.toradex.com/u-boot-toradex.git;protocol=https;branch=${SRCBRANCH}"

SRCREV = "0db5fb465be846c8ca7f69528d267b9ca2aa07e8"
SRCREV:use-head-next = "${AUTOREV}"
SRCBRANCH = "toradex_ti-u-boot-2023.04"

B = "${WORKDIR}/build"
S = "${WORKDIR}/git"

inherit toradex-u-boot-localversion

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "(ti-soc)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# preserve and deploy u-boot-initial-env if building for the Cortex-A53 core
# even if u-boot-ti.inc do_deploy() tries to delete it.
DEPLOY_INITIAL_ENV = "install -D -m 644 ${B}/sd/u-boot-initial-env ${DEPLOYDIR}/ || true"
DEPLOY_INITIAL_ENV:k3r5 = ":"
do_deploy:append () {
    ${DEPLOY_INITIAL_ENV}
}
do_deploy:append:k3r5 () {
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            if [ x${config} = "xverdin-am62_r5_usbdfu_defconfig" ]
            then
                TARGETSUFFIX="-dfu"
            else
                TARGETSUFFIX=""
            fi
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    for f in ${B}/${config}/tiboot3-*.bin; do
                        if [ -f "$f" ]; then
                            TARGET=$(basename $f)${TARGETSUFFIX}
                            install -m 644 $f ${DEPLOYDIR}/${TARGET}
                        fi
                    done

                    for f in ${B}/${config}/sysfw*.itb; do
                            if [ -f "$f" ]; then
                                    install -m 644 $f ${DEPLOYDIR}/
                            fi
                    done
                fi
            done
            unset j
        done
        unset i
    else
        if ! [ -f ${B}/${UBOOT_BINARY} ]; then
            ln -s spl/${UBOOT_BINARY} ${B}/${UBOOT_BINARY}
        fi
    fi
}

# build the k3r5 spl also for DFU
do_compile:append:k3r5 () {
    if [ -L ${B}/${UBOOT_BINARY} ]; then
        rm ${B}/${UBOOT_BINARY}
    fi

    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    if ! [ -f ${B}/${config}/${UBOOT_BINARY} ]; then
                        ln -s spl/${UBOOT_BINARY} ${B}/${config}/${UBOOT_BINARY}
                    fi
                fi
            done
            unset j
        done
        unset i
    else
        if ! [ -f ${B}/${UBOOT_BINARY} ]; then
            ln -s spl/${UBOOT_BINARY} ${B}/${UBOOT_BINARY}
        fi
    fi
}
