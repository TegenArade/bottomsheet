# Android BottomSheet

This is an an extension function on Activity to show bottom sheet.



## Extension function AppCompatActivity.showBottomSheetDialog

    This extension function is used to show bottom sheet from any view which a parent activity.
    @param titleText : Title text for the bottom sheet content
    @param subTitleText: subtitle/description for  the bottom sheet content
    @param viewResId: is layout resourceID which will be inflated in to the body of the bottom sheet
    @param configuration: optional configuration property  to overide the default behaviors of the bottom sheet
    @param callback :  this callback is used to get the current state of the bottom sheet on the view (if it is sliding/ expanded collapsed etc ...)

## class BottomSheetConfiguration
    
    This is a configuration object used to set configurable properties of the bottom sheet.
   
    @param header: is used to set properties for the header section of the bottom sheet.
    currently we can override  headerTitleAppearance, headerSubTitleAppearance
    
    @param body : is used to set the
    currently there its not configured to overide anything
    
    @param behavior: is used to override the behaviour of the bottom sheet
    currently it can override
     peekRatio with default value of  0.5f
     skipCollapsed with default value of false
     isHideable with default value of true
     dismissWithAnimation with default value of true
     cancelable with default value of true
     cancelableOnTOuchOutside with default value of true
    
 
## Configuration
Add this module as a dependency to your app then in any Activity/Fragment open bottom sheet like this
```kotlin

  activity.showBottomSheetDialog(
                titleText = "titleText",
                subTitleText = "subTitleText",
                viewResId = R.layout.small_item,
                callback = bottomSheetCallback
            )

```
or

```kotlin

  activity.showBottomSheetDialog(
                  titleText = "titleText",
                  subTitleText = "subTitleText",
                  body = bodyView,
                  callback = bottomSheetCallback
              )

```