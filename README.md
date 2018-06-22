# MaintenanceManager
Library for handle updating application.

## Feature
* show force update dialog
* show maintenance dialog
* show update available dialog

## Setup

### Gradle
```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

### Dependency
```groovy
dependencies {
	   implementation 'com.github.darkkillen:MaintenanceManager:v1.0.2'
	}
```

## Quick Start

### Implement callback
```kotlin
class MainActivity : AppCompatActivity(), OnMaintenanceButton {

    override fun onForceUpdateButtonClick() {
        
    }

    override fun onMaintenanceButtonClick() {
        
    }

    override fun onUpdateButtonClick() {
        
    }

    override fun onUpdateCancelClick() {
        
    }

    override fun onUpToDate() {
        
    }
}
```
### or no dialog callback
```kotlin
class MainActivity : AppCompatActivity(), OnMaintenance {

    override fun onForceUpdate() {
        
    }

    override fun onMaintenance() {
        
    }

    override fun onUpdateAvailable() {
        
    }

    override fun onUpToDate() {
        
    }

}
```

### Init
set callback one of these option [OnMaintenanceButton, OnMaintenance]

```kotlin
MaintenanceManager.MainternanceBuilder(context, BuildConfig.VERSION_CODE)
                .callback(this)
                .build()
                .maintenaceResult({isForceUpdate}, {isMaintain}, {versionCode})
```

### input
* isForceUpdate: Boolean // is force update required.
* isMaintain: Boolean // is now maintenance.
* versionCode: Int // versionCode of current version.

## Configuration

### Full configuration

```kotlin
        MaintenanceManager.MainternanceBuilder(this, BuildConfig.VERSION_CODE)
                .callback(this)
                .setForceUpdateMessage("title", "message", "buttonUpdate")
                .setMaintenanceMessage("title", "message", "buttonExit")
                .setNewVersionMessage("title", "message", "buttonUpdate", "buttonLater")
		.setUpdateUrl("http://www.google.com")
                .build()
                .maintenaceResult({isForceUpdate}, {isMaintain}, {versionCode})
```
### Default

* FORCE_UPDATE_TITLE = "Update Required"
* FORCE_UPDATE_MESSAGE = "Please update to latest version."
* FORCE_UPDATE_BUTTON = "Update"
* MAINTENANCE_TITLE = "Sorry"
* MAINTENANCE_MESSAGE = "Under Maintenance. Try again later."
* MAINTENANCE_BUTTON = "Exit"
* NEW_VERSION_TITLE = "Update Available"
* NEW_VERSION_MESSAGE = "New version available now."
* NEW_VERSION_UPDATE = "Update"
* NEW_VERSION_CANCEL = "Later"

## Contributions
Feel free to create issues / pull requests.

## License
```
MaintenanceManager library for Android
Copyright (c) 2018 Sanphop Thangpiyathumrong (http://github.com/darkkillen).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
