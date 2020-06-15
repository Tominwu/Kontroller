package com.github.roarappstudio.btkontroller.listeners

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.github.roarappstudio.btkontroller.reports.RadialControllerInput
import com.github.roarappstudio.btkontroller.senders.RadialSender
import com.github.roarappstudio.btkontroller.senders.RelativeMouseSender
import java.nio.ByteBuffer
import kotlin.math.roundToInt

@ExperimentalUnsignedTypes
class WheelListener(val hidDevice: BluetoothHidDevice, val host: BluetoothDevice, val mRadialSender: RadialSender ):  View.OnTouchListener{


    //val absMouseReport = AbsMouseReport()

    private var previousX: Float = 0f
    private var previousY: Float = 0f
    private var pointerMotionStopFlag :Int =0


    override fun onTouch(v: View, event: MotionEvent): Boolean {

        //this.gDetector.onTouchEvent(event)

            val x: Float = event.x
            val y: Float = event.y
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    Log.d("pointerCount_is", event.pointerCount.toString())
                    if (event.pointerCount == 1) {
                        hidDevice.sendReport(this.host, RadialControllerInput.ID, mRadialSender.radialControllerInput.bytes)

/*                    rMouseSender.mouseReport.dxMsb = bytesArrX[0]
                    rMouseSender.mouseReport.dxLsb = bytesArrX[1]

                    rMouseSender.mouseReport.dyMsb = bytesArrY[0]
                    rMouseSender.mouseReport.dyLsb = bytesArrY[1]

                    Log.d("ddf2", bytesArrX.contentToString())

                    hidDevice.sendReport(this.host, 4, rMouseSender.mouseReport.bytes)*/

                    }
                }
                MotionEvent.ACTION_UP->{

                    hidDevice.sendReport(this.host, RadialControllerInput.ID, mRadialSender.radialControllerInput.bytes)

                }

                }


            return true

        }


}