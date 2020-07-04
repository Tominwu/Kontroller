package com.github.roarappstudio.btkontroller.senders



import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.KeyEvent
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

     private fun slow(time:Long){
         if(time-last_time<10000000)
          Thread.sleep(1)
         last_time=time
     }

    var last_time=System.nanoTime()

    protected open fun sendKeys2() {
        var key=keyboardReport.key1
        if(key==last_key){
            slow(System.nanoTime())
            Log.w(TAG, "Force release normal keys (key==last_key)")
            hidDevice.sendReport(host, KeyboardReport.ID,   keyboardReport.new_normal_key())
        }
        last_key=keyboardReport.key1
        slow(System.nanoTime())
        if (!hidDevice.sendReport(host, KeyboardReport.ID, keyboardReport.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
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

    fun sendNullKeys(time: Int) {
//        keyboardReport.bytes.fill(0)
        keyboardReport.reset()
        last_key=(-1).toByte()
        Thread.sleep(time.toLong())
        if (!hidDevice.sendReport(host, KeyboardReport.ID, keyboardReport.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
        Log.i("sendKeyboard","Null(${time})")
    }

    var keyEventLock=false

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

    fun sendKeyboard(str : String): Boolean {
        if(str.length<1){
            sendNullKeys()
            return false
        }

        Log.i("sendKeyboard","charset=unicode\t content="+str)

        try {
            var c : Int
            for (i in 0 until str.length) {
                c = str[i].toInt()
                var m=0
                var n=c

                var k=1
                while (k<c){
                    k=k*10
                }
                
//                keyboardReport.bytes.fill(0)
                keyboardReport.reset()
//        切换小键盘状态
//        keyboardReport.key1=0x53.toByte()
//        sendKeys()
//        sendNullKeys()

                keyboardReport.leftAlt=true
                while (k>10){
                    k=k/10
                    m=n/k
                    if(m>0){
                        n=n-k*m

//                        Log.i("sendKeyboard(str)","c="+c+"=x+k*m+n="+k+" * "+m+" + "+n)
                        keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(m)
                    }else{
//                        Log.i("sendKeyboard(str)","c="+c+"=x+k*m+n="+k+" * "+0+" + "+n)
                        keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(0)
                    }

                    sendKeys2()
                }
                keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(n)
                sendKeys2()

                sendNullKeys()

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




    fun sendKeyboard(string : String, encoder:Charset): Boolean {
        if(string.length<1){
            sendNullKeys()
            return false
        }

        try {
//            var str=String(string.toByteArray(Charset.defaultCharset()), encoder)
//            Log.i("sendKeyboard","charset="+encoder.name()+"\t content="+str)
//            val str =  string.toByteArray(Charset.defaultCharset())
            var c : Int

            for (i in 0 until string.length) {
                c=byteArray2Int(string[i].toString().toByteArray(encoder))
                Log.i("sendKeyboard","charset="+encoder.name()+"\t content="+string[i]+",\tvalue=${c}")
                var m=0
                var n=c

                var k=1
                while (k<c){
                    k=k*10
                }

                sendNullKeys()
                keyboardReport.bytes.fill(0)
//        切换小键盘状态
//        keyboardReport.key1=0x53.toByte()
//        sendKeys()
//        sendNullKeys()

                keyboardReport.leftAlt=true

                while (k>10){
                    k=k/10
                    m=n/k
                    if(m>0){
                        n=n-k*m
                        Log.i("sendKeyboard(str)","send=$m c="+c+"=x+k*m+n="+k+" * "+m+" + "+n)
                        keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(m)
                    }else{
                        Log.i("sendKeyboard(str)","send=0 c="+c+"=x+k*m+n="+k+" * "+0+" + "+n)
                        keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(0)
                    }

                    sendKeys2()
                }
                Log.i("sendKeyboard(str)","send=$n c=$c")
                keyboardReport.key1=KeyboardReport.KeyCodeNumberPad.get(n)
                sendKeys2()
                sendNullKeys()
            }
        }catch (e:Exception){
            // release all keys
            sendNullKeys()
            e.printStackTrace()
        }

        return true
    }

    companion object {
        const val TAG = "KeyboardSender"
    }

}