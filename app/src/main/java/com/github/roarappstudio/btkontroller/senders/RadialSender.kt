package com.github.roarappstudio.btkontroller.senders



import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.KeyEvent
import com.github.roarappstudio.btkontroller.reports.KeyboardReport
import com.github.roarappstudio.btkontroller.reports.RadialControllerInput

@Suppress("MemberVisibilityCanBePrivate")
open class RadialSender(
    val hidDevice: BluetoothHidDevice,
    val host: BluetoothDevice

) {
    var radialControllerInput = RadialControllerInput()
    var x:Int =0x4000
    var y:Int=0x4000

    val maxX=0x7fff
    val maxY=0x7fff


    protected open fun sendKeys() {
        if (!hidDevice.sendReport(host, RadialControllerInput.ID, radialControllerInput.bytes)) {
            Log.e(TAG, "Report wasn't sent")
        }
    }

    private fun calPosition(x:Int,dx:Int,max:Int):Int{
        var y=x+dx;
        if(y<0)
            y=0
        if(y>max)
            y=max
        return y
    }

    open fun moveX(dx:Int){
        x=calPosition(x,dx,maxX)
    }

    open fun moveY(dy:Int){
        y=calPosition(y,dy,maxY)
    }

     open fun setModifiers(state:Boolean) {
       radialControllerInput.Button=state
        sendKeys()
    }

    companion object {
        const val TAG = "RadialSender"
    }



}