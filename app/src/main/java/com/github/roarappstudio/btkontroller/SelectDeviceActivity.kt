package com.github.roarappstudio.btkontroller

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.github.roarappstudio.btkontroller.extraLibraries.CustomGestureDetector
import com.github.roarappstudio.btkontroller.listeners.CompositeListener
import com.github.roarappstudio.btkontroller.listeners.GestureDetectListener
import com.github.roarappstudio.btkontroller.listeners.ViewListener
import com.github.roarappstudio.btkontroller.senders.KeyboardSender
import com.github.roarappstudio.btkontroller.senders.RadialSender2
import com.github.roarappstudio.btkontroller.senders.RelativeMouseSender
import com.github.roarappstudio.btkontroller.senders.SensorSender
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.find
import org.jetbrains.anko.sensorManager
import java.nio.charset.Charset


class SelectDeviceActivity: Activity(),KeyEvent.Callback {

    private var autoPairMenuItem : MenuItem? =null
    private var screenOnMenuItem : MenuItem? =null
    private var keyboardMenuItem:MenuItem?=null
    private var showEditTextMenuItem:MenuItem?=null
    private var keyboard_state_MenuItem:MenuItem?=null
//    private var wheelFeedbackMenu:MenuItem?=null

    private var bluetoothStatus : MenuItem? =null

    private lateinit var linearLayout: _LinearLayout
    private var sender: SensorSender? = null
    //private var  viewTouchListener : ViewListener? = null
    private var modifier_checked_state : Int =0
    private var  rMouseSender : RelativeMouseSender? = null

    private var rKeyboardSender : KeyboardSender? = null

    private var mRadialSender2:RadialSender2?=null

    private var editText:EditText?=null
    private  var btn_send:Button?=null
    private var wheelView: WheelPicker?=null
    private var InputView:View?=null

