package com.darkkillen.maintenancemanager

interface OnMaintenanceButton {

    fun onForceUpdateButtonClick()
    fun onMaintenanceButtonClick()
    fun onUpdateButtonClick()
    fun onUpdateCancelClick()
    fun onUpToDate()

}