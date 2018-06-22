package com.darkkillen.maintenancemanager

interface OnMaintenance {

    fun onForceUpdate()
    fun onMaintenance()
    fun onUpdateAvailable()
    fun onUpToDate()

}