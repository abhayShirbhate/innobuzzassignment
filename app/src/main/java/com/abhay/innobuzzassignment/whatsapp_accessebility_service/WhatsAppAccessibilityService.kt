package com.abhay.innobuzzassignment.whatsapp_accessebility_service
import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.widget.Toast

class WhatsAppAccessibilityService : AccessibilityService() {

    private lateinit var broadcastReceiver : MyBrodcast


        inner class MyBrodcast : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Handle broadcast receiver's actions here

            Log.d("AccessibilityService!!", "WhatsApp Launched.")
            showToast("WhatsApp Launched.")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("AccessibilityService!!", "onStartCommand Launched.")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        Log.d("AccessibilityService!!", "Event Received: ${event.packageName}, Event Type: ${event.eventType}")

        if (event.packageName == "com.whatsapp" && event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // User opened WhatsApp
            Log.d("AccessibilityService!!", "WhatsApp Launched.")
            showToast("WhatsApp Launched.")
        }
    }

    override fun onCreate() {
        super.onCreate()
        broadcastReceiver = MyBrodcast()
//        val filter = IntentFilter("com.whatsapp")
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_PACKAGE_ADDED)
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED)
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED)
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED)
        filter.addAction(Intent.ACTION_PACKAGE_RESTARTED)
        filter.addDataScheme("package")
        registerReceiver(broadcastReceiver, filter)
    }

    override fun onInterrupt() {
        // Not used
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object{
        // Function to check if the accessibility service is enabled
        fun isAccessibilityServiceEnabled(context: Context): Boolean {
            val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            val enabledServices = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
            val serviceString = context.packageName + "/" + WhatsAppAccessibilityService::class.java.name
            return enabledServices?.contains(serviceString) == true
        }

        // Function to prompt the user to open Accessibility settings
        fun openAccessibilitySettings(context: Context) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            context.startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}
