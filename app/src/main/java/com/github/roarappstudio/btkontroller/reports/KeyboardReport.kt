package com.github.roarappstudio.btkontroller.reports

import android.view.KeyEvent
import kotlin.experimental.and
import kotlin.experimental.or

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class KeyboardReport (
    val bytes: ByteArray = ByteArray(3) {0}
) {


    var leftControl: Boolean
        get() = bytes[0] and 0b1 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b1
            else
                bytes[0] and 0b11111110.toByte()
        }

    var leftShift: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b10
            else
                bytes[0] and 0b11111101.toByte()
        }

    var leftAlt: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b100
            else
                bytes[0] and 0b11111011.toByte()
        }
    var leftGui: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b1000
            else
                bytes[0] and 0b11110111.toByte()
        }

    var rightControl: Boolean
        get() = bytes[0] and 0b1 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b10000
            else
                bytes[0] and 0b11101111.toByte()
        }

    var rightShift: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b100000
            else
                bytes[0] and 0b11011111.toByte()
        }

    var rightAlt: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b1000000
            else
                bytes[0] and 0b10111111.toByte()
        }
    var rightGui: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b10000000.toByte()
            else
                bytes[0] and 0b01111111
        }

    var key1: Byte
        get() = bytes[2]
        set(value) {
            bytes[2] = value

        }

    fun reset() = bytes.fill(0)

    fun reset_function_key(){ bytes[0]=0}
    fun new_normal_key():ByteArray{
        var v=ByteArray(3) {0}
        v[0]=bytes[0]
        return v
    }

    companion object {
        const val ID = 8

        val KeyEventMap = mapOf<Int,Int>(
            KeyEvent.KEYCODE_A to 4,
            KeyEvent.KEYCODE_B to 5,
            KeyEvent.KEYCODE_C to 6,
            KeyEvent.KEYCODE_D to 7,
            KeyEvent.KEYCODE_E to 8,
            KeyEvent.KEYCODE_F to 9,
            KeyEvent.KEYCODE_G to 10,
            KeyEvent.KEYCODE_H to 11,
            KeyEvent.KEYCODE_I to 12,
            KeyEvent.KEYCODE_J to 13,
            KeyEvent.KEYCODE_K to 14,
            KeyEvent.KEYCODE_L to 15,
            KeyEvent.KEYCODE_M to 16,
            KeyEvent.KEYCODE_N to 17,
            KeyEvent.KEYCODE_O to 18,
            KeyEvent.KEYCODE_P to 19,
            KeyEvent.KEYCODE_Q to 20,
            KeyEvent.KEYCODE_R to 21,
            KeyEvent.KEYCODE_S to 22,
            KeyEvent.KEYCODE_T to 23,
            KeyEvent.KEYCODE_U to 24,
            KeyEvent.KEYCODE_V to 25,
            KeyEvent.KEYCODE_W to 26,
            KeyEvent.KEYCODE_X to 27,
            KeyEvent.KEYCODE_Y to 28,
            KeyEvent.KEYCODE_Z to 29,


            KeyEvent.KEYCODE_1 to 30,
            KeyEvent.KEYCODE_2 to 31,
            KeyEvent.KEYCODE_3 to 32,
            KeyEvent.KEYCODE_4 to 33,
            KeyEvent.KEYCODE_5 to 34,
            KeyEvent.KEYCODE_6 to 35,
            KeyEvent.KEYCODE_7 to 36,
            KeyEvent.KEYCODE_8 to 37,
            KeyEvent.KEYCODE_9 to 38,
            KeyEvent.KEYCODE_0 to 39,

            KeyEvent.KEYCODE_F1 to 58,
            KeyEvent.KEYCODE_F2 to 59,
            KeyEvent.KEYCODE_F3 to 60,
            KeyEvent.KEYCODE_F4 to 61,
            KeyEvent.KEYCODE_F5 to 62,
            KeyEvent.KEYCODE_F6 to 63,
            KeyEvent.KEYCODE_F7 to 64,
            KeyEvent.KEYCODE_F8 to 65,
            KeyEvent.KEYCODE_F9 to 66,
            KeyEvent.KEYCODE_F10 to 67,
            KeyEvent.KEYCODE_F11 to 68,
            KeyEvent.KEYCODE_F12 to 69,

            KeyEvent.KEYCODE_ENTER to 40,
            KeyEvent.KEYCODE_ESCAPE to 41,
            KeyEvent.KEYCODE_DEL to 42,
            KeyEvent.KEYCODE_TAB to 43,
            KeyEvent.KEYCODE_SPACE to 44,
            KeyEvent.KEYCODE_MINUS to 45,
            KeyEvent.KEYCODE_EQUALS to 46,
            KeyEvent.KEYCODE_LEFT_BRACKET to 47,
            KeyEvent.KEYCODE_RIGHT_BRACKET to 48,
            KeyEvent.KEYCODE_BACKSLASH to 49,
            KeyEvent.KEYCODE_SEMICOLON to 51,
            KeyEvent.KEYCODE_APOSTROPHE to 52,
            KeyEvent.KEYCODE_GRAVE to 53,
            KeyEvent.KEYCODE_COMMA to 54,
            KeyEvent.KEYCODE_PERIOD to 55,
            KeyEvent.KEYCODE_SLASH to 56,

            KeyEvent.KEYCODE_SCROLL_LOCK to 71,
            KeyEvent.KEYCODE_INSERT to 73,
            KeyEvent.KEYCODE_HOME to 74,
            KeyEvent.KEYCODE_PAGE_UP to 75,
            KeyEvent.KEYCODE_FORWARD_DEL to 76,
            KeyEvent.KEYCODE_MOVE_END to 77,
            KeyEvent.KEYCODE_PAGE_DOWN to 78,
            KeyEvent.KEYCODE_NUM_LOCK to 83,

            KeyEvent.KEYCODE_DPAD_RIGHT to 79,
            KeyEvent.KEYCODE_DPAD_LEFT to 80,
            KeyEvent.KEYCODE_DPAD_DOWN to 81,
            KeyEvent.KEYCODE_DPAD_UP to 82,

            KeyEvent.KEYCODE_AT to 31,
            KeyEvent.KEYCODE_POUND to 32,
            KeyEvent.KEYCODE_STAR to 37
        )



        val UnicodeKeyEventMap = mapOf<Int,Int>(
            97   to 4, //KeyEvent.KEYCODE_A
            98   to 5, //KeyEvent.KEYCODE_B
            99   to 6, //KeyEvent.KEYCODE_C
            100  to 7, //KeyEvent.KEYCODE_D
            101  to 8, //KeyEvent.KEYCODE_E
            102  to 9, //KeyEvent.KEYCODE_F
            103  to 10,//KeyEvent.KEYCODE_G,
            104  to 11,//KeyEvent.KEYCODE_H,
            105  to 12,//KeyEvent.KEYCODE_I,
            106  to 13,//KeyEvent.KEYCODE_J,
            107  to 14,//KeyEvent.KEYCODE_K,
            108  to 15,//KeyEvent.KEYCODE_L,
            109  to 16,//KeyEvent.KEYCODE_M,
            110  to 17,//KeyEvent.KEYCODE_N,
            111  to 18,//KeyEvent.KEYCODE_O,
            112  to 19,//KeyEvent.KEYCODE_P,
            113  to 20,//KeyEvent.KEYCODE_Q,
            114  to 21,//KeyEvent.KEYCODE_R,
            115  to 22,//KeyEvent.KEYCODE_S,
            116  to 23,//KeyEvent.KEYCODE_T,
            117  to 24,//KeyEvent.KEYCODE_U,
            118  to 25,//KeyEvent.KEYCODE_V,
            119  to 26,//KeyEvent.KEYCODE_W,
            120  to 27,//KeyEvent.KEYCODE_X,
            121  to 28,//KeyEvent.KEYCODE_Y,
            122  to 29,//KeyEvent.KEYCODE_Z,
            49   to 30,  //  KeyEvent.KEYCODE_1
            50   to 31,  //  KeyEvent.KEYCODE_2
            51   to 32,  //  KeyEvent.KEYCODE_3
            52   to 33,  //  KeyEvent.KEYCODE_4
            53   to 34,  //  KeyEvent.KEYCODE_5
            54   to 35,  //  KeyEvent.KEYCODE_6
            55   to 36,  //  KeyEvent.KEYCODE_7
            56   to 37,  //  KeyEvent.KEYCODE_8
            57   to 38,  //  KeyEvent.KEYCODE_9
            48   to 39,  //  KeyEvent.KEYCODE_0
            9    to 43, //   KeyEvent.KEYCODE_TAB
            32   to 44 // KeyEvent.KEYCODE_SPACE
        )



        val UnicodeKeyEvent2ndMap = mapOf<Int,Int>(
            65   to 4, //KeyEvent.KEYCODE_A
            66   to 5, //KeyEvent.KEYCODE_B
            67   to 6, //KeyEvent.KEYCODE_C
            68  to 7, //KeyEvent.KEYCODE_D
            69  to 8, //KeyEvent.KEYCODE_E
            70  to 9, //KeyEvent.KEYCODE_F
            71  to 10,//KeyEvent.KEYCODE_G,
            72  to 11,//KeyEvent.KEYCODE_H,
            73  to 12,//KeyEvent.KEYCODE_I,
            74  to 13,//KeyEvent.KEYCODE_J,
            75  to 14,//KeyEvent.KEYCODE_K,
            76  to 15,//KeyEvent.KEYCODE_L,
            77  to 16,//KeyEvent.KEYCODE_M,
            78  to 17,//KeyEvent.KEYCODE_N,
            79  to 18,//KeyEvent.KEYCODE_O,
            80  to 19,//KeyEvent.KEYCODE_P,
            81  to 20,//KeyEvent.KEYCODE_Q,
            82  to 21,//KeyEvent.KEYCODE_R,
            83  to 22,//KeyEvent.KEYCODE_S,
            84  to 23,//KeyEvent.KEYCODE_T,
            85  to 24,//KeyEvent.KEYCODE_U,
            86  to 25,//KeyEvent.KEYCODE_V,
            87  to 26,//KeyEvent.KEYCODE_W,
            88  to 27,//KeyEvent.KEYCODE_X,
            89  to 28,//KeyEvent.KEYCODE_Y,
            90  to 29//,//KeyEvent.KEYCODE_Z,
//            49   to 30,  //  KeyEvent.KEYCODE_1
//            50   to 31,  //  KeyEvent.KEYCODE_2
//            51   to 32,  //  KeyEvent.KEYCODE_3
//            52   to 33,  //  KeyEvent.KEYCODE_4
//            53   to 34,  //  KeyEvent.KEYCODE_5
//            54   to 35,  //  KeyEvent.KEYCODE_6
//            55   to 36,  //  KeyEvent.KEYCODE_7
//            56   to 37,  //  KeyEvent.KEYCODE_8
//            57   to 38,  //  KeyEvent.KEYCODE_9
//            48   to 39,  //  KeyEvent.KEYCODE_0
//            9    to 43, //   KeyEvent.KEYCODE_TAB
//            32   to 44 // KeyEvent.KEYCODE_SPACE
        )


        //        89 0x59 Keypad 1 and End 93 √ √ √ 4/101/104
//        83 0x53 Keypad Num Lock and Clear11
        val KeyCodeNumberPad: ByteArray = byteArrayOf(
            98,
            89, 90, 91,
            92, 93, 94,
            95, 96, 97,
            83
        )
    }
}