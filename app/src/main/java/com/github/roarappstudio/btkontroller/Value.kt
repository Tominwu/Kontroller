package com.github.roarappstudio.btkontroller

import android.view.HapticFeedbackConstants

class Value {

    companion object{
        val wheel_haptic_vibrate_value_items= arrayOf(
            HapticFeedbackConstants.LONG_PRESS,
            HapticFeedbackConstants.CONTEXT_CLICK,
            HapticFeedbackConstants.VIRTUAL_KEY,
            HapticFeedbackConstants.KEYBOARD_TAP,
            HapticFeedbackConstants.KEYBOARD_PRESS,
            HapticFeedbackConstants.CLOCK_TICK
        )

        val haptic_use_flag=0
        val wheel_haptic_minimum_time_flag=0
        val wheel_haptic_vibrate_time_flag=HapticFeedbackConstants.LONG_PRESS
        val wheel_haptic_skip_count_flag=30

        val wheel_color_flag=true

        val haptic_use_name="haptic_use_flag"
        val wheel_haptic_minimum_time_name="wheel_haptic_minimum_time_flag"
        val wheel_haptic_vibrate_time_name="wheel_haptic_vibrate_time_flag"
        val wheel_haptic_skip_count_name="wheel_haptic_skip_count_flag"

        val WINDOWS_NEW_LINE=1
        val UNIX_NEW_LINE=0
        val OSX_NEW_LINE=2

        val delay_time=13

    }

}