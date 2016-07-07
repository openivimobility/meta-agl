SUMMARY = "Tool for managing bootable, immutable, versioned filesystem trees"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI = "git://github.com/openivimobility/ostree.git;protocol=ssh;branch=master"
SRCREV="cea824293732300f268fd915812af77342afbd40"

S = "${WORKDIR}/git"

DEPENDS += "attr libarchive glib-2.0 pkgconfig gpgme libgsystem fuse libsoup-2.4"

RDEPENDS_${PN} = "python libsystemd util-linux-libuuid util-linux-libblkid util-linux-libmount libcap liblzma"

inherit autotools-brokensep

EXTRA_OECONF = "--with-libarchive --disable-gtk-doc --disable-gtk-doc-html --disable-gtk-doc-pdf --disable-man"

FILES_${PN} += "${libdir}/ostree/ ${libdir}/ostbuild"

BBCLASSEXTEND = "native"

do_configure() {
 NOCONFIGURE=true ./autogen.sh
 oe_runconf
}
