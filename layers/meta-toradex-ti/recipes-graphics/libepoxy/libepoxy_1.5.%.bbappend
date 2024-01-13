# Append EGL_CFLAGS to CFLAGS
CFLAGS:append:am62xx = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', '-DEGL_API_FB', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-DWL_EGL_PLATFORM', '', d)} \
"
PACKAGE_ARCH:am62xx = "${MACHINE_ARCH}"
