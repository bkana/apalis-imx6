FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PR:append:am62xx = ".arago2"

SRC_URI:append:am62xx = " \
    file://0001-wayland-drm-Add-wayland-drm-protocol-to-stable.patch \
    file://0002-wayland-drm-Update-to-version-2.patch \
"

do_install:append:am62xx () {
	install -d ${D}${datadir}/wayland-protocols/stable/wayland-drm
	cp ${S}/stable/wayland-drm/wayland-drm.xml ${D}${datadir}/wayland-protocols/stable/wayland-drm/
}
