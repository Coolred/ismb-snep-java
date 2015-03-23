# Hardware and APDU Documentation #

If you want to understand in more details the commands this library sends to the NFC Controller chip, or contribute to the library, you will probably need to refer to some of these documents that describe how the controller and the underlying chipset works.

At the moment the library only works with the ACR122U USB Reader/Writer from ACS.

See the [Downloads](http://code.google.com/p/ismb-snep-java/downloads/list) section for documents on the reader and the chipset it uses.

I have uploaded two (very similar) versions of the ACR112U API document:
  * [API\_ACR122.pdf](http://code.google.com/p/ismb-snep-java/downloads/detail?name=API_ACR122.pdf)
  * [API\_ACR122-SAM\_33.pdf](http://code.google.com/p/ismb-snep-java/downloads/detail?name=API_ACR122-SAM_33.pdf)

# ACR122U #
This USB reader is well supported by host operating systems. Windows, Linux and Mac OS X all come bundled with the PC/SC (PC Smart Card) daemon that connects to the reader in the background using a CCID driver that also comes bundled.

Hence you shouldn't have to install anything extra to start any special process to get this library talking to your reader/writer.

This reader/writer uses the PN532 NFC Controller chip from NXP.

This library sends commands (APDU) to the reader, some of which are destined for the reader itself and it's own functionality (e.g. control the state of the LEDs, read it's ID or firmware version etc). These commands are documented in the Reader API document.

Other commands are forwarded to the PN532 chipset, and are specific to the chipset used, not the reader/writer.

# PN532 #
The overall operation of the NFC Controller and its APDU are documented in the PN532 API documents in the Download section.

There I have uploaded the following two documents. The User Manual should cover most needs. The application note is more detailed and hardware oriented for people designing in the chipset but does also cover the APDU somewhat, although it references the User Manual.
  * [NFC\_PN532\_141520\_spec.pdf (User Manual)](http://code.google.com/p/ismb-snep-java/downloads/detail?name=NFC_PN532_141520_spec.pdf)
  * [Note\_v1.2.pdf PN532C106\_Application Note\_v1.2.pdf (Application Note)](http://code.google.com/p/ismb-snep-java/downloads/detail?name=PN532C106_Application)