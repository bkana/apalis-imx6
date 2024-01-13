FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# am62xx extra gles2 packageconfig flags
GLES_EXTRA_DEPS:am62xx = "libdrm wayland"
# rename from gles2 to gles2extra to preserve the original one for non am62xx machines
PACKAGECONFIG[gles2extra] = "-opengl es2 -eglfs,,virtual/libgles2 virtual/egl ${GLES_EXTRA_DEPS}"

PR:append:am62xx = ".arago17"

QT_CONFIG_FLAGS:append:am62xx = " -qpa ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', 'eglfs', d)}"

QT_EGLFS_PATCHES = "\
    file://0001-calculator-Add-exit-button-for-non-window-environmen.patch \
    file://0002-animatedtiles-Add-exit-button-for-non-window-environ.patch \
    file://quit.png \
"

#    file://0002-deform-disable-opengl-button.patch

SRC_URI:append:am62xx = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', "${QT_EGLFS_PATCHES}", d)}\
    file://0001-deform-Fix-how-controls-are-shown.patch \
    file://0001-qtbase-plugins-platforms-eglfs_kms-fix-compiler-erro.patch \
    file://0001-eglfs-Force-888-format-only-on-env-flag.patch \
"

python do_patch:append:am62xx () {
    import shutil

    work_dir = d.getVar("WORKDIR")
    s = d.getVar("S")

    if not bb.utils.contains('DISTRO_FEATURES','wayland',True,False,d):
        shutil.copy(os.path.join(work_dir,"quit.png"),os.path.join(s,"examples/widgets/animation/animatedtiles/images/"))
}

# Add symbolic link qt5/examples for backward compatibility
do_install:append:am62xx () {

    install -d ${D}${datadir}/qt5
    ln -sf ../examples ${D}${datadir}/qt5/examples
}

FILES:${PN}-examples:append:am62xx =  " ${datadir}/qt5/*"


RDEPENDS:${PN}:am62xx += "${PN}-conf"

PACKAGE_ARCH:am62xx = "${MACHINE_ARCH}"


############################### TDX added

# from meta-arago/conf/layer.conf
PACKAGECONFIG_GL:am62xx = "gles2extra linuxfb"
PACKAGECONFIG_DISTRO:am62xx = "icu examples accessibility gif gbm kms libinput"
PACKAGECONFIG_FONTS:am62xx = "fontconfig"

PACKAGECONFIG:remove:am62xx = "kms"
PACKAGECONFIG:remove:am62xx = "vulkan"
PACKAGECONFIG:remove:am62xx = "glib xcb"
# they don't apply without changes, drop them for now
SRC_URI:remove = " \
    file://0002-deform-disable-opengl-button.patch \
    \
    file://0001-deform-Fix-how-controls-are-shown.patch \
    file://0001-qtbase-plugins-platforms-eglfs_kms-fix-compiler-erro.patch \
    file://0001-eglfs-Force-888-format-only-on-env-flag.patch \
"
