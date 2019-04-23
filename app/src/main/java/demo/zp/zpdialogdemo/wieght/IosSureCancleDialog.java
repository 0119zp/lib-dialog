package demo.zp.zpdialogdemo.wieght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.zp.zpdialogdemo.R;

/**
 * 仿照ios确定取消提示框
 */

public class IosSureCancleDialog extends Dialog {

    private Context mcontext;
    private String title, scontent, left_string, right_string, center_string;
    private View.OnClickListener left_onclick, right_onclick, center_onclcik;

    private LinearLayout linearLayout_top;//最顶层linearlayout为了自适应大段落字体高度，可调用自适应方法设置模式
    private LinearLayout linearLayout_two;//第二层
    private TextView content;//内容
    private boolean multimode = false;//是否多行模式

    //2.0版本通用样式
    private int mode;//显示模式 1(3单按钮)：无标题，内容居中，左右2个按钮样式。0(2单按钮)：有标题，内容居中，左右2个按钮样式。

    /*无标题，内容居中，左右2个按钮样式*/
    public IosSureCancleDialog(Context context, String content, String left, View.OnClickListener left_onclick
            , String right, View.OnClickListener right_onclick, int mode) {
        super(context, R.style.okcancledialog);
        this.mcontext = context;
        this.scontent = content;
        this.left_string = left;
        this.right_string = right;
        this.left_onclick = left_onclick;
        this.right_onclick = right_onclick;
        this.mode = mode;
    }

    /*无标题，内容居中，单个按钮样式*/
    public IosSureCancleDialog(Context context, String content, String center_string, View.OnClickListener center_onclick
            , int mode) {
        super(context, R.style.okcancledialog);
        this.mcontext = context;
        this.scontent = content;
        this.center_string = center_string;
        this.center_onclcik = center_onclick;
        this.mode = mode;
    }

    /*有标题，内容居中，左右2个按钮样式*/
    public IosSureCancleDialog(Context context, String title, String content, String left, View.OnClickListener left_onclick
            , String right, View.OnClickListener right_onclick, int mode) {
        super(context, R.style.okcancledialog);
        this.mcontext = context;
        this.scontent = content;
        this.title = title;
        this.left_string = left;
        this.right_string = right;
        this.left_onclick = left_onclick;
        this.right_onclick = right_onclick;
        this.mode = mode;
    }


