package org.geeksforgeeks.cellularnetworkband

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButton = findViewById<Button>(R.id.button_1)

        mButton.setOnClickListener {
            val message = mGetNetworkClass(applicationContext)
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun mGetNetworkClass(context: Context): String? {
        val mConnectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val mInfo = mConnectivityManager.activeNetworkInfo
        if (mInfo == null || !mInfo.isConnected) return "-" // Not Connected
        if (mInfo.type == ConnectivityManager.TYPE_WIFI) return "WIFI" // Connected to Wifi
        if (mInfo.type == ConnectivityManager.TYPE_MOBILE) { // Connected to Mobile
            return when (mInfo.subtype) {
                TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN, TelephonyManager.NETWORK_TYPE_GSM -> "2G"
                TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP, TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3G"
                TelephonyManager.NETWORK_TYPE_LTE, TelephonyManager.NETWORK_TYPE_IWLAN, 19 -> "4G"
                TelephonyManager.NETWORK_TYPE_NR -> "5G"
                else -> "?"
            }
        }
        return "?"
    }
}