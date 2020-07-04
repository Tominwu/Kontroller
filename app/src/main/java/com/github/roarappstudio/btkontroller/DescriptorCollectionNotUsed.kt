package com.github.roarappstudio.btkontroller

object DescriptorCollectionNotUsed {


    //Windows radial controller SAMPLE (Only Mandatory)
    val RADIAL_INPUT_M = byteArrayOf(
        // Integrated Radial Controller TLC
        0x05.toByte(), 0x01.toByte(),     // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x0e.toByte(),     // USAGE (System Multi-Axis Controller)
        0xa1.toByte(), 0x01.toByte(),     // COLLECTION (Application)
        0x85.toByte(), 0x01.toByte(),     //   REPORT_ID (Radial Controller)
        0x05.toByte(), 0x0d.toByte(),     //   USAGE_PAGE (Digitizers)
        0x09.toByte(), 0x21.toByte(),     //   USAGE (Puck)
        0xa1.toByte(), 0x00.toByte(),     //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),     //     USAGE_PAGE (Buttons)
        0x09.toByte(), 0x01.toByte(),     //     USAGE (Button 1)
        0x95.toByte(), 0x01.toByte(),     //     REPORT_COUNT (1)
        0x75.toByte(), 0x01.toByte(),     //     REPORT_SIZE (1)
        0x15.toByte(), 0x00.toByte(),     //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),     //     LOGICAL_MAXIMUM (1)
        0x81.toByte(), 0x02.toByte(),     //     INPUT (Data,Var,Abs)
        0x05.toByte(), 0x01.toByte(),     //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x37.toByte(),     //     USAGE (Dial)
        0x95.toByte(), 0x01.toByte(),     //     REPORT_COUNT (1)
        0x75.toByte(), 0x0f.toByte(),     //     REPORT_SIZE (15)
        0x55.toByte(), 0x0f.toByte(),     //     UNIT_EXPONENT (-1)
        0x65.toByte(), 0x14.toByte(),       //     UNIT (Degrees, English Rotation)
        0x36.toByte(), 0xf0.toByte(), 0xf1.toByte(),   //     PHYSICAL_MINIMUM (-3600)
        0x46.toByte(), 0x10.toByte(), 0x0e.toByte(),   //     PHYSICAL_MAXIMUM (3600)
        0x16.toByte(), 0xf0.toByte(), 0xf1.toByte(),   //     LOGICAL_MINIMUM (-3600)
        0x26.toByte(), 0x10.toByte(), 0x0e.toByte(),   //     LOGICAL_MAXIMUM (3600)
        0x81.toByte(), 0x06.toByte(),     //     INPUT (Data,Var,Rel)
        0xc0.toByte()  ,         //   END_COLLECTION
        0xc0.toByte()          // END_COLLECTION
    )// End