    /*有标题，内容居中，只有一个按钮样式*/
    public IosSureCancleDialog(Context context, String title, String content, String center_string, View.OnClickListener center_onclick
            , int mode) {
        super(context, R.style.okcancledialog);
        this.mcontext = context;
        this.scontent = content;
        this.title = title;
        this.center_string = center_string;
        this.center_onclcik = center_onclick;
        this.mode = mode;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mode == 1) {
            setContentView(initStyleTwo(mcontext));
        } else if (mode == 2) {
            setContentView(initStyleThree(mcontext));
        } else if (mode == 3) {
            setContentView(initStyleFour(mcontext));
        } else {
            setContentView(initStyleOne(mcontext));
        }
    }


    /**
     * 初始化第一种样式(有标题，有内容，左右2个按钮)
     *
     * @return
     */
    private View initStyleOne(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_iostitleokcancle, null);
        TextView titleTv = (TextView) view.findViewById(R.id.dialog_iosokcancle_titile);
        linearLayout_top = (LinearLayout) view.findViewById(R.id.dialog_iostitleokcanccle_top_lin);
        linearLayout_two = (LinearLayout) view.findViewById(R.id.dialog_iostitleokcancle_two_lin);
        titleTv.setText(title);//设置标题
        content = (TextView) view.findViewById(R.id.dialog_iosokcancle_content_neirong);
        content.setText(scontent);//设置内容
        Button leftBt = (Button) view.findViewById(R.id.dialog_iosokcancle_left);//初始化左下弱按钮
        leftBt.setText(left_string);//设置左下按钮字体内容
        leftBt.setOnClickListener(left_onclick);//设置左下按钮点击事件
        Button rightBt = (Button) view.findViewById(R.id.dialog_iosokcancle_right);//初始化右下弱按钮
        rightBt.setText(right_string);//设置右下按钮字体内容
        rightBt.setOnClickListener(right_onclick);//设置左下按钮点击事件
        setMultiLineMode(multimode);
        return view;
    }

    /**
     * 初始化第二种样式(无标题，有内容，左右2个按钮)
     *
     * @return
     */
    private View initStyleTwo(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_iosokcancle, null);
        TextView contentTv = (TextView) view.findViewById(R.id.dialog_iosokcancle_content_neirong);
        contentTv.setText(scontent);//设置内容
        Button leftBt = (Button) view.findViewById(R.id.dialog_iosokcancle_left);//初始化左下弱按钮
        leftBt.setText(left_string);//设置左下按钮字体内容
        leftBt.setOnClickListener(left_onclick);//设置左下按钮点击事件
        Button rightBt = (Button) view.findViewById(R.id.dialog_iosokcancle_right);//初始化右下弱按钮
        rightBt.setText(right_string);//设置右下按钮字体内容
        rightBt.setOnClickListener(right_onclick);//设置左下按钮点击事件
        return view;
    }

    /**
     * 初始化第三种样式(有标题，有内容，只有1个按钮)
     *
     * @return
     */
    private View initStyleThree(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_iostitleok_more, null);
        TextView titleTv = (TextView) view.findViewById(R.id.dialog_iosokcancle_titile);
        titleTv.setText(title);//设置标题
        TextView contentTv = (TextView) view.findViewById(R.id.dialog_iosokcancle_content_neirong);
        contentTv.setText(scontent);//设置内容
        Button centerBt = (Button) view.findViewById(R.id.dialog_iosokcenter);//初始化底部单个按钮
        centerBt.setText(center_string);//设置底部单个按钮字体内容
        centerBt.setOnClickListener(center_onclcik);//设置底部单个按钮点击事件
        return view;
    }

    /**
     * 初始化第四种样式(无标题，有内容，只有1个按钮)
     *
     * @return
     */
    private View initStyleFour(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_iosok, null);
        TextView contentTv = (TextView) view.findViewById(R.id.dialog_iosokcancle_content_neirong);
        contentTv.setText(scontent);//设置内容
        Button centerBt = (Button) view.findViewById(R.id.dialog_iosokcenter);//初始化底部单个按钮
        centerBt.setText(center_string);//设置底部单个按钮字体内容
        centerBt.setOnClickListener(center_onclcik);//设置底部单个按钮点击事件
        return view;
    }

    @Override
    public void show() {
        if (mcontext instanceof Activity) {//如果传入的是Activity，此页面销毁了不展示。
            if (((Activity) mcontext).isFinishing()) {
                return;
            }
        }
        if (this.isShowing()) {
            return;
        } else {
            super.show();
            Window w = this.getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//后置背景变暗
            w.setWindowAnimations(R.style.dialogWindowAnim);
            WindowManager.LayoutParams wl = w.getAttributes();
            wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            wl.dimAmount = 0.7f;
            //wl.alpha=0.5f;透明度阿尔法值
            w.setAttributes(wl);
//        this.onWindowAttributesChanged(wl);
            this.setCanceledOnTouchOutside(false);
            //添加进入动画
//            ScaleAnimation animation_suofang =new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
//                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            animation_suofang.setDuration(100);                     //执行时间
//            animation_suofang.setRepeatMode(Animation.REVERSE);     //重复 缩小和放大效果
//            this.startAnimation(animation_suofang);
        }
    }

    public void setCanCle(boolean iscancle) {
        this.setCanceledOnTouchOutside(iscancle);
        this.setCancelable(iscancle);
    }

    public void setMultiMode(boolean mode) {
        this.multimode = mode;
    }

    /**
     * 两个按钮，有标题mode：0情况下多行适应
     */
    private void setMultiLineMode(boolean mode) {
        if (mode && this.mode == 0) {
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) linearLayout_top.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            linearLayout_top.setLayoutParams(params);
            ViewGroup.LayoutParams two_params = (ViewGroup.LayoutParams) linearLayout_two.getLayoutParams();
            two_params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            linearLayout_two.setLayoutParams(two_params);
            ViewGroup.LayoutParams tv_params = (ViewGroup.LayoutParams) content.getLayoutParams();
            tv_params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            content.setLayoutParams(tv_params);
        }
    }
}