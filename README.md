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
implementation 'com.chabroncano.basemvvm:basemvvm:1.2.0'
```

For maven:

```
<dependency>
	<groupId>com.chabroncano.basemvvm</groupId>
	<artifactId>basemvvm</artifactId>
	<version>1.2.0</version>
	<type>pom</type>
</dependency>
```

## Guide

Simply extend your Activities, Fragments, and Views to `BaseMvvmActivity`, `BaseMvvmFragment`, and `BaseMvvmView`, respectively. These are abstract generic classes.

Base Activity and Base Fragment require:
```
VM: ViewModel
DB: ViewDataBinding
```
These are recommended but optional. If you wish not to include any of these, put `Nothing` to indicate a non-existent ViewModel class or a dataBinding-generated UI. It also needs a res ID as a parameter, for example:
```
class MainActivity : BaseMvvmActivity<MainActivityViewModel, ActivityMainBinding>(R.layout.activity_main) {
```

Base View only requires `VM: ViewModel` class because it is already a View, if you wish to include a databinding inside a View, you can freely do so by attaching it to the view parent:
```
DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.view_sample_text, parent as ViewGroup, true)
```
but you need to copy the parent's layout params to make sure this custom view params work as intended:
```
binding.root.layoutParams = this.layoutParams
```

## Demo App

The demo app showcases the ff:
1. An activity with a ViewModel
2. A fragment without a ViewModel - ViewModels are optional but not recommended because this is built for the MVVM Architecture. If you don't want to add a ViewModel, it would be better if you just extend it the usual way, AppCompatActivity/Fragment
3. A view with its own ViewModel independent from its parent

---

## For BaseMvvmActivity and BaseMvvmFragment

### `init()`
You may treat this as the `onCreate()` of your Activity/Fragment, you can do all initial steps like dagger setup or firebase creation on this method. 
Note that if you made your ViewDataBinding optional, you need to manually call `setContentView` here.

### `setViewModelFactory()`
You need to return an instance of the `ViewModelProvider.Factory` needed to create a ViewModel

### `setViewModelClass()`
You need to return a `::class.java` instance of your ViewModel

### `onBind()`
You may now do your UI logic on this method with the assurance that the layout and viewModel you have provided are already instantiated. To access the UI, call `binding` object and to access your ViewModel, call `viewModel` object. Make sure to leave all the business logic to your ViewModel.

## For BaseMvvmView

### `setViewModelClass()`
You need to return a `::class.java` instance of your ViewModel

### `initView()`
You may now do your UI logic on this method. You can also do databinding here as shown in the example code snippet above. You may also check the View attributes here.

### `setObservers()`
The ViewModel has already been created here, but you need to set up the observers before calling the methods in the ViewModel.

### `onViewModelCreated()`
You may now call view model methods here.

---

## License
```
Apache License
Version 2.0, January 2004
http://www.apache.org/licenses/
```

## Author
Charlotte Margaret Broncano | @chabroncano on Github and Medium | chabroncano@gmail.com
