# PlusMinusNumView

![Preview](https://i.ibb.co/NxJ1Xgv/Screenshot-from-2021-09-15-13-46-01.png)

*Simple widget that has a number with plus button to increase its value 
and a minus button to decrease it

*Xml styleable options:
- maxVal(float): maximum value
- minVal(float): minimum value
- step(float): step value
- title(string): title of widget
- valueTextAppearance(reference): text appearance of value TextView

#XML layout usage

```XML
<ahmad.egypt.plusminusnumview.PlusMinusNumView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf ="parent"
    app:valueTextAppearance="?android:attr/textAppearanceLarge"
    app:maxVal="6"
    app:minVal="1"
    app:step="1"
    android:id="@+id/plus_minus_num_view"
    app:title="Distance"
/>
```

#Activity usage

```Kotlin
//Usage of OnValueChangeListener
binding.plusMinusNumView.valueChangeListener=object : PlusMinusNumView.OnValueChangeListener{
            override fun onValueChange(newVal: Float) {
                    Toast.makeText(applicationContext,"New Value is $newVal",Toast.LENGTH_SHORT).show()
            }
        }
//get current value
        val currentValue=binding.plusMinusNumView.currentValue
```