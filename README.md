# Sample implementation of the Bosch Rexroth Open Protocol

This is a sample implementation of the Bosch Rexroth Open Protocol with selected commands to communicate with Nexo devices

## Bosch Rexroth Open protocol

The Rexroth Open Protocol is a communication protocol based on TCP/IP that has been specially developed
for tightening controllers. Up to 5 clients connected to each other can be managed using the Open
Protocol.. Users can configure a tightening controller and get information on events in the tightening controller
via the client.

### Implementation state

| MID         | Description | Available  | Class |
| ------------- | ------------- | ----- | ----- |
| MID  0001 | Communication start | 1.0.0 | [comm.CommunicationStartMessage](src\main\java\com\microsoft\samples\nexo\openprotocol\impl\comm\CommunicationStartMessage.java)
| MID  0002 | Acknowledgement of communication start | 1.0.0 | [comm.CommunicationStartReply](src\main\java\com\microsoft\samples\nexo\openprotocol\impl\comm\CommunicationStartReply.java)
| MID  0003 | Communication stop | 1.0.0 | [comm.CommunicationStopMessage](src\main\java\com\microsoft\samples\nexo\openprotocol\impl\comm\CommunicationStopMessage.java)
| MID  0004 | Command error | 1.0.0 | [CommandErrorMessage](src\main\java\com\microsoft\samples\nexo\openprotocol\impl\CommandErrorMessage.java)
| MID  0005 | Command accepted | 1.0.0 | [CommandAcceptedMessage](src\main\java\com\microsoft\samples\nexo\openprotocol\impl\CommandAcceptedMessage.java)
| MID  0010 | Tightening program numbers upload request | 1.0.0
| MID  0011 | Tightening program numbers upload response | 1.0.0
| MID  0012 | Tightening program data upload request | n.a.
| MID  0013 | Tightening program data upload response | n.a.
| MID  0014 | Tightening program subscription selected | n.a.
| MID  0015 | Tightening program selected | n.a.
| MID  0016 | Tightening program selected acknowledgement | n.a.
| MID  0017 | Cancel tightening program selected subscription | n.a.
| MID  0018 | Select tightening program | n.a.
| MID  0019 | Make the presetting for OK/NOK counter | n.a.
| MID  0020 | Reset the OK/NOK counter | n.a.
| MID  0021 | Deactivate the OK/NOK counter | n.a.
| MID  0030 | OK counter upload request | n.a.
| MID  0031 | OK counter upload response | n.a.
| MID  0040 | Tool data upload request | n.a.
| MID  0041 | Tool data upload | n.a.
| MID  0042 | Deactivate tool | n.a.
| MID  0043 | Activate tool | n.a.
| MID  00451) | Define calibration value request | n.a.
| MID  0050 | ID code download request | n.a.
| MID  0150 | ID code download request | n.a.
| MID  0051 | ID code upload subscription | n.a.
| MID  0052 | Upload ID code | n.a.
| MID  0053 | Upload ID code acknowledgement | n.a.
| MID  0054 | Cancel upload ID code subscription | n.a.
| MID  0060 | Last tightening results data subscription | n.a.
| MID  0061 | Upload last tightening results data response | n.a.
| MID  0062 | Last tightening results data acknowledgement | n.a.
| MID  0063 | Cancel last tightening results data | n.a.
| MID  0064 | Archived tightening results upload request | n.a.
| MID  0065 | Archived tightening results response | n.a.
| MID  0070 | Resulting system errors subscription | n.a.
| MID  0071 | Upload resulting system errors | n.a.
| MID  0072 | Upload system errors acknowledgement | n.a.
| MID  0073 | Cancel system errors subscription | n.a.
| MID  0074 | System error in tightening controller acknowledged | n.a.
| MID  0075 | Acknowledgement System error in tightening controller acknowledged | n.a.
| MID  0076 | System error status | n.a.
| MID  0077 | System error status acknowledgement | n.a.
| MID  0078 | Acknowledge system error in tightening controller | n.a.
| MID  0080 | Time on the tightening controller request | n.a.
| MID 0081 | Upload time | n.a.
| MID 0082 | Set the time in the tightening controller | n.a.
| MID 0111 | Message on the graphical display of the nutrunner | n.a.
| MID 01272) | Aborts a job | n.a.
| MID 0400 | Activate automatic/manual mode | n.a.
| MID 0401 | Upload automatic/manual mode | n.a.
| MID 0402 | Upload automatic/manual mode acknowledgement | n.a.
| MID 0403 | Deactivate automatic/manual mode | n.a.
| MID 04043) | Select automatic/manual mode | n.a.
| MID 04102) | AutoDisable setting request | n.a.
| MID 04112) | AutoDisable setting response | n.a.
| MID 0500 | Subscription Output signal change | n.a.
| MID 0501 | Upload Output signal change | n.a.
| MID 0502 | Acknowledgement Upload output signal change | n.a.
| MID 0503 | Cancel Output signal change | n.a.
| MID 0504 | Change value of input signals | n.a.
| MID 05701) | Activate job | n.a.
| MID 05711) | Start job sequence | n.a.
| MID 05731) | Select job number | n.a.
| MID 08001) | Battery level request | 1.0.0
| MID 08011) | Battery level response | 1.0.0
| MID 08021) | Battery level changes subscription | n.a.
| MID 08031) | Battery level changes upload | n.a.
| MID 08041) | Cancel battery level changes subscription | n.a.
| MID 08051) | Reception quality request | n.a.
| MID 08061) | Reception quality response | n.a.
| MID 08071) | Reception quality change subscription | n.a.
| MID 08081) | Reception quality change upload | n.a.
| MID 08091) | Cancel reception quality change subscription | n.a.
| MID 9999 | Keep alive message | 1.0.0
