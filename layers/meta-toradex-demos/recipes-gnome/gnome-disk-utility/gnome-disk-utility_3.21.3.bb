SUMMARY = "GNOME disk utility"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "avahi-ui glib-2.0 gnome-doc-utils gtk+3 intltool-native \
           libatasmart libcanberra libdvdread libgnome-keyring \
           libnotify libpwquality libsecret libunique libxslt-native \
           pkgconfig-native udisks2 virtual/libx11 xmlto-native xz"

PR = "r4"

inherit gnomebase gtk-icon-cache
SRC_URI[archive.md5sum] = "265ed1aede9ef0570228ca6908e9df59"
SRC_URI[archive.sha256sum] = "f39508226268f39dafa8977ebcec1d36e694f8aa7c4a404d1ac50f76e8e57739"
GNOME_COMPRESS_TYPE = "xz"

EXTRA_OECONF += "--disable-gsd-plugin"

do_configure:prepend() {
    sed -i -e "s: help : :g" ${S}/Makefile.am
}

FILES:${PN} += "${datadir}/appdata ${datadir}/dbus-1/services"
FILES:${PN}-libs += "${libdir}/libgdu*.so.*"
