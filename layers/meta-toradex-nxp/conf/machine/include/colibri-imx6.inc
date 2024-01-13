IMAGE_CLASSES:append = " image_type_tezi"
IMAGE_FSTYPES:append = " teziimg"
# The payload offsets are given in number of 512 byte blocks.
OFFSET_BOOTROM_PAYLOAD = "2"
OFFSET_SPL_PAYLOAD = "138"
TORADEX_PRODUCT_IDS = "0014 0015 0016 0017"

MACHINE_NAME = "Colibri-iMX6"

TEZI_EXTERNAL_KERNEL_DEVICETREE_BOOT:use-mainline-bsp = " \
    colibri-imx6_vga-640x480_overlay.dtbo \
"
TEZI_EXTERNAL_KERNEL_DEVICETREE_BOOT = ""

KERNEL_DEVICETREE:append = " \
    imx6dl-colibri-aster.dtb \
    imx6dl-colibri-iris.dtb \
    imx6dl-colibri-iris-v2.dtb \
"

PREFERRED_PROVIDER_virtual/kernel = "linux-toradex"
PREFERRED_PROVIDER_virtual/kernel:preempt-rt = "linux-toradex"
PREFERRED_PROVIDER_virtual/kernel:use-mainline-bsp = "linux-toradex-mainline"
PREFERRED_PROVIDER_virtual/kernel:use-mainline-bsp:preempt-rt = "linux-toradex-mainline-rt"
PREFERRED_PROVIDER_virtual/dtb:use-mainline-bsp = "device-tree-overlays-mainline"

IMX_DEFAULT_BOOTLOADER:colibri-imx6 = "u-boot-toradex"
PREFERRED_PROVIDER_u-boot-default-script = "u-boot-distro-boot"
UBOOT_SUFFIX = "img"

MACHINE_FIRMWARE:remove = "firmware-imx-epdc"

UBOOT_DTB_LOADADDRESS:colibri-imx6 = "0x18200000"
UBOOT_DTBO_LOADADDRESS:colibri-imx6 = "0x18300000"
UBOOT_ENTRYPOINT:colibri-imx6 = "0x14200000"
UBOOT_RD_LOADADDRESS:colibri-imx6 = "0x1A400000"
