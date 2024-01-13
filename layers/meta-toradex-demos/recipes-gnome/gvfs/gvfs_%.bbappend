#do not build with libgphoto2
PACKAGECONFIG:remove = "libgphoto2"

DEPENDS:append = " udev libusb1"
