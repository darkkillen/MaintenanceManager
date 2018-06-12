package com.darkkillen.maintenancemanager

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.Log

class MaintenanceManager private constructor(builder: MainternanceBuilder) {

    private val TAG = "MaintenanceManager"
    private var context: Context? = null
    private var versionCode = 0
    private var callback: onMaintenanceCallback? = null
    private var FORCE_UPDATE_TITLE: String? = null
    private var FORCE_UPDATE_MESSAGE: String? = null
    private var FORCE_UPDATE_BUTTON: String? = null
    private var MAINTENANCE_TITLE: String? =  null
    private var MAINTENANCE_MESSAGE: String? = null
    private var MAINTENANCE_BUTTON: String? = null
    private var NEW_VERSION_TITLE: String? = null
    private var NEW_VERSION_MESSAGE: String? = null
    private var NEW_VERSION_UPDATE: String? = null
    private var NEW_VERSION_CANCEL: String? = null

    init {
        this.context = builder.context
        this.versionCode = builder.versionCode
        this.callback = builder.callback
        this.FORCE_UPDATE_TITLE = builder.FORCE_UPDATE_TITLE
        this.FORCE_UPDATE_MESSAGE = builder.FORCE_UPDATE_MESSAGE
        this.FORCE_UPDATE_BUTTON = builder.FORCE_UPDATE_BUTTON
        this.MAINTENANCE_TITLE = builder.MAINTENANCE_TITLE
        this.MAINTENANCE_MESSAGE = builder.MAINTENANCE_MESSAGE
        this.MAINTENANCE_BUTTON = builder.MAINTENANCE_BUTTON
        this.NEW_VERSION_TITLE = builder.NEW_VERSION_TITLE
        this.NEW_VERSION_MESSAGE = builder.NEW_VERSION_MESSAGE
        this.NEW_VERSION_UPDATE = builder.NEW_VERSION_UPDATE
        this.NEW_VERSION_CANCEL = builder.NEW_VERSION_CANCEL
    }

    class MainternanceBuilder(
            internal val context: Context, internal val versionCode: Int) {

        internal var callback: onMaintenanceCallback? = null
        internal var FORCE_UPDATE_TITLE: String? = "Update Required"
        internal var FORCE_UPDATE_MESSAGE: String? = "Please update to latest version."
        internal var FORCE_UPDATE_BUTTON: String? = "Update"
        internal var MAINTENANCE_TITLE: String? = "Sorry"
        internal var MAINTENANCE_MESSAGE: String? = "Under Maintenance. Try again later."
        internal var MAINTENANCE_BUTTON: String? = "Exit"
        internal var NEW_VERSION_TITLE: String? = "Update Available"
        internal var NEW_VERSION_MESSAGE: String? = "New version available now."
        internal var NEW_VERSION_UPDATE: String? = "Update"
        internal var NEW_VERSION_CANCEL: String? = "Later"

        fun callback(callback: onMaintenanceCallback?): MainternanceBuilder {
            this.callback = callback
            return this
        }

        fun setForceUpdateMessage(title: String?, message: String?, button: String?): MainternanceBuilder {
            FORCE_UPDATE_TITLE = title
            FORCE_UPDATE_MESSAGE = message
            FORCE_UPDATE_BUTTON = button
            return this
        }

        fun setMaintenanceMessage(title: String?, message: String?, button: String?): MainternanceBuilder {
            MAINTENANCE_TITLE = title
            MAINTENANCE_MESSAGE = message
            MAINTENANCE_BUTTON = button
            return this
        }

        fun setNewVersionMessage(title: String?, message: String?, buttonUpdate: String?, buttonCancel: String?): MainternanceBuilder {
            NEW_VERSION_TITLE = title
            NEW_VERSION_MESSAGE = message
            NEW_VERSION_UPDATE = buttonUpdate
            NEW_VERSION_CANCEL = buttonCancel
            return this
        }


        fun build(): MaintenanceManager {
            return MaintenanceManager(this)
        }

    }

    fun maintenaceResult(isForceUpdate: Boolean?, isMaintain: Boolean?, latestVersionCode: Int?) {

        if (callback == null) throw MaintenanceException("Callback not found")

        if (isForceUpdate == null || latestVersionCode == null) throw MaintenanceException("ForceUpdate not found.")
        if (isForceUpdate && versionCode < latestVersionCode) {
            showDialog(Constant.FORCE_UPDATE)
            return
        }

        if (isMaintain == null) throw MaintenanceException("Maintenance not found.")
        if (isMaintain) {
            showDialog(Constant.UNDER_MAINTENANCE)
            return
        }

        if (versionCode < latestVersionCode) {
            showDialog(Constant.NEW_VERSION_AVAILABLE)
            return
        }

        showDialog(Constant.UP_TO_DATE)
    }

    private fun showDialog(condition: Int) {
        if (!isContext()) {
            Log.e(TAG,"Please init MaintenanceManager with context.")
            throw MaintenanceException("No context found")
        }
        when(condition) {

            Constant.FORCE_UPDATE          -> {
                val dialogBuilder = AlertDialog.Builder(context!!)
                dialogBuilder.setTitle(FORCE_UPDATE_TITLE)
                dialogBuilder.setMessage(FORCE_UPDATE_MESSAGE)
                dialogBuilder.setPositiveButton(FORCE_UPDATE_BUTTON, DialogInterface.OnClickListener { dialog, which ->
                    callback?.onForceUpdateButtonClick()
                })
                dialogBuilder.setCancelable(false)

                val dialog = dialogBuilder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }

            Constant.UNDER_MAINTENANCE     -> {
                val dialogBuilder = AlertDialog.Builder(context!!)
                dialogBuilder.setTitle(MAINTENANCE_TITLE)
                dialogBuilder.setMessage(MAINTENANCE_MESSAGE)
                dialogBuilder.setPositiveButton(MAINTENANCE_BUTTON, DialogInterface.OnClickListener { dialog, which ->
                    callback?.onMaintenanceButtonClick()
                })
                dialogBuilder.setCancelable(false)

                val dialog = dialogBuilder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }

            Constant.NEW_VERSION_AVAILABLE -> {
                val dialogBuilder = AlertDialog.Builder(context!!)
                dialogBuilder.setTitle(NEW_VERSION_TITLE)
                dialogBuilder.setMessage(NEW_VERSION_MESSAGE)
                dialogBuilder.setPositiveButton(NEW_VERSION_UPDATE, DialogInterface.OnClickListener { dialog, which ->
                    callback?.onUpdateButtonClick()
                })
                dialogBuilder.setNegativeButton(NEW_VERSION_CANCEL, DialogInterface.OnClickListener { dialog, which ->
                    callback?.onUpdateCancelClick()
                })
                dialogBuilder.setOnCancelListener {
                    callback?.onUpdateCancelClick()
                }
                dialogBuilder.setCancelable(true)

                val dialog = dialogBuilder.create()
                dialog.setCanceledOnTouchOutside(true)
                dialog.show()
            }

            Constant.UP_TO_DATE            -> {
                callback?.onUpToDate()
            }
        }
    }

    private fun isContext(): Boolean {
        return context != null
    }

}