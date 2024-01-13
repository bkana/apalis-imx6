# set the following variable to your one and only application which should
# be started

INITIAL_APP_PKGS ?= "xterm"
INITIAL_PATH ?= ""
X_APPLICATION ?= "/usr/bin/xterm"

FILESEXTRAPATHS:prepend := "${THISDIR}/x-window-simple-app:"
require recipes-graphics/x-window-simple-app/x-window-simple-app.inc
