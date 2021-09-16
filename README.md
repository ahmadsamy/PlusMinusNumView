# PlusMinusNumView

![Preview](https://i.ibb.co/vdW6LLT/Video-Editor-20210916-015933-2.gif)
*Note: the widget inherits its style from your app theme

*Simple widget that has a number with plus button to increase its value 
and a minus button to decrease it

*Xml styleable options:
- maxVal(float): maximum value
- minVal(float): minimum value
- step(float): step value
- title(string): title of widget
- valueTextAppearance(reference): text appearance of value TextView
- titleTextAppearance(reference): text appearance of Title TextView


#XML layout usage

```XML
<ahmad.egypt.plusminusnumview.PlusMinusNumView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:valueTextAppearance="@style/TextAppearance.AppCompat.Large"
    app:titleTextAppearance="@style/TextAppearance.AppCompat.Large"
    app:maxVal="6"
    app:minVal="1"
    app:step="1"
    android:id="@+id/plus_minus_num_view"
    app:title="Distance"
    android:paddingVertical="16dp"
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