    @SuppressLint("ResourceType")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
    }

    fun getContext(): Context {
        return this
    }

    public override fun onStart() {
        super.onStart()


        editText=find<EditText>(R.id.editText)
        InputView=find<View>(R.id.input_view)

        bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
        bluetoothStatus?.tooltipText="App not connected via bluetooth"


        val sharedPref = this.getSharedPreferences("setting",Context.MODE_PRIVATE)


        BluetoothController.autoPairFlag= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        autoPairMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        screenOnMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.screen_on_flag),false)

        val _show_edit_text = sharedPref.getBoolean("showEditTextMenuItem", false)
        showEditTextMenuItem?.isChecked = _show_edit_text
        if (_show_edit_text)
            InputView!!.setVisibility(View.VISIBLE)
        else
            InputView!!.setVisibility(View.GONE)

        if(sharedPref.getBoolean(getString(R.string.screen_on_flag),false)) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val trackPadView = find<View>(R.id.mouseView)

        wheelView=find<WheelPicker>(R.id.wheel)
        wheelView?.setNewCenterColor(Color.LTGRAY)

        val button1=find<Button>(R.id.button1)
        val button2=find<Button>(R.id.button2)
        val button3=find<Button>(R.id.button3)
        val button4=find<Button>(R.id.button4)
         btn_send= find<Button>(R.id.btn_send)


        setANSIcode("")

        BluetoothController.init(this)

        BluetoothController.getSender { hidd, device ->
            Log.wtf("weiufhas", "Callback called")
            val mainHandler = Handler(getContext().mainLooper)

            mainHandler.post(object : Runnable{
                override fun run() {

                    rKeyboardSender= KeyboardSender(hidd,device)
                    mRadialSender2= RadialSender2(hidd,device)

                        wheelView?.setBluetoothRadialSender(mRadialSender2);
                        wheelView?.setNewCenterColor(Color.GREEN)

                    rKeyboardSender?.configSendString(
                        sharedPref.getInt("delay_time",Value.delay_time),
                        sharedPref.getBoolean("fast_mode_flag",false),
                        sharedPref.getBoolean("replace_newline_flag",false)
                    )

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
                    find<Button>(R.id.button5).setOnClickListener {
                        Thread({
                            rKeyboardSender!!.sendKeyboard_test2(16)
                            Thread.sleep(500)
                            rKeyboardSender!!.sendNullKeys()
                        }).start()
                    }

/*                    editText!!.setOnEditorActionListener() { v, actionId, event ->
                        if(actionId == EditorInfo.IME_ACTION_DONE){
                            var str=v.text;
                        }
                        false
                    }*/

                 btn_send!!.setOnClickListener {
                     btn_send!!.isClickable=false
                     var str=editText!!.text.toString()
                     if(str.length<1 || keyboard_state==0) {
                         rKeyboardSender!!.sendNullKeys()
                         btn_send!!.isClickable = true
                     }else{

/*
                       var  mRunnable  =  Runnable {
                             run {
                                 if(keyboard_state==1){
                                     rKeyboardSender!!.sendKeyboard(str)
                                 }else if(keyboard_state==2){
                                     rKeyboardSender!!.sendKeyboard(str,Charset.forName(ansi_code_flag))
                                 }
                                 mainHandler.post(object : Runnable{
                                     override fun run() {
                                         btn_send!!.isClickable=true
                                         editText!!.setText("")
                                     }
                                 })
                             }
                         }
                       Thread(mRunnable).start()
*/

                         Thread({
                             if(keyboard_state==1){
                                 rKeyboardSender!!.sendString(str)
                             }else if(keyboard_state==2){
                                 rKeyboardSender!!.sendString(str,Charset.forName(ansi_code_flag))
                             }
                             val mainHandler = Handler(getContext().mainLooper)

                             mainHandler.post(object : Runnable {
                                 override fun run() {
                                     btn_send!!.isClickable=true
                                     editText!!.setText("")
                                 }
                             })

                         }).start()


                     }
                 }

                }

            })

            Log.i("TAGddd", Thread.currentThread().getName());

        }

        BluetoothController.getDisconnector{
            Log.e("BluetoothController","getDisconnector")
//            bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
//            bluetoothStatus?.tooltipText="App not connected via bluetooth"
//            wheelView?.setNewCenterColor(Color.LTGRAY)

            val mainHandler = Handler(getContext().mainLooper)

            mainHandler.post(object : Runnable {
                override fun run() {
                    bluetoothStatus?.icon=getDrawable(R.drawable.ic_action_app_not_connected)
                    bluetoothStatus?.tooltipText="App not connected via bluetooth"
                    wheelView?.setNewCenterColor(Color.LTGRAY)
                }
            })
        }


    }

    protected override fun onResume() {
        super.onResume()
        val sharedPref = this.getSharedPreferences("setting",Context.MODE_PRIVATE)
        wheelView?.config_haptic(
            sharedPref.getInt("haptic_use_flag",Value.haptic_use_flag),
            sharedPref.getInt("wheel_haptic_min_time_falg",Value.wheel_haptic_minimum_time_flag),
            sharedPref.getInt("wheel_haptic_vibrate_time_flag",Value.wheel_haptic_vibrate_time_flag),
            sharedPref.getInt("wheel_haptic_skip_count_flag",Value.wheel_haptic_skip_count_flag)
        )

        rKeyboardSender?.configSendString(
            sharedPref.getInt("delay_time",Value.delay_time),
            sharedPref.getBoolean("fast_mode_flag",false),
            sharedPref.getBoolean("replace_newline_flag",false)
        )

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
        val sharedPref = this.getSharedPreferences("setting",Context.MODE_PRIVATE)

        screenOnMenuItem?.isChecked = sharedPref.getBoolean(getString(R.string.screen_on_flag),false);
        Log.i("crown","jewel")
        autoPairMenuItem?.isChecked= sharedPref.getBoolean(getString(R.string.auto_pair_flag),false)

        keyboardMenuItem=menu?.findItem(R.id.keyboard_state)

        showEditTextMenuItem=menu?.findItem(R.id.action_show_edittext)
        showEditTextMenuItem?.isChecked = sharedPref.getBoolean("showEditTextMenuItem",false)
//        wheel_haptic_min_time_falg=sharedPref.getInt("wheel_haptic_min_time_falg",0)
//        wheelFeedbackMenu=menu?.findItem(R.id.action_wheel_haptic)
//        wheelFeedbackMenu?.title=getString(R.string.wheel_haptic_min)+" "+wheel_haptic_min_time_falg

//        keyboard_state_MenuItem=menu?.findItem(R.id.keyboard_state)

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

        if(keyboard_state==1||keyboard_state==2){
            return  super.onKeyUp(keyCode, event)
        }

        if(rKeyboardSender !=null && event !=null) {
            var rvalue: Boolean? = false
            rvalue = rKeyboardSender?.sendKeyboard(keyCode, event,modifier_checked_state)

            if (rvalue == true) return true

            else return super.onKeyDown(keyCode, event)

        }
        else return super.onKeyUp(keyCode, event)


    }






    fun selectCode(){
        // 得到全部字符集
        val map: Map<String, Charset> =
            Charset.availableCharsets()
        val set =   map.entries
        var items=Array<String>(set.size){""}

        var index=0
        for (( key, value) in set) {
            items[index]=key
            index++
//            println( "$key=$value" )
        }

//        val items = arrayOf("1","2","3")
        val dialog = AlertDialog.Builder(this@SelectDeviceActivity)
        dialog.setTitle(getString(R.string.select_ansi_code)).setItems(items){
                _,which ->
            setANSIcode(items[which])
        }.create().show()
        
    }

/*    var ANSIcode_flag=0
    fun setANSIcode( i:Int){
        val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
        if(i>=0) {
            ANSIcode_flag=i
            with(sharedPref.edit())
            {
                putInt("ANSIcode_flag", i)
                commit()
            }
        }else{
            ANSIcode_flag=sharedPref.getInt("ANSIcode_flag",0)
        }
    }*/
    var ansi_code_flag="GBK"
    //   ansi_code_flag实际上标记了字符集（charset）
    fun setANSIcode( i:String){
        val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
        if(i.length>0) {
            ansi_code_flag=i
            with(sharedPref.edit())
            {
                putString("ansi_code_flag", i)
                commit()
            }
        }else{
            ansi_code_flag=sharedPref.getString("ansi_code_flag","GBK")
        }
    }

      var keyboard_state=0

