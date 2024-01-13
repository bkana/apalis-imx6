FILESEXTRAPATHS:prepend := "${THISDIR}/lxqt-panel:"

# add some widgets, add some applications to quicklaunch by injecting
# panel.conf configured on the target

SRC_URI += "file://panel.conf"
do_configure:prepend () {
    cp ${WORKDIR}/panel.conf ${S}/panel/resources/
}
