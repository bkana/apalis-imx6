# build the k3r5 tiboot also for DFU
EXTRA_OEMAKE_DFU = "\
    CROSS_COMPILE=${TARGET_PREFIX} SOC=${SYSFW_SOC} SOC_TYPE=${SYSFW_SUFFIX} \
    CONFIG=${SYSFW_CONFIG} SYSFW_DIR="${S}/ti-sysfw" \
    SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin-${UBOOT_DFU_CONFIG}" \
"

do_compile:prepend:k3r5 () {
    if [ -e "${STAGING_DIR_HOST}/boot/u-boot-spl.bin-${UBOOT_DFU_CONFIG}" ]; then
        cd ${WORKDIR}/imggen/
        make ${EXTRA_OEMAKE_DFU} $@
        if [ -f "${WORKDIR}/imggen/${SYSFW_TIBOOT3}" ]; then
            mv "${WORKDIR}/imggen/${SYSFW_TIBOOT3}" "${WORKDIR}/imggen/${SYSFW_TIBOOT3}"-dfu
        fi
        make clean
    fi
}

do_deploy:append:k3r5 () {
    if [ -f "${WORKDIR}/imggen/${SYSFW_TIBOOT3}-dfu" ]; then
        install -m 644 ${WORKDIR}/imggen/${SYSFW_TIBOOT3}-dfu ${DEPLOYDIR}/
    fi
}
