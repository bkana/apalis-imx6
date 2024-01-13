# set the following variable to your one and only application which should
# be started

INITIAL_APP_PKGS ?= "cinematicexperience"
INITIAL_PATH ?= "/usr/share/cinematicexperience-1.0"
X_APPLICATION ?= "${INITIAL_PATH}/Qt5_CinematicExperience"

FILESEXTRAPATHS:prepend := "${THISDIR}/x-window-simple-app:"
require recipes-graphics/x-window-simple-app/x-window-simple-app.inc