/*      旧的切换
//      state=0 隐藏键盘和文本框    =1 键盘直接输入   =2 字符串输入
    fun refreshInput(et: EditText,keyboardMenuItem:MenuItem) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if(keyboard_state==1){
//            et.setImeOptions(EditorInfo.IME_ACTION_DONE);
            imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
            keyboardMenuItem.title  =getString(R.string.keyboard_state_1)
            et.visibility=View.GONE
        }else if(keyboard_state==2){
            et.visibility=View.VISIBLE
            et.requestFocus()
//            et.setImeOptions(EditorInfo.IME_ACTION_DONE);
            imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
            keyboardMenuItem.title=getString(R.string.keyboard_state_2)
        }else{
            keyboard_state=0
            val v = window.peekDecorView()
            if (null != v) {
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
            et.visibility=View.GONE
            keyboardMenuItem.title=getString(R.string.keyboard_state_0)
        }
    }

*/
    fun refresh_input(){
        if(keyboard_state==1){
            keyboardMenuItem?.tooltipText=getString(R.string.keyboard_tip_unicode)
            keyboardMenuItem?.icon=getDrawable(R.drawable.ic_icon_unicode)
            editText?.setEnabled(true)
            btn_send?.text=getString(R.string.button_send)
        }else if(keyboard_state==2){
            keyboardMenuItem?.tooltipText=getString(R.string.keyboard_tip_ansi)
            keyboardMenuItem?.icon=getDrawable(R.drawable.ic_icon_ansi)
            editText?.setEnabled(true)
            btn_send?.text=getString(R.string.button_send)
        }else{
            keyboard_state=0
            keyboardMenuItem?.tooltipText=getString(R.string.keyboard_tip_hid)
            keyboardMenuItem?.icon=getDrawable(R.drawable.ic_icon_hid)
            editText?.setEnabled(false)
            btn_send?.text=getString(R.string.button_release)
        }

/*    if(showEditTextMenuItem!!.isChecked)
        InputView!!.setVisibility(View.VISIBLE)
    else
        InputView!!.setVisibility(View.GONE)*/

    }


// 设置转动触发反馈的时间限制。保证转速快时，仍然有齿轮感，而不是持续震动。此部分设置已经转移
/*   var wheel_haptic_min_time_falg=0
    fun set_wheel_haptic(i:Int){
        val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
        if(i>-1) {
            var j=i
            if(i>3000){
                j=3000
               Log.w("set_wheel_haptic","input $i > 3000")
            }
            Log.d("set_wheel_haptic","input $i")
            wheel_haptic_min_time_falg=i
            with(sharedPref.edit())
            {
                putInt("wheel_haptic_min_time_falg", i)
                commit()
            }
            wheelFeedbackMenu?.title=getString(R.string.wheel_haptic_min)+" "+wheel_haptic_min_time_falg
            wheelView?.setWheel_haptic_minimum_time(sharedPref.getInt("wheel_haptic_min_time_falg",0))

        }
    }

    fun set_wheel_haptic(){
         val items = arrayOf("0"
            , "80","100", "120", "140","160",
             "180","200","220","240","260"
      )
        val dialog = AlertDialog.Builder(this@SelectDeviceActivity)
        dialog.setTitle(getString(R.string.wheel_haptic_min_time)).setItems(items){
                _,which ->
            set_wheel_haptic(items[which].toInt())
        }.create().show()

    }

*/


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_haptic_settings -> {
           startActivity(Intent(this,
               HapticConfigActivity::class.java))
            true
        }

        R.id.action_show_edittext -> {
            item.isChecked = !item.isChecked
            val sharedPref = this?.getSharedPreferences("setting", Context.MODE_PRIVATE)
            with(sharedPref.edit())
            {
                putBoolean("showEditTextMenuItem", item.isChecked)
                commit()
            }
            if (item.isChecked)
                InputView!!.setVisibility(View.VISIBLE)
            else{
                InputView!!.setVisibility(View.GONE)
                keyboard_state=0;
                refresh_input()
            }

            true
        }

        R.id.action_select_ansi_code ->{
            selectCode()
            true
        }

/*        R.id.action_wheel_haptic ->{
            set_wheel_haptic()
            true
        }*/

        R.id.action_keyboard -> {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
            true
        }

        R.id.keyboard_state -> {
            if(showEditTextMenuItem!!.isChecked){
                keyboard_state++;
            }else{
                keyboard_state=0;
            }

//            editText?.let { refreshInput(it,item) }

            //  只切换模式，不切换键盘状态
            //  STATE=0  usb-hid keycode  =1 unicode   =2 ansi
            refresh_input()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)

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
            val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
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
            val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
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