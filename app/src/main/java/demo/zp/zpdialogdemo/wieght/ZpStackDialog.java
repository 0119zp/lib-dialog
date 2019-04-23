package demo.zp.zpdialogdemo.wieght;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.bean.ZpStackDialogBean;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class ZpStackDialog extends Dialog {

    // dialog 类型
    private static final int DIALOG_TYPE_1 = 1;
    private static final int DIALOG_TYPE_2 = 2;
    private static final int DIALOG_TYPE_3 = 3;
    private static final int DIALOG_TYPE_4 = 4;
    // dialog 背景
    private static int mTheme = R.style.ZpDialog;

    private LinearLayout commonLayout;
    private Context mContext;
    private LayoutInflater inflater;
    private ZpStackDialogBean stackDialogBean;
    private boolean isClick = false;            // 是否点击 X 关闭
    private boolean isSkip = false;             // 是否点击跳转

    public ZpStackDialog(Context context) {
        super(context, mTheme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog);

        commonLayout = (LinearLayout) findViewById(R.id.llayout_common);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public void dismiss() {
        super.dismiss();

        Log.e("zpan", "===id === " + stackDialogBean.getId());
        Log.e("zpan", "===isClick === " + isClick);
        Log.e("zpan", "===isSkip === " + isSkip);

        if (!isClick && !isSkip){
            if (onClickDismissListner != null){
                onClickDismissListner.dismissDialog(stackDialogBean);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 弹窗栈
     * @param list
     */
    public void setDialogList(ArrayList<ZpStackDialogBean> list){
        this.isClick = false;
        for (int i = 0 ; i < list.size() ; i++){
            boolean isShowed = list.get(i).isShow();
            stackDialogBean = list.get(i);
            if (isShowed){
                if (i < list.size() - 1){
                    continue;
                }else {
                    dismiss();
                }
            }else {
                setDialogType(stackDialogBean);
                break;
            }
        }
    }

    /**
     * 单个弹窗
     * @param stackDialogBean
     */
    public void setDialogType(ZpStackDialogBean stackDialogBean){
        this.stackDialogBean = stackDialogBean;
        switch (stackDialogBean.getId()){
            case DIALOG_TYPE_1:
                setDialog1(stackDialogBean);
                break;
            case DIALOG_TYPE_2:
                setDialog2(stackDialogBean);
                break;
            case DIALOG_TYPE_3:
                setDialog3(stackDialogBean);
                break;
            case DIALOG_TYPE_4:
                setDialog4(stackDialogBean);
                break;
        }
    }

    // 弹框类型 - 1
    private void setDialog1(ZpStackDialogBean stackDialogBean) {
        commonLayout.removeAllViews();
        View v1 = inflater.inflate(R.layout.dialog_1, null);
        ImageView img = (ImageView) v1.findViewById(R.id.iv_dialog_img);
        clickDeletBtn(img, stackDialogBean);

        commonLayout.addView(v1);
    }

    // 弹框类型 - 2
    private void setDialog2(ZpStackDialogBean stackDialogBean) {
        commonLayout.removeAllViews();
        View v2 = inflater.inflate(R.layout.dialog_2, null);
        ImageView img = (ImageView) v2.findViewById(R.id.iv_dialog_img);
        clickDeletBtn(img, stackDialogBean);
        TextView skip = (TextView) v2.findViewById(R.id.tv_dialog_skip);
        clickSkipBtn(skip, stackDialogBean);

        commonLayout.addView(v2);
    }

    // 弹框类型 - 3
    private void setDialog3(ZpStackDialogBean stackDialogBean) {
        commonLayout.removeAllViews();
        View v3 = inflater.inflate(R.layout.dialog_3, null);
        ImageView img = (ImageView) v3.findViewById(R.id.iv_dialog_img);
        clickDeletBtn(img, stackDialogBean);

        commonLayout.addView(v3);
    }

    // 弹框类型 - 4
    private void setDialog4(ZpStackDialogBean stackDialogBean) {
        commonLayout.removeAllViews();
        View v4 = inflater.inflate(R.layout.dialog_4, null);
        ImageView img = (ImageView) v4.findViewById(R.id.iv_dialog_img);
        clickDeletBtn(img, stackDialogBean);

        commonLayout.addView(v4);
    }


    /**
     * 点击删除按钮
     * @param deletBtn
     * @param stackDialogBean
     */
    private void clickDeletBtn(ImageView deletBtn, final ZpStackDialogBean stackDialogBean){
        deletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = true;
                if (onClickDismissListner != null){
                    onClickDismissListner.dismissDialog(stackDialogBean);
                }
                dismiss();
            }
        });
    }

    // 点击关闭弹窗监听
    private ZpStackDialog.OnClickDismissListner onClickDismissListner;

    public void setOnClickDismissListner(ZpStackDialog.OnClickDismissListner listner){
        this.onClickDismissListner = listner;
    }

    public interface OnClickDismissListner{
        /**
         * 隐藏 dialog
         * @param stackDialogBean dialog 数据
         */
        void dismissDialog(ZpStackDialogBean stackDialogBean);
    }

    /**
     * 点击跳转
     * @param skipBtn
     * @param stackDialogBean
     */
    private void clickSkipBtn(TextView skipBtn, final ZpStackDialogBean stackDialogBean){
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSkip = true;
                dismiss();
                stackDialogBean.setShow(true);
                onClickSkipListner.clickSkip(stackDialogBean);
            }
        });
    }

    // 点击跳转按钮的监听
    private ZpStackDialog.OnClickSkipListner onClickSkipListner;

    public void setOnClickSkipListner(ZpStackDialog.OnClickSkipListner listner){
        this.onClickSkipListner = listner;
    }

    public interface OnClickSkipListner{
        /**
         * 点击跳转
         * @param stackDialogBean dialog 数据
         */
        void clickSkip(ZpStackDialogBean stackDialogBean);
    }

}
