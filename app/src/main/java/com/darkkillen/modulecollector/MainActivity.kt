package com.darkkillen.modulecollector

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.darkkillen.maintenancemanager.MaintenanceManager
import com.darkkillen.maintenancemanager.OnMaintenanceButton

class MainActivity : AppCompatActivity(), OnMaintenanceButton {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initial()
    }

    private fun initial() {
        MaintenanceManager.MaintenanceBuilder(this, BuildConfig.VERSION_CODE)
                .callback(this)
                .build()
                .maintenaceResult(true, false, 2)
    }

    override fun onForceUpdateButtonClick(url: String?) {
        Toast.makeText(this, "onForceUpdateButtonClick", Toast.LENGTH_SHORT).show()
    }

    override fun onMaintenanceButtonClick() {
        Toast.makeText(this, "onMaintenanceButtonClick", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateButtonClick(url: String?) {
        Toast.makeText(this, "onUpdateButtonClick", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateCancelClick() {
        Toast.makeText(this, "onUpdateCancelClick", Toast.LENGTH_SHORT).show()
    }

    override fun onUpToDate() {
        Toast.makeText(this, "onUpToDate", Toast.LENGTH_SHORT).show()
    }
}
