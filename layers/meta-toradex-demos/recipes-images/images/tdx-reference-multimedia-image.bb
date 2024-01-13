require tdx-reference-minimal-image.bb

SUMMARY = "Toradex Embedded Linux Reference Multimedia Image"
DESCRIPTION = "Image for BSP verification with QT and multimedia features"

inherit populate_sdk_qt5

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "Reference-Multimedia-Image"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base', '', d), d)} \
"

APP_LAUNCH_WAYLAND ?= "wayland-qtdemo-launch-cinematicexperience"
APP_LAUNCH_WAYLAND:colibri-imx6ull ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND:colibri-imx6ull-emmc ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND:colibri-imx7 ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND:colibri-imx7-emmc ?= "wayland-qtdemo-launch-analogclock"
APP_LAUNCH_WAYLAND:verdin-am62 ?= "wayland-qtdemo-launch-analogclock"

APP_LAUNCH_X11 ?= "x-window-qtcinematicexperience"
APP_LAUNCH_X11:colibri-imx6ull ?= "x-window-analogclock"
APP_LAUNCH_X11:colibri-imx6ull-emmc ?= "x-window-analogclock"
APP_LAUNCH_X11:colibri-imx7 ?= "x-window-analogclock"
APP_LAUNCH_X11:colibri-imx7-emmc ?= "x-window-analogclock"
APP_LAUNCH_X11:verdin-am62 ?= "x-window-analogclock"

IMAGE_INSTALL += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         '${APP_LAUNCH_WAYLAND}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', \
       bb.utils.contains('DISTRO_FEATURES', 'x11', '${APP_LAUNCH_X11}', '', d), d)} \
    \
    packagegroup-tdx-cli \
    packagegroup-tdx-graphical \
    packagegroup-tdx-qt5 \
    packagegroup-fsl-isp \
    \
    bash \
    coreutils \
    less \
    makedevs \
    mime-support \
    net-tools \
    util-linux \
    v4l-utils \
    \
    gpicview \
    media-files \
"