    //Windows radial controller SAMPLE
    val RADIAL_INPUT = byteArrayOf(
        // Integrated Radial Controller TLC
        0x05.toByte(), 0x01.toByte(),     // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x0e.toByte(),     // USAGE (System Multi-Axis Controller)
        0xa1.toByte(), 0x01.toByte(),     // COLLECTION (Application)
        0x85.toByte(), 0x01.toByte(),     //   REPORT_ID (Radial Controller)
        0x05.toByte(), 0x0d.toByte(),     //   USAGE_PAGE (Digitizers)
        0x09.toByte(), 0x21.toByte(),     //   USAGE (Puck)
        0xa1.toByte(), 0x00.toByte(),     //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),     //     USAGE_PAGE (Buttons)
        0x09.toByte(), 0x01.toByte(),     //     USAGE (Button 1)
        0x95.toByte(), 0x01.toByte(),     //     REPORT_COUNT (1)
        0x75.toByte(), 0x01.toByte(),     //     REPORT_SIZE (1)
        0x15.toByte(), 0x00.toByte(),     //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),     //     LOGICAL_MAXIMUM (1)
        0x81.toByte(), 0x02.toByte(),     //     INPUT (Data,Var,Abs)
        0x05.toByte(), 0x01.toByte(),     //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x37.toByte(),     //     USAGE (Dial)
        0x95.toByte(), 0x01.toByte(),     //     REPORT_COUNT (1)
        0x75.toByte(), 0x0f.toByte(),     //     REPORT_SIZE (15)
        0x55.toByte(), 0x0f.toByte(),     //     UNIT_EXPONENT (-1)
        0x65.toByte(), 0x14.toByte(),       //     UNIT (Degrees, English Rotation)
        0x36.toByte(), 0xf0.toByte(), 0xf1.toByte(),   //     PHYSICAL_MINIMUM (-3600)
        0x46.toByte(), 0x10.toByte(), 0x0e.toByte(),   //     PHYSICAL_MAXIMUM (3600)
        0x16.toByte(), 0xf0.toByte(), 0xf1.toByte(),   //     LOGICAL_MINIMUM (-3600)
        0x26.toByte(), 0x10.toByte(), 0x0e.toByte(),   //     LOGICAL_MAXIMUM (3600)
        0x81.toByte(), 0x06.toByte(),     //     INPUT (Data,Var,Rel)
        0x09.toByte(), 0x30.toByte(),     //     USAGE (X)
        0x75.toByte(), 0x10.toByte(),     //     REPORT_SIZE (16)
        0x55.toByte(), 0x0d.toByte(),     //     UNIT_EXPONENT (-3)
        0x65.toByte(), 0x13.toByte(),     //     UNIT (Inch,EngLinear)
        0x35.toByte(), 0x00.toByte(),     //     PHYSICAL_MINIMUM (0)
        0x46.toByte(), 0xc0.toByte(), 0x5d.toByte(),   //     PHYSICAL_MAXIMUM (24000)
        0x15.toByte(), 0x00.toByte(),     //     LOGICAL_MINIMUM (0)
        0x26.toByte(), 0xff.toByte(), 0x7f.toByte(),   //     LOGICAL_MAXIMUM (32767)
        0x81.toByte(), 0x02.toByte(),     //     INPUT (Data,Var,Abs)
        0x09.toByte(), 0x31.toByte(),     //     USAGE (Y)
        0x46.toByte(), 0xb0.toByte(), 0x36.toByte(),   //     PHYSICAL_MAXIMUM (14000)
        0x81.toByte(), 0x02.toByte(),     //     INPUT (Data,Var,Abs)
        0x05.toByte(), 0x0d.toByte(),     //     USAGE_PAGE (Digitizers)
        0x09.toByte(), 0x48.toByte(),     //     USAGE (Width)
        0x36.toByte(), 0xb8.toByte(), 0x0b.toByte(),   //     PHYSICAL_MINIMUM (3000)
        0x46.toByte(), 0xb8.toByte(), 0x0b.toByte(),   //     PHYSICAL_MAXIMUM (3000)
        0x16.toByte(), 0xb8.toByte(), 0x0b.toByte(),   //     LOGICAL_MINIMUM (3000)
        0x26.toByte(), 0xb8.toByte(), 0x0b.toByte(),   //     LOGICAL_MAXIMUM (3000)
        0x81.toByte(), 0x03.toByte(),      //     INPUT (Cnst,Var,Abs)
        0xc0.toByte()  ,         //   END_COLLECTION
        0xc0.toByte()          // END_COLLECTION
    )// End


