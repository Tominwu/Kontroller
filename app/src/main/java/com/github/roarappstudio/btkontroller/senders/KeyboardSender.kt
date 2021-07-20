package com.github.roarappstudio.btkontroller.senders



import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.KeyEvent
import com.github.roarappstudio.btkontroller.Value
import com.github.roarappstudio.btkontroller.reports.KeyboardReport
import java.nio.charset.Charset


@Suppress("MemberVisibilityCanBePrivate")
open class KeyboardSender(
    val hidDevice: BluetoothHidDevice,
    val host: BluetoothDevice

) {
    val keyboardReport = KeyboardReport()

    protected open fun sendKeys() {
        if (!hidDevice.sendReport(host, KeyboardReport.ID, keyboardReport.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
    }

    var last_key=(-1).toByte()

    var caps_lock_flag=false
    var num_lock_flag=true

    var replace_newline_flag=true
    var fast_mode_flag=false
    var delay_nano:Long=13500000L
    var delay_millis:Long=13L

    open fun configSendString(delay: Int, fast_mode: Boolean, replace_newline: Boolean){
        delay_millis=delay.toLong()
        delay_nano=delay_millis*1000000+500000
        fast_mode_flag=fast_mode
        replace_newline_flag=replace_newline
        Log.w("configSendString","delay_millis=${delay_millis}  delay_nano=${delay_nano}")
    }


    private fun slow(time:Long){
         if(delay_millis>0){
//             Log.i("slow()","delay_millis=${delay_millis} ${delay_millis>0} ${delay_millis>0L}")
             if(time-last_time<delay_nano){
                 Thread.sleep(delay_millis)
//                 Log.w("slow=true","time=${time} ,last=${last_time}, dif=${time-last_time}, now=${System.nanoTime()}")
             }else{
//                 Log.w("slow=false","time=${time} ,last=${last_time}, dif=${time-last_time}")
             }
             last_time=time
         }
     }

    var last_time=System.nanoTime()

    //  send keys ONLY for send string
    protected open fun sendKeys2() {
        var key=keyboardReport.key1
        if(key==last_key){
            slow(System.nanoTime())
//            Log.w(TAG, "Force release normal keys (key==last_key)")
            if (!hidDevice.sendReport(host, KeyboardReport.ID,   keyboardReport.new_normal_key())) {
                Log.e(TAG, "Report wasn't sent - ")
            }
        }

        last_key=keyboardReport.key1
        slow(System.nanoTime())
        if (!hidDevice.sendReport(host, KeyboardReport.ID, keyboardReport.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
        if(keyboardReport.key1==83.toByte())
            Log.e(TAG,"send numLK")
    }

    protected open fun customSender(modifier_checked_state: Int) {
        sendKeys()
        if(modifier_checked_state==0) sendNullKeys()
        else {
            keyboardReport.key1=0.toByte()
            sendKeys()
        }
    }

    protected open fun setModifiers(event:KeyEvent) {
        if(event.isShiftPressed) keyboardReport.leftShift=true
        if(event.isAltPressed) keyboardReport.leftAlt=true
        if(event.isCtrlPressed) keyboardReport.leftControl=true
        if(event.isMetaPressed) keyboardReport.leftGui=true
    }

    fun sendNullKeys() {
//        keyboardReport.bytes.fill(0)
        keyboardReport.reset()
        last_key=(-1).toByte()
        slow(System.nanoTime())
        if (!hidDevice.sendReport(host, KeyboardReport.ID, keyboardReport.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
        Log.i("sendKeyboard","NULL key")
    }

    fun keyEventHandler(keyEventCode: Int, event : KeyEvent, modifier_checked_state: Int,keyCode:Int): Boolean{

        val byteKey = KeyboardReport.KeyEventMap[keyEventCode]

        //  按下1时，keyEventCode=8, byteKey=30, event.keycode=8, toByte=30=0x1E
        //        KeyEvent.KEYCODE_1 to 30,
        //        Log.d("keyEventHandler","keyEventCode="+keyCode+", byteKey="+byteKey+", event.keycode="+event.keyCode+", toByte="+byteKey?.toByte())

        if(byteKey!=null)
        {
            setModifiers(event)
            if(event.keyCode== KeyEvent.KEYCODE_AT || event.keyCode== KeyEvent.KEYCODE_POUND || event.keyCode== KeyEvent.KEYCODE_STAR)
            {
                keyboardReport.leftShift=true
            }
            keyboardReport.key1=byteKey.toByte()
            customSender(modifier_checked_state)

            KeyEvent.KEYCODE_ALT_LEFT
            return true
        }
        else
        {
            return false
        }

    }

    fun sendKeyboard(keyCode : Int, event : KeyEvent, modifier_checked_state :Int): Boolean {
        return keyEventHandler(event.keyCode,event,modifier_checked_state,keyCode)
    }

    //  把一个字符的10进制编码以Alt+小键盘的形式发送出去
    fun sendKeyCode(c :Int) {
        keyboardReport.reset()

        if (replace_newline_flag && c == 0x0a) {
            keyboardReport.key1 = 40.toByte()
            sendKeys()
        }else{
        //        切换小键盘状态
        //        keyboardReport.key1=0x53.toByte()
        //        sendKeys()
        //        sendNullKeys()
                keyboardReport.leftAlt = true

            var m = 0
            var n = c

            var k = 1
            while (k <= c) {
                k = k * 10
            }
                while (k > 10) {
                    k = k / 10
                    m = n / k
                    if (m > 0) {
                        n = n - k * m

                        Log.i("sendKeyboard(str)", "c=" + c + ", send.${k}=${m}, n=${n}")
                        keyboardReport.key1 = KeyboardReport.KeyCodeNumberPad.get(m)
                    } else {
                        Log.i("sendKeyboard(str)", "c=" + c + ", send.${k}=0, n=${n}")
                        keyboardReport.key1 = KeyboardReport.KeyCodeNumberPad.get(0)
                    }
                    sendKeys2()
                }

                if (n > 9) {
                    m = n / 10
                    n = n - 10 * m
                    Log.e("sendKeyboard(str)", "c=" + c + ", send.10=" + n)
                    keyboardReport.key1 = KeyboardReport.KeyCodeNumberPad.get(m)
                    sendKeys2()
                }

                Log.i("sendKeyboard(str)", "c=" + c + ", send.1= " + n)
                keyboardReport.key1 = KeyboardReport.KeyCodeNumberPad.get(n)
                sendKeys2()
            }
         sendNullKeys()
    }



    // 如果是键盘字符，直接发送按键，并返回true
    fun fast_send_char(char : Int) :Boolean {
//        if (fast_mode_flag) {     }

            var byteKey = KeyboardReport.UnicodeKeyEventMap[char]

            if (byteKey != null) {
                keyboardReport.reset()
                keyboardReport.key1 = byteKey.toByte()
                sendKeys2()
                sendNullKeys()
                return true
            } else {
                byteKey = KeyboardReport.UnicodeKeyEvent2ndMap[char]
                if (byteKey != null) {
                    keyboardReport.reset()
                    keyboardReport.leftShift = true
                    keyboardReport.key1 = byteKey.toByte()
                    sendKeys2()
                    sendNullKeys()
                    return true
                }
            }


       return false
    }

    fun sendString(string : String): Boolean {
        if(string.length<1){
            sendNullKeys()
            return false
        }
        var str=string
        if (fast_mode_flag || replace_newline_flag)
            str=string.replace("\r","")

        Log.i("sendKeyboard","time=${System.currentTimeMillis()} charset=unicode\t content="+str)

        try {
            var c : Int
            for (i in 0 until str.length) {
                c = str[i].toInt()
                if (fast_mode_flag) {
                    if (fast_send_char(c))
                        continue
                }
                sendKeyCode(c)
            }
        }catch (e:Exception){
            // release all keys
            sendNullKeys()
            e.printStackTrace()
        }

        Log.i("sendKeyboard","time=${System.currentTimeMillis()} str.length=${str.length}")

        return true
    }


    fun sendString(str : String, encoder:Charset): Boolean {
        if(str.length<1){
            sendNullKeys()
            return false
        }

        var string=str
        if (fast_mode_flag || replace_newline_flag)
            string=str.replace("\r","")

        try {
//            var str=String(string.toByteArray(Charset.defaultCharset()), encoder)
//            Log.i("sendKeyboard","charset="+encoder.name()+"\t content="+str)
//            val str =  string.toByteArray(Charset.defaultCharset())
            var c : Int

            for (i in 0 until string.length) {
                if (fast_mode_flag) {
                    if (fast_send_char(string[i].toInt()))
                        continue
                }
                c=byteArray2Int(string[i].toString().toByteArray(encoder))
                Log.i("sendKeyboard","charset="+encoder.name()+"\t content="+string[i]+",\tvalue=${c}")
                sendKeyCode(c)
            }
        }catch (e:Exception){
            // release all keys
            sendNullKeys()
            e.printStackTrace()
        }

        return true
    }


    // 这个方法不对。事实上系统使用了big-endian的编码
     fun convertFourUnSignInt(byteArray: ByteArray): Int =
        (byteArray[1].toInt() and 0xFF) shl 8 or (byteArray[0].toInt() and 0xFF)

    // 把一个big-endian编码字符的数组转换为10进制
     fun byteArray2Int(byteArray: ByteArray): Int {
        var result =0
        for (i in 0 until byteArray.size){
//            丢掉高于位的部分
            if (i>4) {
                Log.w("byteArray2Int","input size=${byteArray.size}\t value=${byteArray}")
                return result
            }
           result=(result shl 8) or (byteArray[i].toInt() and 0xFF)
//            var v=(byteArray[i].toInt() and 0xFF).toInt()
//            Log.w("byte","$i = $v \t int_result=$result")
        }
       return result
    }


    companion object {
        const val TAG = "KeyboardSender"
    }


    //================== garbage====================//

    // only for test
    fun sendKeyboard_test(): Boolean {

        keyboardReport.bytes.fill(0)

//        // 切换小键盘状态
//        keyboardReport.key1=0x53.toByte()
//        sendKeys()
//        sendNullKeys()

        // 作为测试，发送alt+88可以得到X；小键盘失效时发送的是 上

        keyboardReport.leftAlt=true
        keyboardReport.key1=96.toByte()
        sendKeys()
        keyboardReport.key1=0.toByte()
        sendKeys()
        keyboardReport.key1=96.toByte()
        sendKeys()
//        Log.d("keyEventHandler","SEND KEY="+0X3A.toByte())

        // 0X3A=F1 KEY,82=8  ,
        // D/keyEventHandler: SEND KEY=58
        // D/keyEventHandler: SEND U88  num_pad_8=152, keycode_8=15, button_8=195

/*

     val byteKey =  KeyEvent.KEYCODE_NUMPAD_8

        keyboardReport.key1=byteKey.toByte()
        sendKeys()
        keyboardReport.key1=0.toByte()
        sendKeys()
        keyboardReport.key1=byteKey.toByte()
        sendKeys()
        keyboardReport.key1=0.toByte()
        sendKeys()*/
        sendNullKeys()

        Log.d("keyEventHandler","SEND U88  num_pad_8="+KeyEvent.KEYCODE_NUMPAD_8+", keycode_8="+KeyEvent.KEYCODE_8+", button_8="+KeyEvent.KEYCODE_BUTTON_8)

        return  true
    }

    // only for test
    fun sendKeyboard_test2(count:Int): Boolean {

        keyboardReport.bytes.fill(0)
        val t0=System.currentTimeMillis()
        for (i in 0 until count){
            keyboardReport.key1=96.toByte()
            sendKeys()
            keyboardReport.bytes.fill(0)
            sendKeys()
//            Log.d("sendKeyboard_test2", "keycode=96, i=${i}")
        }
        val time=System.currentTimeMillis()-t0
        Log.d("sendKeyboard_test2", "keycode=96, count=${count}, use time ${time}")
        return  true
    }


    // fast mode  抛弃无效字符直接发送回车键
    // fast_mode_flag=false 如果需要转换，那么发送到换行时，发送键盘码
    // 通过简化，不需要额外写方法了
    fun replace_space(string:String):String{
        if (fast_mode_flag || replace_newline_flag)
            return  string.replace("\r","")
        else
            return string
    }


}