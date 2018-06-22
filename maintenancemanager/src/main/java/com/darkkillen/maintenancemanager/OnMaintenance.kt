package com.darkkillen.maintenancemanager

interface OnMaintenance {

    fun onForceUpdate(url: String?)
    fun onMaintenance()
    fun onUpdateAvailable(url: String?)
    fun onUpToDate()

}