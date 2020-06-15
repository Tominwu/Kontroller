package com.github.roarappstudio.btkontroller.reports
/*
The host makes use of the following usages when extracting data from an input report:

Member	Description	Page	ID	Mandatory/Optional
Button	State of the button located on radial controller	0x09	0x01	Mandatory
Dial	Relative rotation of the radial controller	0x01	0x37	Mandatory
X	X coordinate of contact position	0x01	0x30	Optional
Y	Y coordinate of contact position	0x01	0x31	Optional
Width	Width of bounding box around a contact	0x0D	0x48	Optional
Height	Height of bounding box around a contact	0x0D	0x49	Optional

The class only has the Mandatory function
*/
import android.util.Log
import com.github.roarappstudio.btkontroller.senders.RadialSender
import java.nio.ByteBuffer
import kotlin.experimental.and
import kotlin.experimental.or

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class RadialControllerInput(
    val bytes: ByteArray = ByteArray(6) { 0 }
) {

//
//    var x: Int = 0x4000
//    var y: Int = 0x4000
//
//    val maxX = 0x7fff
//    val maxY = 0x7fff


    var Button: Boolean
        get() = bytes[0] and 0b1 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b1
            else
                bytes[0] and 0b1111110
        }


    var Dial: Byte
        get() = (bytes[0].toInt() shr 1).toByte()
        set(value) {
            bytes[0] = ((value.toInt() shl 1).toByte() or (bytes[0] and 0b1))
            if (value > 0)
                bytes[1] = 0;
            else
                bytes[1] = 0xff.toByte();
        }

    /*  2byte数据转int

        {
            // 有符号
            fun convertTwoSignInt(byteArray: ByteArray): Int =
                (byteArray[1].toInt() shl 8) or (byteArray[0].toInt() and 0xFF)

            fun convertTwoUnSignInt(byteArray: ByteArray): Int =
                (byteArray[3].toInt() shl 24) or (byteArray[2].toInt() and 0xFF) or (byteArray[1].toInt() shl 8) or (byteArray[0].toInt() and 0xFF)

            // 无符号
            fun convertFourUnSignInt(byteArray: ByteArray): Int =
                (byteArray[1].toInt() and 0xFF) shl 8 or (byteArray[0].toInt() and 0xFF)

            fun convertFourUnSignLong(byteArray: ByteArray): Long =
                ((byteArray[3].toInt() and 0xFF) shl 24 or (byteArray[2].toInt() and 0xFF) shl 16 or (byteArray[1].toInt() and 0xFF) shl 8 or (byteArray[0].toInt() and 0xFF)).toLong()

        }

    */
    fun conver_2byte_int(byteArray: ByteArray): Int =
        (byteArray[1].toInt() shl 8) or (byteArray[0].toInt() and 0xFF)

    fun conver_int_2byte(value: Int): ByteArray {
        var byte4 = ByteBuffer.allocate(4).putInt(value).array();
        var bytes: ByteArray = ByteArray(2) { 0 }
        bytes[0] = byte4[0]
        bytes[1] = byte4[1]
        return bytes
    }


    private fun calPosition(x: Int, dx: Int, max: Int): Int {
        var y = x + dx;
        if (y < 0)
            y = 0
        if (y > max)
            y = max
        return y
    }



    var dx: Byte
        get() = bytes[1]
        set(value) {
            ByteBuffer.allocate(4).putInt(value.toInt()).array();
            bytes[1] = value
        }

    var dy: Byte
        get() = bytes[2]
        set(value) {
            bytes[2] = value
        }

    fun reset() = bytes.fill(0)

    companion object {
        const val ID = 1
    }

}