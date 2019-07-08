package com.stickmanmobile.engineroom.kotlinarchitecture.util

import android.os.Build

class DeviceHelper {

    companion object {
        fun isOrAboveMarshmallow(): Boolean {
            return Build.VERSION.SDK_INT >= 23;
        }
    }
}