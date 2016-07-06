DESCRIPTION = "Simple OSTree-based package manager"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = \
"file://${S}/README.md;beginline=1;endline=2;md5=c02776b897cc43f60d2c98cc545094b2"

SRC_URI = "git://github.com/advancedtelematic/ostree-basic-pkg.git;protocol=ssh;branch=master"

FILES_${PN} = " \
                ${bindir}/otbpkg \
              "

RDEPENDS_${PN} = "bash ostree tar"

do_install() {

  install -d ${D}${bindir}
  install -m 0755 ${S}/otbpkg ${D}${bindir}

}

