# AnalogClockView [![](https://jitpack.io/v/Hemu43362/AnalogClockView.svg)](https://jitpack.io/#Hemu43362/AnalogClockView) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Output:
<image src="https://user-images.githubusercontent.com/59821647/117830707-836ea280-b291-11eb-8119-5e9bda184342.jpg" width="30%" height="auto" align="left"/>
<image src="https://user-images.githubusercontent.com/59821647/117830837-a26d3480-b291-11eb-8197-0c0f93cab3f8.jpg" width="30%" height="auto" align="left"/>
<image src="https://user-images.githubusercontent.com/59821647/117830862-a9944280-b291-11eb-8082-2abe41b4a479.jpg" width="30%" height="auto"/>

### Usage:

1. Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  2. Add the dependency in app-level build.gradle
  
  ```
 dependencies {
	        implementation 'com.github.Hemu43362:AnalogClockView:0.0.1'
	}
  ```
  
  3. Layout implementation
  
  ```xml
    <com.hemantpatel.clock_library.AnalogClockView
        android:id="@+id/clock_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:pointsColor="#EAC53E"
        app:dialColor="#CDFFD2"
        app:secondHandColor="#FD602F"
        app:minuteHandColor="#7789EF"
        app:hourHandColor="#29BC65"
        app:secondHandWidth="5"
        app:minuteHandWidth="10"
        app:hourHandWidth="15"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

4. Don't forget to call <strong>startClock()</strong> in OnStart() and <strong>stopClock()</strong> in OnStop()

```kotlin
   // get reference of AnalogClockview
   private val mClockView: AnalogClockView
        get() = findViewById(R.id.clock_view)
```	
	
```kotlin
   override fun onStart() {
        super.onStart()
        mClockView.startClock()
    }
```

```kotlin
   override fun onStop() {
        super.onStop()
        mClockView.stopClock()
    }
```

### Attribute details:

| Atrribute | Properties | Type | Default Value̥|
| --------- | ---------- | ---- | -------------|
| pointsColor | color of points which indicates the numbers | Color | Color.WHITE |
| dialColor | color of dial of watch | Color | Color.GRAY |
| secondHandColor | color of second hand | Color | Color.RED |
| minuteHandColor | color of minute hand | Color | Color.YELLOW |
| hourHandColor | color of hour hand | Color | Color.GREEN |
| secondHandWidth | width of second hand | Float | 8f |
| minuteHandWidth | width of minute hand | Float | 12f |
| hourHandWidth | width of hour hand | Float | 15f |

### Changelog:
**0.0.1**
 - Initial release
 - added attributes for customize clock hand, dial and points

### License:
    Copyright 2021 HemantPatel
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


