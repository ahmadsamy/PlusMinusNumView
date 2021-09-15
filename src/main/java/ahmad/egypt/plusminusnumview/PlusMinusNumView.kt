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

        private var maxNum=6f
        private var minNum=1f
        private var step=1f
        private var title:String?=null

        private var plusButton:AppCompatImageView
        private var minusButton:AppCompatImageView
        private var numTV:TextView
        private var titleTV:TextView


        var currentNum:Float = maxNum
        get() {
            return numTV.text.toString().toFloat()
        }

        var numChangeListener:OnNumChangeListener?=null

        init {
        inflate(context, R.layout.plus_minus_num_layout,this)
        plusButton=findViewById(R.id.plus)
        minusButton=findViewById(R.id.minus)
        numTV=findViewById(R.id.numView)
        numTV.text=getFormattedNum(maxNum)
        titleTV=findViewById(R.id.title)

        context.theme.obtainStyledAttributes(
            attrSet,
            R.styleable.PlusMinusNumView,
            0, 0).apply {
            try {
                maxNum = getFloat(R.styleable.PlusMinusNumView_maxNum, 6f)
                minNum = getFloat(R.styleable.PlusMinusNumView_minNum, 1f)
                step = getFloat(R.styleable.PlusMinusNumView_step,1f)
                val textAppearance = getResourceId(R.styleable.PlusMinusNumView_numTextAppearance,
                    android.R.attr.textAppearanceLarge)
                numTV.setTextAppearance(context,textAppearance)
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
            setNum(currentNum+step)
        }

        minusButton.setOnClickListener {
            setNum(currentNum-step)
        }


    }

    private fun setNum(newNum:Float){
        if (newNum>maxNum||newNum<minNum)return
        numTV.text=getFormattedNum(newNum)
        numChangeListener?.onNumChange(newNum)
    }

    private fun getFormattedNum(num:Float):String{
        return if (isInteger(num)) "${num.toInt()}" else "%.2f".format(num)
    }

    private fun isInteger(num:Float):Boolean{
        return ((num.toInt()*10)-(num*10).toInt()==0)
    }

    interface OnNumChangeListener{
        fun onNumChange(newNum:Float)
    }
}