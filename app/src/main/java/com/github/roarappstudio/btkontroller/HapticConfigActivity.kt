package com.github.roarappstudio.btkontroller

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.find


class HapticConfigActivity : Activity() {

    private var text_haptic_use: Button ?=null
    private var edit_wheel_haptic_min_time:EditText?=null
    private var edit_wheel_haptic_skip_count:EditText?=null
//    private var edit_wheel_haptic_vibrate_time:EditText?=null
    private var text_wheel_haptic_vibrate_time:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haptic_config)
        haptic_use_items = arrayOf(getString(R.string.haptic_use_system),getString(R.string.haptic_use_mandatory),getString(R.string.haptic_use_null))


        text_haptic_use= find<Button>(R.id.text_haptic_use)
        text_haptic_use!!.setOnClickListener {

                      val dialog = AlertDialog.Builder(this)
            dialog.setTitle(getString(R.string.haptic_use)).setItems(haptic_use_items){
                    _,which ->
                text_haptic_use!!.setText(haptic_use_items[which])
                haptic_use_flag=which
            }.create().show()
        }

        text_wheel_haptic_vibrate_time= find<Button>(R.id.text_wheel_haptic_vibrate_time)
        text_wheel_haptic_vibrate_time!!.setOnClickListener {

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(getString(R.string.wheel_haptic_vib)).setItems(wheel_haptic_vibrate_name_items){
                    _,which ->
                text_wheel_haptic_vibrate_time!!.setText(wheel_haptic_vibrate_name_items[which])
                wheel_haptic_vibrate_time_flag=Value.wheel_haptic_vibrate_value_items[which]
            }.create().show()
        }

        edit_wheel_haptic_min_time=find<EditText>(R.id.edit_wheel_haptic_min_time)

        edit_wheel_haptic_skip_count=find<EditText>(R.id.edit_wheel_haptic_skip_count)

//        edit_wheel_haptic_vibrate_time=find<EditText>(R.id.edit_wheel_haptic_vibrate_time)
        load()

    }

    override fun onPause() {
        super.onPause()
        save()
    }

    fun save(){
        var v=edit_wheel_haptic_skip_count!!.text.toString().toInt()

        if(v>=0){
            if(v>3000)
                v=3000
            wheel_haptic_skip_count_flag=v
        }


        v=edit_wheel_haptic_min_time!!.text.toString().toInt()
        if(v>=0){
            if (v>3000)
                v=3000
            wheel_haptic_min_time_falg=v
        }

//        v=edit_wheel_haptic_vibrate_time!!.text.toString().toInt()


        val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
        with(sharedPref.edit())
        {
            putInt("haptic_use_flag", haptic_use_flag)
            putInt("wheel_haptic_vibrate_time_flag",wheel_haptic_vibrate_time_flag)
            putInt("wheel_haptic_skip_count_flag",wheel_haptic_skip_count_flag)
            putInt("wheel_haptic_min_time_falg",wheel_haptic_min_time_falg)

            commit()
        }

        load()
    }

    fun load(){
        val sharedPref = this?.getSharedPreferences("setting",Context.MODE_PRIVATE)
        haptic_use_flag=sharedPref.getInt("haptic_use_flag",haptic_use_flag)
        text_haptic_use!!.setText(haptic_use_items[haptic_use_flag])

        wheel_haptic_skip_count_flag=sharedPref.getInt("wheel_haptic_skip_count_flag",wheel_haptic_skip_count_flag)
        edit_wheel_haptic_skip_count!!.setText(wheel_haptic_skip_count_flag.toString())

        wheel_haptic_min_time_falg=sharedPref.getInt("wheel_haptic_min_time_falg",wheel_haptic_min_time_falg)
        edit_wheel_haptic_min_time!!.setText(wheel_haptic_min_time_falg.toString())

        wheel_haptic_vibrate_time_flag=sharedPref.getInt("wheel_haptic_vibrate_time_flag",wheel_haptic_vibrate_time_flag)
//        edit_wheel_haptic_vibrate_time!!.setText(wheel_haptic_vibrate_time_flag)
        text_wheel_haptic_vibrate_time!!.setText(wheel_haptic_vibrate_name_items[Value.wheel_haptic_vibrate_value_items.indexOf(wheel_haptic_vibrate_time_flag)])

    }


    var haptic_use_flag = Value.haptic_use_flag
    var wheel_haptic_min_time_falg = Value.wheel_haptic_minimum_time_flag
    var wheel_haptic_skip_count_flag =
        Value.wheel_haptic_skip_count_flag
    var wheel_haptic_vibrate_time_flag =
        Value.wheel_haptic_vibrate_time_flag


    var haptic_use_items: Array<String> = arrayOf("default", "no", "awlays")

    var wheel_haptic_vibrate_name_items= arrayOf(
        "LONG_PRESS",
        "CONTEXT_CLICK",
        "VIRTUAL_KEY",
        "KEYBOARD_TAP",
        "KEYBOARD_PRESS",
        "CLOCK_TICK"
    )



}