    //Windows radial controller SAMPLE
    val RADIAL_OUTPUT = byteArrayOf(
        0x85.toByte(), 0x02.toByte(),            // REPORT_ID (Haptic Feedback)
        0x05.toByte(), 0x0e.toByte(),            // USAGE_PAGE (Haptics)
        0x09.toByte(), 0x01.toByte(),            // USAGE (Simple Haptic Controller)
        0xa1.toByte(), 0x02.toByte(),            // COLLECTION (Logical)
        0x09.toByte(), 0x20.toByte(),            //   USAGE (Auto Trigger)
        0x16.toByte(), 0x00.toByte(), 0x10.toByte(),        //   LOGICAL_MINIMUM (0x1000)
        0x26.toByte(), 0x04.toByte(), 0x10.toByte(),        //   LOGICAL_MAXIMUM (0x1004)
        0x95.toByte(), 0x01.toByte(),            //   REPORT_COUNT (1)
        0x75.toByte(), 0x10.toByte(),            //   REPORT_SIZE (16)
        0xb1.toByte(), 0x02.toByte(),            //   FEATURE (Data,Var,Abs)
        0x09.toByte(), 0x21.toByte(),            //   USAGE (Manual Trigger)
        0x91.toByte(), 0x02.toByte(),            //   OUTPUT (Data,Var,Abs)
        0x09.toByte(), 0x22.toByte(),            //   USAGE (Auto Trigger Associated Control)
        0x17.toByte(), 0x37.toByte(), 0x00.toByte(), 0x01.toByte(), 0x00.toByte(),     //   LOGICAL_MINIMUM (0x00010037)
        0x27.toByte(), 0x37.toByte(), 0x00.toByte(), 0x01.toByte(), 0x00.toByte(),    //   LOGICAL_MAXIMUM (0x00010037)
        0x95.toByte(), 0x01.toByte(),            //   REPORT_COUNT (1)
        0x75.toByte(), 0x20.toByte(),            //   REPORT_SIZE (32)
        0xb1.toByte(), 0x03.toByte(),            //   FEATURE (Cnst.toByte(),Var,Abs)
        0x09.toByte(), 0x23.toByte(),            //   USAGE (Intensity)
        0x15.toByte(), 0x00.toByte(),            //   LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x7f.toByte(),            //   LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),            //   REPORT_SIZE (8)
        0x91.toByte(), 0x02.toByte(),            //   OUTPUT (Data,Var,Abs)
        0x09.toByte(), 0x23.toByte(),            //   USAGE (Intensity)
        0xb1.toByte(), 0x02.toByte(),            //   FEATURE (Data,Var,Abs)
        0x09.toByte(), 0x24.toByte(),            //   USAGE (Repeat Count)
        0x91.toByte(), 0x02.toByte(),            //   OUTPUT (Data,Var,Abs)
        0x09.toByte(), 0x24.toByte(),            //   USAGE (Repeat Count)
        0xb1.toByte(), 0x02.toByte(),            //   FEATURE (Data,Var,Abs)
        0x09.toByte(), 0x25.toByte(),            //   USAGE (Retrigger Period)
        0x91.toByte(), 0x02.toByte(),            //   OUTPUT (Data,Var,Abs)
        0x09.toByte(), 0x25.toByte(),            //   USAGE (Retrigger Period)
        0xb1.toByte(), 0x02.toByte(),            //   FEATURE (Data,Var,Abs)
        0x09.toByte(), 0x28.toByte(),            //   USAGE (Waveform Cutoff Time)
        0x26.toByte(), 0xff.toByte(), 0x7f.toByte(),        //   LOGICAL_MAXIMUM (32.toByte(),767)
        0x75.toByte(), 0x10.toByte(),            //   REPORT_SIZE (16)
        0xb1.toByte(), 0x02.toByte(),            //   FEATURE (Data,Var,Abs)
        0x05.toByte(), 0x0e.toByte(),            //   USAGE_PAGE (Haptics)
        0x09.toByte(), 0x10.toByte(),            //   USAGE (Waveform List)
        0xa1.toByte(), 0x02.toByte(),            //   COLLECTION (Logical)
        0x05.toByte(), 0x0A.toByte(),            //     USAGE_PAGE (Ordinal)
        0x09.toByte(), 0x03.toByte(),            //     USAGE (Ordinal 3)
        0x95.toByte(), 0x01.toByte(),            //     REPORT_COUNT (1)
        0x75.toByte(), 0x08.toByte(),            //     REPORT_SIZE (8)
        0x15.toByte(), 0x03.toByte(),            //     LOGICAL_MINIMUM (3)
        0x25.toByte(), 0x03.toByte(),            //     LOGICAL_MAXIMUM (3)
        0x36.toByte(), 0x03.toByte(), 0x10.toByte(),        //     PHYSICAL_MINIMUM (0x1003)
        0x46.toByte(), 0x03.toByte(), 0x10.toByte(),        //     PHYSICAL_MAXIMUM (0x1003)
        0xb1.toByte(), 0x03.toByte(),            //     FEATURE (Cnst,Var,Abs)
        0x09.toByte(), 0x04.toByte(),             //     USAGE (Ordinal 4)
        0x15.toByte(), 0x04.toByte(),             //     LOGICAL_MINIMUM (4)
        0x25.toByte(), 0x04.toByte(),            //     LOGICAL_MAXIMUM (4)
        0x36.toByte(), 0x04.toByte(), 0x10.toByte(),        //     PHYSICAL_MINIMUM (0x1004)
        0x46.toByte(), 0x04.toByte(), 0x10.toByte(),        //     PHYSICAL_MAXIMUM (0x1004)
        0xb1.toByte(), 0x03.toByte(),            //     FEATURE (Cnst,Var,Abs)
        0xc0.toByte(),                //   END_COLLECTION
        0x05.toByte(), 0x0e.toByte(),            //   USAGE_PAGE (Haptics)
        0x09.toByte(), 0x11.toByte(),            //   USAGE (Duration List)
        0xa1.toByte(), 0x02.toByte(),            //   COLLECTION (Logical)
        0x05.toByte(), 0x0a.toByte(),            //     USAGE_PAGE (Ordinal)
        0x09.toByte(), 0x03.toByte(),            //     USAGE (Ordinal 3)
        0x09.toByte(), 0x04.toByte(),            //     USAGE (Ordinal 4)
        0x15.toByte(), 0x00.toByte(),            //     LOGICAL_MINIMUM (0)
        0x26.toByte(), 0xff.toByte(), 0x0f.toByte(),        //     LOGICAL_MAXIMUM (4095)
        0x95.toByte(), 0x02.toByte(),            //     REPORT_COUNT (2)
        0x75.toByte(), 0x08.toByte(),            //     REPORT_SIZE (8)
        0xb1.toByte(), 0x02.toByte(),            //     FEATURE (Data,Var,Abs)
        0xc0 .toByte()                //   END_COLLECTION
    )// End

