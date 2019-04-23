package demo.zp.zpdialogdemo.wieght;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.zp.zpdialogdemo.R;

/**
 *
 */

public class Animationdialog extends Dialog implements android.view.View.OnClickListener {


    private Context context;
    private String describution;//文字描述
    private String TV_des;//按钮描述

    public Animationdialog(Context context, int theme, String titleDes, String ButDes) {
        super(context, theme);
        this.context = context;
        this.describution = titleDes;
        this.TV_des = ButDes;
        createDialog();
    }

    protected Animationdialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        createDialog();
    }

    public Animationdialog(Context context) {
        super(context);
        this.context = context;
        createDialog();
    }

    private void createDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.animation_managercard_dialog, null);
        setContentView(view);
        RelativeLayout animation_mancard_ll = (RelativeLayout) findViewById(R.id.animation_mancard_ll);//外部空白布局
        TextView animation_mancard = (TextView) findViewById(R.id.animation_mancard);//文字描述
        animation_mancard.setText(describution);
        TextView animation_mancard_del_bt = (TextView) findViewById(R.id.animation_mancard_del_bt);//删除按钮
        animation_mancard_del_bt.setText(TV_des);
        TextView animation_mancard_cancel_bt = (TextView) findViewById(R.id.animation_mancard_cancel_bt); // 取消按钮
        animation_mancard_del_bt.setOnClickListener(this);
        animation_mancard_cancel_bt.setOnClickListener(this);
        animation_mancard_ll.setOnClickListener(this);
    }

    public interface AnimationCustomDialogInterface {
        public void DeleteCard();

        public void CancelCard();
    }

    private AnimationCustomDialogInterface animationCustomDialogInterface;


    public void setAnimationCustomDialogInterface(
            AnimationCustomDialogInterface animationCustomDialogInterface) {
        this.animationCustomDialogInterface = animationCustomDialogInterface;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.animation_mancard_ll://点击外部隐藏alertdialog
                dismiss();
                cancel();
                break;
            case R.id.animation_mancard_del_bt://调用删除卡接口
                dismiss();
                cancel();
                if (null != animationCustomDialogInterface) {
                    animationCustomDialogInterface.DeleteCard();
                }
                break;
            case R.id.animation_mancard_cancel_bt: // 点击取消
                dismiss();
                cancel();
                if (null != animationCustomDialogInterface) {
                    animationCustomDialogInterface.CancelCard();
                }
                break;
        }

    }

}
