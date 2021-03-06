package com.jiangkang.ktools

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.transition.Fade
import android.view.Window
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import com.jiangkang.ktools.widget.*
import com.jiangkang.tools.extend.launch
import com.jiangkang.tools.utils.ToastUtils
import com.jiangkang.tools.widget.FloatingWindow
import com.jiangkang.tools.widget.KNotification

class WidgetActivity : AppCompatActivity() {

    private val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //转场动画
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Fade()
        window.exitTransition = Fade()
        setContentView(R.layout.activity_widget)
        handleOnClick()
    }

    private fun handleOnClick() {
        findViewById<Button>(R.id.btn_coordinator_layout).setOnClickListener {
            CoordinatorActivity.launch(mContext, null)
        }
        findViewById<Button>(R.id.btn_show_floating_window).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this@WidgetActivity)) {
                    startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                } else {
                    FloatingWindow.show(mContext, "来来来，看这里\n这是一个悬浮框")
                }
            }
        }

        findViewById<Button>(R.id.btn_dismiss_floating_window).setOnClickListener {
            FloatingWindow.dismiss()
        }

        findViewById<Button>(R.id.btn_set_toast_show_time).setOnClickListener {
            ToastUtils.showToast("测试自定义显示时间Toast:20s", 1000 * 20)
        }

        findViewById<Button>(R.id.btn_create_simple_notification).setOnClickListener {
            KNotification.createNotification(mContext, R.mipmap.ic_launcher, "测试标题", "测试内容", Intent(mContext, MainActivity::class.java))
        }


        findViewById<Button>(R.id.btn_show_custom_notification).setOnClickListener {
            val views = RemoteViews(mContext.packageName, R.layout.layout_big_notification)
            views.setImageViewResource(R.id.iv_notification_img, R.drawable.demo)
            KNotification.createNotification(mContext, R.mipmap.ic_launcher, views,
                    Intent(mContext, MainActivity::class.java))
        }


        findViewById<Button>(R.id.btn_widget_dialog).setOnClickListener {
            launch(KDialogActivity::class.java, null)
        }


        findViewById<Button>(R.id.btn_floating_action_button).setOnClickListener {
            FabActivity.launch(mContext, null)
        }

        findViewById<Button>(R.id.btn_scroll).setOnClickListener {
            ScrollingActivity.launch(mContext, null)
        }

        findViewById<Button>(R.id.btn_bottom_nav).setOnClickListener {
            BottomNavigationActivity.launch(mContext, null)
        }

        findViewById<Button>(R.id.btn_constraint_layout).setOnClickListener {
            ConstraintLayoutActivity.launch(mContext, null)
        }


    }


    override fun onBackPressed() {
        super.onBackPressed()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        }
    }


    companion object {

        fun launch(context: Context, bundle: Bundle?) {
            val intent = Intent(context, WidgetActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            context.startActivity(intent)
        }
    }

}
