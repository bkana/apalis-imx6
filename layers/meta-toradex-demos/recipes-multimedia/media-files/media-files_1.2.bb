SUMMARY = "Media Files for tests"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/CC0-1.0;md5=0ceb3372c9595f0a8067e55da801e4a1"

inherit allarch bin_package

SRC_URI = " \
    https://developer1.toradex.com/files/toradex-dev/uploads/media/Colibri/AddSW/Linux/ReleaseTest/media-files-${PV}.tar.xz \
"
SRC_URI[md5sum] = "9db08fa5c08063d345a7fc7e8b49b55e"
SRC_URI[sha256sum] = "f6f40cd90ba6da06644cbfe5d48498c42836654897707fedeaaea6e00d99d334"

S = "${WORKDIR}/media-files"
