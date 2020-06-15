package com.github.roarappstudio.btkontroller

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.github.roarappstudio.btkontroller.listeners.CompositeListener
import com.github.roarappstudio.btkontroller.listeners.GestureDetectListener
import com.github.roarappstudio.btkontroller.listeners.ViewListener
import org.jetbrains.anko.*
import com.github.roarappstudio.btkontroller.extraLibraries.CustomGestureDetector
import com.github.roarappstudio.btkontroller.reports.RadialControllerInput
import com.github.roarappstudio.btkontroller.senders.*
import com.tumuyan.wheelcontrol.controls.WheelPicker


class SelectDeviceActivity: Activity(),KeyEvent.Callback {

    private var autoPairMenuItem : MenuItem? =null
    private var screenOnMenuItem : MenuItem? =null

    private var bluetoothStatus : MenuItem? =null

    private lateinit var linearLayout: _LinearLayout
    private var sender: SensorSender? = null
    //private var  viewTouchListener : ViewListener? = null
    private var modifier_checked_state : Int =0
    private var  rMouseSender : RelativeMouseSender? = null

    private var rKeyboardSender : KeyboardSender? = null

    private var mRadialSender2:RadialSender2?=null


    @SuppressLint("ResourceType")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
/*            verticalLayout {


                linearLayout = this
                id = 0x69

                textView(){
                  id= R.id.mouseView
                  background=getDrawable(R.drawable.view_border)


                    text="Trackpad"
                    gravity=Gravity.CENTER


                }.lparams(width= matchParent,height = matchParent )

            }*/
    }

    fun getContext(): Context {
        return this
    }




    public override fun onStart() {
        super.onStart()

        bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
        bluetoothStatus?.tooltipText="App not connected via bluetooth"


        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)


        BluetoothController.autoPairFlag= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        autoPairMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        screenOnMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.screen_on_flag),false)

        if(sharedPref.getBoolean(getString(R.string.screen_on_flag),false)) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val trackPadView = find<View>(R.id.mouseView)

        val wheelView=find<WheelPicker>(R.id.wheel)

        val button1=find<Button>(R.id.button1)
        val button2=find<Button>(R.id.button2)
        val button3=find<Button>(R.id.button3)
        val button4=find<Button>(R.id.button4)

        wheelView.getColor();

//To set the old selected color u can do it like this
        wheelView.setOldCenterColor(wheelView.getColor());
// adds listener to the colorpicker which is implemented
//in the activity
//        wheelView.setOnColorChangedListener( WheelPicker.OnColorChangedListener {  });

