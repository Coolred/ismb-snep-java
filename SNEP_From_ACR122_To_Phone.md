# Introduction #

In this wiki page, the test environment will be presented together with the logical steps necessary to test the SNEP (Simple NDEF Push Protocol) from the NFC reader (ACR-122) to the phone (Android Galaxy Nexus).


See which SampleProjects are included in the source code.

# Details #

**Test Environment**

![http://ismb-snep-java.googlecode.com/files/TestEnvironmentAndroid_site.jpg](http://ismb-snep-java.googlecode.com/files/TestEnvironmentAndroid_site.jpg)


**Requirements**

- Java and an IDE (e.g. Eclipse)


- ACS ACR12U2 PC/SC Driver,
available at http://www.acs.com.hk/index.php?pid=product&id=ACR122U


- if you're using Eclipse, modify settings in order to use javax.smartcard
as shown in the page http://code.google.com/p/ismb-npp-java/wiki/EclipseSettings4javax


- Download and install the Android app available in the download section.

source: http://ismb-snep-java.googlecode.com/files/SnepReaderToPhoneSource.zip

or

installer: http://ismb-snep-java.googlecode.com/files/SnepReaderToPhone.apk.zip



- Download the library source code available in the download section.

source: http://ismb-snep-java.googlecode.com/files/ISMB-SNEP-LIBRARY%20FromReaderToPhone_source.zip



**Steps**

**1) Execute the app installed on the Android device (ISMB-SNEP)**

... then wait until you'll see the following message:

![http://ismb-snep-java.googlecode.com/files/AndroidStartingSnepApp_site.jpg](http://ismb-snep-java.googlecode.com/files/AndroidStartingSnepApp_site.jpg)

**2) Execute the java code (SenderToPhone)** contained in the archive

http://ismb-snep-java.googlecode.com/files/ISMB-SNEP-LIBRARY%20FromReaderToPhone_source.zip


... then wait until in the console appears the following messages:

```
Get factory
Get terminals
Terminal name: ACS ACR122U 00 00
card: PC/SC card in ACS ACR122U 00 00, protocol T=0, state OK
Protocol:T=0
Called Procedure to Send data .. INITIATOR MODE
[DEBUG] {sending   [23 bytes]} 0xFF 0x00 0x00 0x00 0x12 0xD4 0x56 0x01 0x02 0x04 0x46 0x66 0x6D 0x01 0x01 0x10 0x03 0x02 0x00 0x01 0x04 0x01 0x96 
```

**3) Put the Android phone on the NFC reader**

![http://ismb-snep-java.googlecode.com/files/PuttingAndroid_site.jpg](http://ismb-snep-java.googlecode.com/files/PuttingAndroid_site.jpg)


**4) As soon as the phone will be on the NFC reader, the J2SE application will go on and the message will be sent to the Android device.**
N.B. Do not touch anything on the Android device and keep your fingers crossed

![http://ismb-snep-java.googlecode.com/files/Crossed_Fingers_50px.jpg](http://ismb-snep-java.googlecode.com/files/Crossed_Fingers_50px.jpg)


**4a) On the J2SE console the following messages will appear**

```
Get factory
Get terminals
Terminal name: ACS ACR122U 00 00
card: PC/SC card in ACS ACR122U 00 00, protocol T=0, state OK
Protocol:T=0
Called Procedure to Send data .. INITIATOR MODE
[DEBUG] {sending   [23 bytes]} 0xFF 0x00 0x00 0x00 0x12 0xD4 0x56 0x01 0x02 0x04 0x46 0x66 0x6D 0x01 0x01 0x10 0x03 0x02 0x00 0x01 0x04 0x01 0x96 
[DEBUG] {receiving [34 bytes]} 0xD5 0x57 0x00 0x01 0x21 0xAE 0x1F 0xD9 0x4D 0xEB 0xBE 0xF7 0x74 0xE8 0x00 0x00 0x00 0x0E 0x32 0x46 0x66 0x6D 0x01 0x01 0x10 0x03 0x02 0x00 0x01 0x04 0x01 0x96 0x90 0x00 
IN JUMP FOR DEP done..
[DEBUG] {sending   [10 bytes]} 0xFF 0x00 0x00 0x00 0x05 0xD4 0x40 0x01 0x11 0x20 
[DEBUG] {receiving [7 bytes]} 0xD5 0x41 0x00 0x00 0x00 0x90 0x00 
3 
[DEBUG] {sending   [10 bytes]} 0xFF 0x00 0x00 0x00 0x05 0xD4 0x40 0x01 0x11 0x20 
[DEBUG] {receiving [11 bytes]} 0xD5 0x41 0x00 0x81 0x84 0x02 0x02 0x00 0x78 0x90 0x00 
7 
CC RECEIVED 0x00 0x81 0x84 0x02 0x02 0x00 0x78 
[DEBUG] {sending   [105 bytes]} 0xFF 0x00 0x00 0x00 0x64 0xD4 0x40 0x01 0x13 0x20 0x00 0x10 0x02 0x00 0x00 0x00 0x58 0xD2 0x24 0x31 0x61 0x70 0x70 0x6C 0x69 0x63 0x61 0x74 0x69 0x6F 0x6E 0x2F 0x63 0x6F 0x6D 0x2E 0x65 0x78 0x61 0x6D 0x70 0x6C 0x65 0x2E 0x61 0x6E 0x64 0x72 0x6F 0x69 0x64 0x2E 0x62 0x65 0x61 0x6D 0x42 0x65 0x61 0x6D 0x20 0x72 0x65 0x63 0x65 0x69 0x76 0x65 0x64 0x20 0x66 0x72 0x6F 0x6D 0x20 0x49 0x53 0x4D 0x42 0x20 0x53 0x4E 0x45 0x50 0x20 0x4C 0x49 0x42 0x52 0x41 0x52 0x59 0x3A 0x0A 0x0A 0x67 0x6F 0x6F 0x64 0x20 0x6C 0x75 0x63 0x6B 0x21 
[DEBUG] {receiving [8 bytes]} 0xD5 0x41 0x00 0x83 0x44 0x01 0x90 0x00 
Finished
[DEBUG] {sending   [8 bytes]} 0xFF 0x00 0x00 0x00 0x03 0xD4 0x52 0x01 
[DEBUG] {receiving [5 bytes]} 0xD5 0x53 0x00 0x90 0x00 
```

**4b) And on the Android device, instead, the message sent will be displayed**

![http://ismb-snep-java.googlecode.com/files/DataReceivedAndroid_site.jpg](http://ismb-snep-java.googlecode.com/files/DataReceivedAndroid_site.jpg)


**5) That's all**

![http://ismb-snep-java.googlecode.com/files/50px-Done.png](http://ismb-snep-java.googlecode.com/files/50px-Done.png)


With this example you have used the ISMB-SNEP-LIBRARY to send to the Android device the string:

Beam received from ISMB SNEP LIBRARY:

good luck!



**Please consider this first release of the application as a Proof of Concept and not as a fully defined and bug free application. Obviously, it's not yet STABLE.**



If you're interested in contributing to build a stable library, please contact me at **lotito@ismb.it**.