# This project is a Java library developed with the aim of facilitating peer to peer communication over NFC. #

In particular, this library is useful to exchange data over **SNEP** (SIMPLE NDEF Exchange Protocol) between an ACS ACR-122 NFC reader and an NFC capable phone.

The library has already been tested on MAC OS and Windows 7 using an Android Samsung Galaxy Nexus device (with Android 4.0 - Ice Cream Sandwich).

I will test the library with other Android devices, Blackberry devices and Nokia Windows Phone devices.


If you are interested in **contributing to build a stable library**, please contact me at **lotito@ismb.it**.


---

## January 16, 2013 ##
I've received many e-mails and warnings about the interoperability with Samsung S3 devices.
I've studied the implementation of SNEP in the S3 device and I've tried to change the library point of view, passing from an **Initiator** approach to a **Target** approach, in both the communication flows (sending data from PC to Smartphone and receiving data from Smartphone to PC).
I've tested the updated library (using the Target Mode) with the following Android devices without problems :


- Samsung S3


- Samsung Galaxy Nexus


- LG Optimus L5


- Sony Xperia S


**Note** Excuse me for long absence and not to have replied to all your e-mails, but it has been a very overloaded period both from the working point of view (Preparation of Proposals for the European Commission - WP7 ; writing of scientific Papers and Patents; NFC-based research activities and so on) and the personal point of view (in December I became, for the first time, a parent and so I have "studied" for the most important and emotional work: the dad)


Guide is available at:
http://code.google.com/p/ismb-snep-java/wiki/SNEP_TARGET_MODE_SEND_RECEIVE


Source code is available at:
http://ismb-snep-java.googlecode.com/files/ISMB-SNEP-Library.zip



---

## August 30, 2012 ##

Now also the code to enable the communication flow from Smartphone to ACR-122 NFC reader is available

![http://ismb-snep-java.googlecode.com/files/August30_SNEP_from_smartphone.png](http://ismb-snep-java.googlecode.com/files/August30_SNEP_from_smartphone.png)

In particular, to test such code I've used an Android smartphone (LG Optimus L5- E610) and an NFC reader (Touchatag).


The **Downloads** section provides the code, running in Java SE:

http://ismb-snep-java.googlecode.com/files/ISMB-SNEP-LIBRARY_PhoneToReader_Java_Client_SRC.zip


The **Downloads** section provides also an example code running on Android:

source
http://ismb-snep-java.googlecode.com/files/SNEP_PhoneToReader_Android_Client_SRC.zip


installer
http://ismb-snep-java.googlecode.com/files/SNEP_PhoneToReader_Android_Client_APK.zip



The **Wiki** section provides a simple guide to use the above codes:

http://code.google.com/p/ismb-snep-java/wiki/SNEP_From_Smartphone_To_ACR122



---

**May 21, 2012**


The communication flow implemented is: from ACR-122 to Android device.

![http://ismb-snep-java.googlecode.com/files/May21_SNEP_to_Phone.png](http://ismb-snep-java.googlecode.com/files/May21_SNEP_to_Phone.png)



The **Downloads** section provides the code, running in Java SE:

http://ismb-snep-java.googlecode.com/files/ISMB-SNEP-LIBRARY%20FromReaderToPhone_source.zip


The **Downloads** section provides also an example code running on Android:

source : http://ismb-snep-java.googlecode.com/files/SnepReaderToPhoneSource.zip

installer: http://ismb-snep-java.googlecode.com/files/SnepReaderToPhone.apk.zip


The **Wiki** section provides a simple guide to use the above codes:

http://code.google.com/p/ismb-snep-java/wiki/SNEP_From_ACR122_To_Phone






---

If you are interested in a peer-to-peer solution using the **NPP** (NDEF PUSH PROTCOL), instead, you can try using the following library: http://code.google.com/p/ismb-npp-java/.

**N.B.** The NPP protocol is the only protocol available for P2P on Android 2.3.3 (Gingerbread)