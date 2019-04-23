package demo.zp.zpdialogdemo.wieght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.utils.AndroidUtil;
import demo.zp.zpdialogdemo.utils.BitmapAndDrawable;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ZpCommonDialog extends Dialog {

    private Handler handler = new Handler();
    private Context ctx;
    private ImageView sign_image;
    private TextView sign_text;
    private LinearLayout successDialog;
    private int ImagePic;// 0 惊叹号 1√ 2×

    public interface PaySignDialogInterFace{
        public void paySignDialogDismiss();
    }

    public void setPaySignDialogInterFace(PaySignDialogInterFace paySignDialogInterFace) {
        this.paySignDialogInterFace = paySignDialogInterFace;
    }

    private PaySignDialogInterFace paySignDialogInterFace;//关闭提示框后操作接口

    public ZpCommonDialog(Context context,int ImagePic, int mode) {// 0 惊叹号 1√ 2×
        super(context, R.style.waitdialog);
        this.ctx = context;
        this.ImagePic=ImagePic;
        initGUI();

    }
    public ZpCommonDialog(Context context, int theme) {
        super(context, R.style.waitdialog);
        this.ctx = context;
        initGUI();

    }

    public void setImagePic(int imagePic) {
        this.imagePic = imagePic;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    private int imagePic = 0;//显示资源图片
    private String describ = "";//提示框文字

    public void setMode(int mode) {
        this.mode = mode;
    }

    private int mode = 1;//目前模式下，模式1为正常190dp宽度，2为210dp

    private void initGUI() {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view;
        if (ImagePic==3){
            view = inflater.inflate(R.layout.dialog_sign_send, null);
        }else {
            view = inflater.inflate(R.layout.dialog_sign_success, null);
        }
        setContentView(view);
        sign_image = (ImageView) findViewById(R.id.sign_image);
        sign_text = (TextView) findViewById(R.id.sign_text);
        successDialog = (LinearLayout) findViewById(R.id.successDialog);
        if (mode != 1) {
            RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(AndroidUtil.dip2px(ctx, 210.0f), LinearLayout.LayoutParams.WRAP_CONTENT);
            successDialog.setLayoutParams(llp);
        }
        if(ImagePic==1){
            sign_image.setBackgroundDrawable(new BitmapDrawable(BitmapAndDrawable.createBitmap(ctx, R.drawable.dialog_setpaw, 1)));
        }else if(ImagePic==2){
            sign_image.setBackgroundDrawable(new BitmapDrawable(BitmapAndDrawable.createBitmap(ctx, R.drawable.sign_faile, 1)));
        }else {
            sign_image.setBackgroundDrawable(new BitmapDrawable(BitmapAndDrawable.createBitmap(ctx, R.drawable.gesture_locked, 1)));
        }
        sign_text.setText(describ);
    }


    @Override
    public void show() {
        if(ctx instanceof Activity &&((Activity) ctx).isFinishing()){
            return;
        }
        initGUI();
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                if(null!=paySignDialogInterFace){
                    paySignDialogInterFace.paySignDialogDismiss();
                }
            }
        },1500);
        super.show();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void dismiss() {
        //回收资源图片
        if(ctx instanceof Activity&&((Activity) ctx).isFinishing()){
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                BitmapAndDrawable.recycleViewBg(sign_image);
            }
        });
        super.dismiss();
    }

}
