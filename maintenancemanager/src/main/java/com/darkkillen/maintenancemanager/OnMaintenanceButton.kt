package com.darkkillen.maintenancemanager

interface OnMaintenanceButton {

    fun onForceUpdateButtonClick(url: String?)
    fun onMaintenanceButtonClick()
    fun onUpdateButtonClick(url: String?)
    fun onUpdateCancelClick()
    fun onUpToDate()

}