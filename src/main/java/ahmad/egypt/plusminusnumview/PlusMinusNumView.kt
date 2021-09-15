package ahmad.egypt.plusminusnumview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView


class PlusMinusNumView(context: Context, attrSet: AttributeSet?, defStyleAttr:Int)
    :LinearLayout(context,attrSet,defStyleAttr) {
        constructor(context: Context,attrSet: AttributeSet) : this(context,attrSet,0)
        constructor(context: Context): this(context,null,0)

        private var maxVal=6f
        private var minVal=1f
        private var step=1f
        private var title:String?=null

        private var plusButton:AppCompatImageView
        private var minusButton:AppCompatImageView
        private var valueTV:TextView
        private var titleTV:TextView


        var currentValue:Float = maxVal
        get() {
            return valueTV.text.toString().toFloat()
        }

        var valueChangeListener:OnValueChangeListener?=null

        init {
        inflate(context, R.layout.plus_minus_num_layout,this)
        plusButton=findViewById(R.id.plus)
        minusButton=findViewById(R.id.minus)
        valueTV=findViewById(R.id.valueView)
        valueTV.text=formatNumber(maxVal)
        titleTV=findViewById(R.id.title)

        context.theme.obtainStyledAttributes(
            attrSet,
            R.styleable.PlusMinusNumView,
            0, 0).apply {
            try {
                maxVal = getFloat(R.styleable.PlusMinusNumView_maxVal, 6f)
                minVal = getFloat(R.styleable.PlusMinusNumView_minVal, 1f)
                step = getFloat(R.styleable.PlusMinusNumView_step,1f)
                val textAppearance = getResourceId(R.styleable.PlusMinusNumView_valueTextAppearance,
                    android.R.attr.textAppearanceLarge)
                valueTV.setTextAppearance(context,textAppearance)
                title=getString(R.styleable.PlusMinusNumView_title)
                if (title!=null){
                    titleTV.text=title
                    titleTV.visibility= VISIBLE
                }
            } finally {
                recycle()
            }
        }

        plusButton.setOnClickListener {
            setVal(currentValue+step)
        }

        minusButton.setOnClickListener {
            setVal(currentValue-step)
        }


    }

    private fun setVal(newVal:Float){
        if (newVal>maxVal||newVal<minVal)return
        valueTV.text=formatNumber(newVal)
        valueChangeListener?.onValueChange(newVal)
    }

    private fun formatNumber(num:Float):String{
        return if (isInteger(num)) "${num.toInt()}" else "%.2f".format(num)
    }

    private fun isInteger(num:Float):Boolean{
        return ((num.toInt()*10)-(num*10).toInt()==0)
    }

    interface OnValueChangeListener{
        fun onValueChange(newVal:Float)
    }
}