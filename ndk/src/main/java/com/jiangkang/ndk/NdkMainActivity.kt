package com.jiangkang.ndk

import android.app.Activity
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiangkang.tools.utils.ToastUtils
import com.jiangkang.tools.widget.KDialog
import kotlinx.android.synthetic.main.activity_ndk_main.*

class NdkMainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ndk_main)
        System.loadLibrary("ktools")
        handleLogic()
    }

    private fun handleLogic() {
        btn_hello_world.setOnClickListener {
            ToastUtils.showShortToast(sayHello())
        }

        
        btn_draw_shape.setOnClickListener {
            val file = createTempFile("shape_",".png")
            drawShapeTest(file.absolutePath)
            KDialog.showImgInDialog(this@NdkMainActivity,BitmapFactory.decodeFile(file.absolutePath))
        }

        btn_draw_text.setOnClickListener {
            val file = createTempFile("text_",".png")
            drawText(file.absolutePath)
            KDialog.showImgInDialog(this@NdkMainActivity,BitmapFactory.decodeFile(file.absolutePath))
        }
    }

    private external fun drawText(filename:String)

    private external fun drawShapeTest(filename:String)

    private external fun sayHello():String
}