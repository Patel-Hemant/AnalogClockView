# AnalogClockView [![](https://jitpack.io/v/Hemu43362/AnalogClockView.svg)](https://jitpack.io/#Hemu43362/AnalogClockView)

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
