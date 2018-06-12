package com.darkkillen.maintenancemanager

interface onMaintenanceCallback {

    fun onForceUpdateButtonClick()
    fun onMaintenanceButtonClick()
    fun onUpdateButtonClick()
    fun onUpdateCancelClick()
    fun onUpToDate()

}