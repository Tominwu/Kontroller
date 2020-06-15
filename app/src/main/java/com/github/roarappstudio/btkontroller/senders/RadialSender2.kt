package com.github.roarappstudio.btkontroller.senders

// 用于连接测试，简化了事件。与轮子不同，不需要reports类即可发送报文
// RadialSender3只包含button和dial的4个事件
// RadialSender2比RadialSender3优化了报文。
// 事实上，dial只有短按/长按/滚动三种操作，不存在下压的同时旋转的动作

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.HapticFeedbackConstants
import java.nio.ByteBuffer

@Suppress("MemberVisibilityCanBePrivate")
open class RadialSender2(
    val hidDevice: BluetoothHidDevice,
    val host: BluetoothDevice

) {
    val ID = 1
    var angel_0 = 0
    var byte_l = byteArrayOfInts(0x38, 0x00)
    var byte_r = byteArrayOfInts(0xc8, 0xff)
    var byte_press = byteArrayOfInts(1, 0)
    var byte_release = byteArrayOfInts(0, 0)

    fun byteArrayOfInts(vararg ints: Int) = ByteArray(ints.size) { pos -> ints[pos].toByte() }
/*

    private fun sendReport(bytes: ByteArray, key: String) {
//        Log.i(TAG,"hid="+hidDevice+", host="+host)
        Log.i(TAG, "Report sent=" + hidDevice.sendReport(host, ID, byte_press) + ",key=" + key)
    }


    open fun sendDial(angel: Int) {
        // 输入当前角度。如果有角度差，则发送dial旋转事件
        // 默认已经正则化，一周3600count.如果差异大于半周
        var key = angel - angel_0
        if (key == 0)
            return
        if (Math.abs(key) > 1800) {
            if (angel > angel_0)
                key += -3600
            else
                key += 3600
        }
        angel_0 = angel

        // 参数key是前一次报文和当前报文的角度差，故有正负。处理了旋转方向的问题
        // key*2 进行作移1bit
        var byte4 = ByteBuffer.allocate(4).putInt(key * 2).array();
        var bytes: ByteArray = ByteArray(2) { 0 }
        bytes[0] = byte4[3]
        bytes[1] = byte4[2]
        // 事实上，dial只有短按/长按/滚动三种操作，不存在下压的同时旋转的动作
//        if (button) {
//            bytes[0] = bytes[0] or 1.toByte()
//        }
        sendReport(
            bytes,
            "dial count " + key + ", bytes=" + bytes[0] + ":" + bytes[1] + ", byte4=" + byte4[0] + ":" + byte4[1] + ":" + byte4[2] + ":" + byte4[3]
        )
    }


    open fun sendKeys(key: Int) {
        // 发送button按键事件
        if (key == 1)
            sendReport(byte_press, key.toString())
        else if (key == 2)
            sendReport(byte_release, key.toString())
        else if (key == 3)
            sendReport(byte_l, key.toString())
        else if (key == 4)
            sendReport(byte_r, key.toString())
        else
            Log.e(TAG, "error key=" + key)
    }

    */

    open fun sendKeys(key: Int) {
        if (key == 1)
            Log.i(TAG, "Report sent=" + hidDevice.sendReport(host, ID, byte_press) + ",key=" + key)
        else if (key == 2)
            Log.i(TAG, "Report sent=" + hidDevice.sendReport(host, ID, byte_release) + ",key=" + key)
        else if (key == 3)
            Log.w(TAG, "Report sent=" + hidDevice.sendReport(host, ID, byte_l) + ",key=" + key)
        else if (key == 4)
            Log.w(TAG, "Report sent=" + hidDevice.sendReport(host, ID, byte_r) + ",key=" + key)
        else
            Log.e(TAG, "error key=" + key)
    }




    open fun sendDial(angel: Int) :Boolean{
        // 默认已经正则化，一周3600count.如果差异大于半周
        var key = angel - angel_0

        if (Math.abs(key) > 1800) {
            if (angel > angel_0)
                key += -3600
            else
                key += 3600
        }

        if (Math.abs(key) > 900) {
            // 旋转角度大于90，跳帧
            Log.e(TAG, "skip. angel=" + angel+", angel_0="+angel_0)
            angel_0 = angel
            return false
        }


        // 衰减系数k。每k个count发送1次事件，从而令旋转操作变慢
        val k=2
        if(key*key<k*k)
            return false

        angel_0 = angel

        // 参数key是前一次报文和当前报文的角度差，故有正负。处理了旋转方向的问题
        // key*2 进行作移1bit
        var byte4 = ByteBuffer.allocate(4).putInt(key/k * 2).array();
        var bytes: ByteArray = ByteArray(2) { 0 }
        bytes[0] = byte4[3]
        bytes[1] = byte4[2]
        // 事实上，dial只有短按/长按/滚动三种操作，不存在下压的同时旋转的动作
//        if (button) {
//            bytes[0] = bytes[0] or 1.toByte()
//        }

        Log.w(TAG, "Report sent=" + hidDevice.sendReport(host, ID, bytes) + ", dial count=" + key+", bytes="+bytes[0]+":"+bytes[1]+", byte4="+byte4[0]+":"+byte4[1]+":"+byte4[2]+":"+byte4[3])
   return true
    }



    companion object {
        const val TAG = "RadialSender2"
    }


}