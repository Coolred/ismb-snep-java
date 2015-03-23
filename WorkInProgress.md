# Work In Progress #
This is just to let people know who is doing what at the moment, and things being worked on that won't be apparent and won't be checked in yet...

## Andrew ##
  * Creating an AbstractReader interface/abstract class that can be used by upper layers to send commands to whatever reader is currently selected, with no specific knowledge of APDU commands.
  * Implementing the ACS132U reader as a specific implementation of the AbstractReader
  * Separate Reader commands code from chipset commands code and implement a set of APDU commands for the PN532 chipset, that is then used by the ACS1322U Reader.
  * Try to make the P2P push to Android phone more reliable, it often fails for me and seems timign dependant.
  * Look into real implementations of upper levels as protocols, not just a specific interaction for this push (e.g. NFCIP)