    val MOUSE_KEYBOARD_COMBO = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)

        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)

        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x16.toByte(), 0x01.toByte(),0xf8.toByte(),                         //     LOGICAL_MINIMUM (-2047)
        0x26.toByte(), 0xff.toByte(),0x07.toByte(),                         //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x10.toByte(),                         //     REPORT_SIZE (16)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)

        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)


        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        //0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x38.toByte(),        //         USAGE (Wheel)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION

        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)

        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x04.toByte(),        //         REPORT_SIZE (4)
        0xb1.toByte(), 0x03.toByte(),        //         FEATURE (Cnst,Var,Abs)



        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x05.toByte(), 0x0c.toByte(),        //         USAGE_PAGE (Consumer Devices)
        0x0a.toByte(), 0x38.toByte(), 0x02.toByte(),  //         USAGE (AC Pan)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),        //         INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION
        0xc0.toByte(),              //       END_COLLECTION

        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte(),                                //END_COLLECTION

        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)

        0x09.toByte(), 0x06.toByte(),                         // Usage (Keyboard)
        0xA1.toByte(), 0x01.toByte(),                         // Collection (Application)
        0x85.toByte(), 0x08.toByte(),                           //   REPORT_ID (Keyboard)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key Codes)
        0x19.toByte(), 0xe0.toByte(),                         //     Usage Minimum (224)
        0x29.toByte(), 0xe7.toByte(),                         //     Usage Maximum (231)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x01.toByte(),                         //     Logical Maximum (1)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x95.toByte(), 0x08.toByte(),                         //     Report Count (8)
        0x81.toByte(), 0x02.toByte(),                         //     Input (Data, Variable, Absolute)

        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x81.toByte(), 0x01.toByte(),                         //     Input (Constant) reserved byte(1)



        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x65.toByte(),                         //     Logical Maximum (101)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key codes)
        0x19.toByte(), 0x00.toByte(),                         //     Usage Minimum (0)
        0x29.toByte(), 0x65.toByte(),                         //     Usage Maximum (101)
        0x81.toByte(), 0x00.toByte(),                         //     Input (Data, Array) Key array(6 bytes)
        0xc0.toByte()                               // End Collection (Application)

    )


    val featurerr = byteArrayOf(

        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)

        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)




        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)
        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x04.toByte(),        //         REPORT_SIZE (4)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)
        0xb1.toByte(), 0x03.toByte(),        //         FEATURE (Cnst,Var,Abs)
        0xc0.toByte(),                                //END_COLLECTION

        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)

        0x09.toByte(), 0x06.toByte(),                         // Usage (Keyboard)
        0xA1.toByte(), 0x01.toByte(),                         // Collection (Application)
        0x85.toByte(), 0x08.toByte(),                           //   REPORT_ID (Keyboard)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key Codes)
        0x19.toByte(), 0xe0.toByte(),                         //     Usage Minimum (224)
        0x29.toByte(), 0xe7.toByte(),                         //     Usage Maximum (231)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x01.toByte(),                         //     Logical Maximum (1)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x95.toByte(), 0x08.toByte(),                         //     Report Count (8)
        0x81.toByte(), 0x02.toByte(),                         //     Input (Data, Variable, Absolute)

        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x81.toByte(), 0x01.toByte(),                         //     Input (Constant) reserved byte(1)



        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x65.toByte(),                         //     Logical Maximum (101)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key codes)
        0x19.toByte(), 0x00.toByte(),                         //     Usage Minimum (0)
        0x29.toByte(), 0x65.toByte(),                         //     Usage Maximum (101)
        0x81.toByte(), 0x00.toByte(),                         //     Input (Data, Array) Key array(6 bytes)
        0xc0.toByte()                               // End Collection (Application)




    )





    val KEYBOARD_MODIFIED = byteArrayOf(

        0x05.toByte(), 0x01.toByte(),                         // Usage Page (Generic Desktop)
        0x09.toByte(), 0x06.toByte(),                         // Usage (Keyboard)
        0xA1.toByte(), 0x01.toByte(),                         // Collection (Application)
        0x85.toByte(), 0x08.toByte(),                           //   REPORT_ID (Keyboard)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key Codes)
        0x19.toByte(), 0xe0.toByte(),                         //     Usage Minimum (224)
        0x29.toByte(), 0xe7.toByte(),                         //     Usage Maximum (231)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x01.toByte(),                         //     Logical Maximum (1)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x95.toByte(), 0x08.toByte(),                         //     Report Count (8)
        0x81.toByte(), 0x02.toByte(),                         //     Input (Data, Variable, Absolute)

        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x81.toByte(), 0x01.toByte(),                         //     Input (Constant) reserved byte(1)



        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x65.toByte(),                         //     Logical Maximum (101)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key codes)
        0x19.toByte(), 0x00.toByte(),                         //     Usage Minimum (0)
        0x29.toByte(), 0x65.toByte(),                         //     Usage Maximum (101)
        0x81.toByte(), 0x00.toByte(),                         //     Input (Data, Array) Key array(6 bytes)
        0xC0.toByte()                                // End Collection (Application)
    )

    val KEYBOARD = byteArrayOf(

        0x05.toByte(), 0x01.toByte(),                         // Usage Page (Generic Desktop)
        0x09.toByte(), 0x06.toByte(),                         // Usage (Keyboard)
        0xA1.toByte(), 0x01.toByte(),                         // Collection (Application)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key Codes)
        0x19.toByte(), 0xe0.toByte(),                         //     Usage Minimum (224)
        0x29.toByte(), 0xe7.toByte(),                         //     Usage Maximum (231)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x01.toByte(),                         //     Logical Maximum (1)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x95.toByte(), 0x08.toByte(),                         //     Report Count (8)
        0x81.toByte(), 0x02.toByte(),                         //     Input (Data, Variable, Absolute)

        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x81.toByte(), 0x01.toByte(),                         //     Input (Constant) reserved byte(1)

        0x95.toByte(), 0x05.toByte(),                         //     Report Count (5)
        0x75.toByte(), 0x01.toByte(),                         //     Report Size (1)
        0x05.toByte(), 0x08.toByte(),                         //     Usage Page (Page# for LEDs)
        0x19.toByte(), 0x01.toByte(),                         //     Usage Minimum (1)
        0x29.toByte(), 0x05.toByte(),                         //     Usage Maximum (5)
        0x91.toByte(), 0x02.toByte(),                         //     Output (Data, Variable, Absolute), Led report
        0x95.toByte(), 0x01.toByte(),                         //     Report Count (1)
        0x75.toByte(), 0x03.toByte(),                         //     Report Size (3)
        0x91.toByte(), 0x01.toByte(),                         //     Output (Data, Variable, Absolute), Led report padding

        0x95.toByte(), 0x06.toByte(),                         //     Report Count (6)
        0x75.toByte(), 0x08.toByte(),                         //     Report Size (8)
        0x15.toByte(), 0x00.toByte(),                         //     Logical Minimum (0)
        0x25.toByte(), 0x65.toByte(),                         //     Logical Maximum (101)
        0x05.toByte(), 0x07.toByte(),                         //     Usage Page (Key codes)
        0x19.toByte(), 0x00.toByte(),                         //     Usage Minimum (0)
        0x29.toByte(), 0x65.toByte(),                         //     Usage Maximum (101)
        0x81.toByte(), 0x00.toByte(),                         //     Input (Data, Array) Key array(6 bytes)
        0xC0.toByte()                                // End Collection (Application)
    )



    val MOUSE_RELATIVE1 = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x15.toByte(), 0x01.toByte(),0xf8.toByte(),                         //     LOGICAL_MINIMUM (-2047)
        0x25.toByte(), 0xff.toByte(),0x07.toByte(),                         //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x0c.toByte(),                         //     REPORT_SIZE (12)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte()                                //END_COLLECTION
    )

    val MOUSE_RELATIVE_WITHOUT_SCROLL = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x16.toByte(), 0x01.toByte(),0xf8.toByte(),                         //     LOGICAL_MINIMUM (-2047)
        0x26.toByte(), 0xff.toByte(),0x07.toByte(),                         //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x10.toByte(),                         //     REPORT_SIZE (16)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte()                                //END_COLLECTION
    )


    val MOUSE_RELATIVE_WITH_SCROLL = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)

        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)

        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x16.toByte(), 0x01.toByte(),0xf8.toByte(),                         //     LOGICAL_MINIMUM (-2047)
        0x26.toByte(), 0xff.toByte(),0x07.toByte(),                         //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x10.toByte(),                         //     REPORT_SIZE (16)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)

        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)


        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        //0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x38.toByte(),        //         USAGE (Wheel)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION

        0xa1.toByte(), 0x02.toByte(),        //       COLLECTION (Logical)
        0x85.toByte(), 0x06.toByte(),               //   REPORT_ID (Feature)
        0x09.toByte(), 0x48.toByte(),        //         USAGE (Resolution Multiplier)

        0x15.toByte(), 0x00.toByte(),        //         LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),        //         LOGICAL_MAXIMUM (1)
        0x35.toByte(), 0x01.toByte(),        //         PHYSICAL_MINIMUM (1)
        0x45.toByte(), 0x04.toByte(),        //         PHYSICAL_MAXIMUM (4)
        0x75.toByte(), 0x02.toByte(),        //         REPORT_SIZE (2)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)

        0xb1.toByte(), 0x02.toByte(),        //         FEATURE (Data,Var,Abs)

        0x35.toByte(), 0x00.toByte(),        //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),        //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x04.toByte(),        //         REPORT_SIZE (4)
        0xb1.toByte(), 0x03.toByte(),        //         FEATURE (Cnst,Var,Abs)



        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x05.toByte(), 0x0c.toByte(),        //         USAGE_PAGE (Consumer Devices)
        0x0a.toByte(), 0x38.toByte(), 0x02.toByte(),  //         USAGE (AC Pan)

        0x15.toByte(), 0x81.toByte(),        //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),        //         LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),        //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),        //         REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),        //         INPUT (Data,Var,Rel)
        0xc0.toByte(),              //       END_COLLECTION
        0xc0.toByte(),              //       END_COLLECTION

        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte()                                //END_COLLECTION

    )


    val MOUSE_RELATIVE_WITH_SCROLL_NOTSMOOTH = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x85.toByte(), 0x04.toByte(),                         //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x16.toByte(), 0x01.toByte(),0xf8.toByte(),           //     LOGICAL_MINIMUM (-2047)
        0x26.toByte(), 0xff.toByte(),0x07.toByte(),           //     LOGICAL_MAXIMUM (2047)
        0x75.toByte(), 0x10.toByte(),                         //     REPORT_SIZE (16)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x38.toByte(),                        //      USAGE (Wheel)
        0x15.toByte(), 0x81.toByte(),                       //       LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),                       //       LOGICAL_MAXIMUM (127)
        0x35.toByte(), 0x00.toByte(),                     //         PHYSICAL_MINIMUM (0)        - reset physical
        0x45.toByte(), 0x00.toByte(),                     //         PHYSICAL_MAXIMUM (0)
        0x75.toByte(), 0x08.toByte(),                     //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)


        0x05.toByte(), 0x0c.toByte(),                     //         USAGE_PAGE (Consumer Devices)
        0x0a.toByte(), 0x38.toByte(), 0x02.toByte(),      //         USAGE (AC Pan)
        0x15.toByte(), 0x81.toByte(),                     //         LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),                     //         LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),                     //         REPORT_SIZE (8)
        0x95.toByte(), 0x01.toByte(),                     //         REPORT_COUNT (1)
        0x81.toByte(), 0x06.toByte(),                     //         INPUT (Data,Var,Rel)

        0xc0.toByte(),                                        //   END_COLLECTION
        0xc0.toByte()                                          //END_COLLECTION
    )

    val MOUSE_RELATIVE = byteArrayOf(
        //MOUSE TLC
        0x05.toByte(), 0x01.toByte(),                         // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                         // USAGE (Mouse)
        0xa1.toByte(), 0x01.toByte(),                         // COLLECTION (Application)
        0x85.toByte(), 0x04.toByte(),               //   REPORT_ID (Mouse)
        0x09.toByte(), 0x01.toByte(),                         //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                         //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                         //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                         //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x02.toByte(),                         //     USAGE_MAXIMUM (Button 2)
        0x15.toByte(), 0x00.toByte(),                         //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                         //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                         //     REPORT_SIZE (1)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x02.toByte(),                         //     INPUT (Data,Var,Abs)
        0x95.toByte(), 0x01.toByte(),                         //     REPORT_COUNT (1)
        0x75.toByte(), 0x06.toByte(),                         //     REPORT_SIZE (6)
        0x81.toByte(), 0x03.toByte(),                         //     INPUT (Cnst,Var,Abs)
        0x05.toByte(), 0x01.toByte(),                         //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                         //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                         //     USAGE (Y)
        0x15.toByte(), 0x81.toByte(),                         //     LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),                         //     LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),                         //     REPORT_SIZE (8)
        0x95.toByte(), 0x02.toByte(),                         //     REPORT_COUNT (2)
        0x81.toByte(), 0x06.toByte(),                         //     INPUT (Data,Var,Rel)
        0xc0.toByte(),                               //   END_COLLECTION
        0xc0.toByte()                                //END_COLLECTION
    )




    //BluetoothHidDevice.SUBCLASS1_COMBO
    val MOUSE_ABSOLUTE = byteArrayOf(
        0x05.toByte(), 0x01.toByte(), //USAGE_PAGE (Digitizers)
        0x09.toByte(), 0x02.toByte(), //USAGE (Touch Screen)
        0xa1.toByte(), 0x01.toByte(), //COLLECTION (Application)
        0x85.toByte(), 0x02.toByte(), //    REPORT_ID (MOUSE)
        0x09.toByte(), 0x01.toByte(), //    USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(), //    COLLECTION (Physical)
        0x05.toByte(), 0x01.toByte(), //        USAGE_PAGE (Generic Desk..
        0x26.toByte(), 0xff.toByte(), 0x0f, //  LOGICAL_MAXIMUM (32767)
        0x75.toByte(), 0x10.toByte(), //        REPORT_SIZE (16)
        0x95.toByte(), 0x01.toByte(), //        REPORT_COUNT (1)
        0x55.toByte(), 0x0e.toByte(), //        UNIT_EXPONENT (-2)
        0x65.toByte(), 0x13.toByte(), //        UNIT (Inch,EngLinear)
        0x09.toByte(), 0x30.toByte(), //        USAGE (X)
        0x35.toByte(), 0x00.toByte(), //        PHYSICAL_MINIMUM (0)
        0x46.toByte(), 0x70.toByte(), 0x08, //  PHYSICAL_MAXIMUM (1080)
        0x81.toByte(), 0x02.toByte(), //        INPUT (Data,Var,Abs)
        0x46.toByte(), 0x00.toByte(), 0x0f, //  PHYSICAL_MAXIMUM (1920)
        0x09.toByte(), 0x31.toByte(), //        USAGE (Y)
        0x81.toByte(), 0x02.toByte(), //        INPUT (Data,Var,Abs)
        0xc0.toByte(),                //    END_COLLECTION
        0xc0.toByte() //                END_COLLECTION
    )// End

}