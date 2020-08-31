# Android App Boilerplate: MVVM Architecture

A boilerplate for your Android App's Model-View-ViewModel architectural framework.

## Set up

Add `jcenter` to your root project's `build.gradle`

```
buildscript {
    repositories {
        jcenter()
        
    }
}
```

Then add this dependency to your app's `build.gradle`

```
implementation 'com.chabroncano.basemvvm:basemvvm:1.1.0'
```

For maven:

```
<dependency>
	<groupId>com.chabroncano.basemvvm</groupId>
	<artifactId>basemvvm</artifactId>
	<version>1.1.0</version>
	<type>pom</type>
</dependency>
```

## Guide

Simply extend your Activities and Fragments to `BaseMvvmActivity` and `BaseMvvmFragment`, respectively. These are abstract generic classes that require:
```
VM: ViewModel
DB: ViewDataBinding
```
These are recommended but optional. If you wish not to include any of these, put `Nothing` to indicate a non-existent ViewModel class or a dataBinding-generated UI. It also needs a res ID as a parameter, for example:
```
class MainActivity : BaseMvvmActivity<MainActivityViewModel, ActivityMainBinding>(R.layout.activity_main) {
```

### `init()`
You may treat this as the `onCreate()` of your Activity/Fragment, you can do all initial steps like dagger setup or firebase creation on this method. 
Note that if you made your ViewDataBinding optional, you need to manually call `setContentView` here.

### `setViewModelFactory()`
You need to return an instance of the `ViewModelProvider.Factory` needed to create a ViewModel

### `setViewModelClass()`
You need to return a `::class.java` instance of your ViewModel

### `onBind()`
You may now do your UI logic on this method with the assurance that the layout and viewModel you have provided are already instantiated. To access the UI, call `binding` object and to access your ViewModel, call `viewModel` object. Make sure to leave all the business logic to your ViewModel.

## License
```
Apache License
Version 2.0, January 2004
http://www.apache.org/licenses/
```

## Author
Charlotte Margaret Broncano | @chabroncano on Github and Medium | chabroncano@gmail.com