//to turn of showing the old color
//        wheelView.setShowOldCenterColor(false);




        BluetoothController.init(this)

        BluetoothController.getSender { hidd, device ->
            Log.wtf("weiufhas", "Callback called")
            val mainHandler = Handler(getContext().mainLooper)

            mainHandler.post(object : Runnable{
                override fun run() {


                    rKeyboardSender= KeyboardSender(hidd,device)

//                    mRadialSender= RadialSender(hidd,device)

                    mRadialSender2= RadialSender2(hidd,device)

                        wheelView.setBluetoothRadialSender(mRadialSender2);

                    val rMouseSender = RelativeMouseSender(hidd,device)
                    Log.i("TAGdddUI", Thread.currentThread().getName());
                    val viewTouchListener = ViewListener(hidd, device, rMouseSender)
                    val mDetector = CustomGestureDetector(getContext(), GestureDetectListener(rMouseSender))

                    val gTouchListener = object : View.OnTouchListener {

                        override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                            return mDetector.onTouchEvent(event)
                        }
                    }


                    val composite : CompositeListener = CompositeListener()

                    composite.registerListener(gTouchListener)
                    composite.registerListener(viewTouchListener)
                    trackPadView.setOnTouchListener(composite)



                    bluetoothStatus?.icon = getDrawable(R.drawable.ic_action_app_connected)
                    bluetoothStatus?.tooltipText="App Connected via bluetooth"

                    button1.setOnClickListener {
                      //  it.visibility = View.VISIBLE
                        mRadialSender2!!.sendKeys(1)
                    }

                    button2.setOnClickListener {
                        mRadialSender2!!.sendKeys(2)
                    }
                    button3.setOnClickListener {
                        mRadialSender2!!.sendKeys(3)
                    }
                    button4.setOnClickListener {
                        mRadialSender2!!.sendKeys(4)
                    }
                }

            })

            Log.i("TAGddd", Thread.currentThread().getName());

        }

        BluetoothController.getDisconnector{
            val mainHandler = Handler(getContext().mainLooper)

            mainHandler.post(object : Runnable {
                override fun run() {
                    bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
                    bluetoothStatus?.tooltipText="App not connected via bluetooth"
                }
            })
        }


    }

    private fun initSensor() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)
        sensorManager.registerListener(sender, sensor, SensorManager.SENSOR_DELAY_GAME)
    }



    public override fun onPause() {
        super.onPause()

    }

    public override fun onStop() {
        super.onStop()
        BluetoothController.btHid?.unregisterApp()

        BluetoothController.hostDevice=null
        BluetoothController.btHid=null
    }


    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.select_device_activity_menu, menu)

        bluetoothStatus = menu?.findItem(R.id.ble_app_connection_status)
        autoPairMenuItem= menu?.findItem(R.id.action_autopair)

        screenOnMenuItem = menu?.findItem(R.id.action_screen_on)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        screenOnMenuItem?.isChecked = sharedPref.getBoolean(getString(R.string.screen_on_flag),false);
        Log.i("crown","jewel")
        autoPairMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Log.d("keyeventdown_tag","desc is - $event")

        if(rKeyboardSender !=null && event !=null) {

            var rvalue: Boolean? = false
            if (rvalue == true) return true

            else return super.onKeyDown(keyCode, event)
        }
        else return super.onKeyDown(keyCode, event)
    }


    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {


        Log.d("keyeventup_tag","desc is - $event")

        if(rKeyboardSender !=null && event !=null) {
            var rvalue: Boolean? = false
            rvalue = rKeyboardSender?.sendKeyboard(keyCode, event,modifier_checked_state)

            if (rvalue == true) return true


            else return super.onKeyDown(keyCode, event)

        }
        else return super.onKeyUp(keyCode, event)


    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.action_keyboard -> {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
            true
        }

        R.id.check_modifier_state -> {
            if(modifier_checked_state==1)
            {
                modifier_checked_state=0
                item.title="(N)"
                rKeyboardSender?.sendNullKeys()

            }

            else
            {
                modifier_checked_state=1
                item.title="(P)"

            }

            true
        }


        R.id.action_disconnect -> {

            BluetoothController.btHid?.disconnect(BluetoothController.hostDevice)
            bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
            bluetoothStatus?.tooltipText="App not connected via bluetooth"
            true
        }

        R.id.action_screen_on -> {
            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
            if(item.isChecked) {
                item.isChecked = false

                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

                with(sharedPref.edit())
                {
                    putBoolean(getString(R.string.screen_on_flag), false)
                    commit()
                }

            }
            else
            {
                item.isChecked=true
                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

                with(sharedPref.edit())
                {
                    putBoolean(getString(R.string.screen_on_flag), true)
                    commit()
                }

            }

            true
        }

        R.id.action_autopair -> {
            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
            if(item.isChecked) {
                item.isChecked = false
                BluetoothController.autoPairFlag=false

                with(sharedPref.edit())
                {
                    putBoolean(getString(R.string.auto_pair_flag), BluetoothController.autoPairFlag)
                    commit()
                }

            }
            else
            {
                item.isChecked=true
                BluetoothController.autoPairFlag=true
                if(BluetoothController.btHid?.getConnectionState(BluetoothController.mpluggedDevice)==0 && BluetoothController.mpluggedDevice!= null && BluetoothController.autoPairFlag ==true)
                {
                    BluetoothController.btHid?.connect(BluetoothController.mpluggedDevice)
                    //hostDevice.toString()
                }
                with(sharedPref.edit())
                {
                    putBoolean(getString(R.string.auto_pair_flag), BluetoothController.autoPairFlag)
                    commit()
                }

            }
            true
        }


        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



}