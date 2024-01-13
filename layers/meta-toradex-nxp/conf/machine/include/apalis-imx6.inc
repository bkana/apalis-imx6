IMAGE_CLASSES:append = " image_type_tezi"
IMAGE_FSTYPES:append = " teziimg"
# The payload offsets are given in number of 512 byte blocks.
OFFSET_BOOTROM_PAYLOAD = "2"
OFFSET_SPL_PAYLOAD = "138"
TORADEX_PRODUCT_IDS = "0027 0028 0029 0035"

MACHINE_NAME = "Apalis-iMX6"

TEZI_EXTERNAL_KERNEL_DEVICETREE_BOOT:use-mainline-bsp = " \
    apalis-imx6_hdmi_overlay.dtbo \
    apalis-imx6_spi1_spidev_overlay.dtbo \
    apalis-imx6_spi2_spidev_overlay.dtbo \
"
TEZI_EXTERNAL_KERNEL_DEVICETREE_BOOT = ""

KERNEL_DEVICETREE:append = " \
    imx6q-apalis-ixora-v1.2.dtb \
"

PREFERRED_PROVIDER_virtual/kernel = "linux-toradex"
PREFERRED_PROVIDER_virtual/kernel:preempt-rt = "linux-toradex"
PREFERRED_PROVIDER_virtual/kernel:use-mainline-bsp = "linux-toradex-mainline"
PREFERRED_PROVIDER_virtual/kernel:use-mainline-bsp:preempt-rt = "linux-toradex-mainline-rt"
PREFERRED_PROVIDER_virtual/dtb:use-mainline-bsp = "device-tree-overlays-mainline"

IMX_DEFAULT_BOOTLOADER:apalis-imx6 = "u-boot-toradex"
PREFERRED_PROVIDER_u-boot-default-script = "u-boot-distro-boot"
UBOOT_SUFFIX = "img"

MACHINE_FIRMWARE:remove = "firmware-imx-epdc"

UBOOT_DTB_LOADADDRESS:apalis-imx6 = "0x18200000"
UBOOT_DTBO_LOADADDRESS:apalis-imx6 = "0x18300000"
UBOOT_ENTRYPOINT:apalis-imx6 = "0x14200000"
UBOOT_RD_LOADADDRESS:apalis-imx6 = "0x1A400000"
