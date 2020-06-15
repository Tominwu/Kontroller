package com.github.roarappstudio.btkontroller.senders

// 用于连接测试，简化了事件。与轮子不同，不需要reports类即可发送报文
// RadialSender3只包含button和dial的4个事件
// 欠缺之处是，button按下需要手动恢复，并且不能产生下压的同时旋转的动作

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.KeyEvent
import android.widget.Switch
import com.github.roarappstudio.btkontroller.reports.KeyboardReport
import com.github.roarappstudio.btkontroller.reports.RadialControllerInput

@Suppress("MemberVisibilityCanBePrivate")
open class RadialSender3(
    val hidDevice: BluetoothHidDevice,
    val host: BluetoothDevice

) {
    val ID=1
//    val byte_press: ByteArray = ByteArray(2) {0}

    var byte_l  = byteArrayOfInts(0x38,0x00)
    var byte_r  = byteArrayOfInts(0xc8,0xff)
    var byte_press=byteArrayOfInts(1,0)
    var byte_release=byteArrayOfInts(0,0)

    fun byteArrayOfInts(vararg ints: Int) = ByteArray(ints.size) { pos -> ints[pos].toByte() }

    val radialControllerInput = RadialControllerInput()

    open fun sendKeys(key:Int) {
       var bytes:ByteArray=byteArrayOfInts(0,0)
        if(key==1)
            bytes=byte_l
        else if(key==2)
            bytes=byte_r
        else if(key==3)
            bytes=byte_press
        else if(key==4)
            bytes=byte_release
        else
            Log.e(TAG, "error key="+key)

        Log.w(TAG, "Report sent="+hidDevice.sendReport(host, ID,bytes)+",key="+key)

}
    companion object {
        const val TAG = "RadialSender2"
    }



}