DESCRIPTION = "ota-plus-client rust recipe"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = \
"file://README.md;beginline=1;endline=2;md5=e5b4c4b4ef35489d85664eeb98d16a49"

SRC_URI = "git://github.com/advancedtelematic/ota-plus-client.git;protocol=ssh;branch=stable-otb"
S = "${WORKDIR}/git"

SRCREV = "7787b00667b306b82d7e2a8f17b932fb73bd486b"

inherit systemd cargo

PV = "${SRCPV}"

# SERVICE_VERSION is picked up by the build process
export SERVICE_VERSION = "${PV}"

BBCLASSEXTEND = "native"

FILES_${PN} = " \
                /usr/bin/ota_plus_client \
                /etc/ota.version \
                ${base_libdir}/systemd/system/ota-plus-client.service \
              "

SYSTEMD_SERVICE_${PN} = "ota-plus-client.service"

DEPENDS += " openssl "
RDEPENDS_${PN} = " libcrypto libssl "

do_install() {
  install -d ${D}${bindir}
  install -m 0755 target/x86_64-poky-linux/release/ota_plus_client ${D}${bindir}

  install -d ${D}${systemd_unitdir}/system
  install -c ${S}/pkg/yocto/ota-plus-client.service ${D}${systemd_unitdir}/system

  install -d ${D}${sysconfdir}
  echo "${PV}" > ${D}${sysconfdir}/ota.version
}
