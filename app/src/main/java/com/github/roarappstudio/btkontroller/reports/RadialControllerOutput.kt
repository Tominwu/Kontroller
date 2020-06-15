package com.github.roarappstudio.btkontroller.reports
/*
Haptic feedback collection (optional)
If a Windows radial controller device supports haptic feedback, it can allow the system and applications to take advantage of it by including a haptic feedback collection (Page 0x0E, Usage 0x01) within the Windows radial controller TLC. For more information on how the HID specification supports haptic feedback, please consult the Haptics page ratification to the HID specification.

The host uses the following usages in an output report (through the haptic feedback collection) to allow the host to issue haptic feedback events to the Windows radial controller device. If a device chooses to expose a haptic feedback collection, some usages are mandatory to allow host-initiated haptic feedback to be supported.

Member	Description	Page	ID	Mandatory/Optional
Manual Trigger	Waveform to fire as explicit command from the host.	0x0E	0x21	Mandatory
Intensity	Output – Intensity of Manual Trigger waveform as a percentage	0x0E	0x23	Optional
Repeat Count	Output – Number of times to play Manual Trigger waveform after initial play	0x0E	0x24	Optional
Retrigger Period	Output – Duration of time to wait before retriggering Manual Trigger when repeating


The class only has the Mandatory function
*/
class RadialControllerOutput {
}