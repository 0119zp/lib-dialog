package demo.zp.zpdialogdemo.wieght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.utils.MyAnimationDrawable;

public class WaitDialog extends Dialog {
	private Context ctx;
	private int type = 2;//显示的类型1：黑色底，白色曲线，others：透明底，蓝色曲线

	public WaitDialog(Context context, int theme) {
		super(context,theme);
		this.ctx =context;
		initGUI();
	}

	public WaitDialog(Context context, int theme, int type){
		super(context,theme);
		this.ctx =context;
		this.type = type;
		initGUI();
	}

	private ImageView load_iv;
	private RelativeLayout wait_rl;

	private void initGUI() {
		LayoutInflater inflater = LayoutInflater.from(ctx);
		View view = inflater.inflate(R.layout.dialog_wait, null);
		load_iv = (ImageView) view.findViewById(R.id.load_iv);
		wait_rl = (RelativeLayout)view.findViewById(R.id.wait_rl);
		if(type != 1){
			wait_rl.setBackgroundResource(R.drawable.background_wait_dialog);//设置黑色背景
			wait_rl.setAlpha(0.75f);//透明度0.75
		}
		setContentView(view);
	}

	@Override
	public void show() {
        if(ctx instanceof Activity){
            Activity act = (Activity)ctx;
            if(null!=act&&act.isFinishing()){
                return;
            }
        }
		MyAnimationDrawable.animateRawManuallyFromXML(type == 1?R.drawable.anim:R.drawable.white_anim, load_iv,
				new Runnable() {

					@Override
					public void run() {
						// TODO onStart
						Log.e("TAG","等待层开始");
					}
				}, new Runnable() {

					@Override
					public void run() {
						// TODO onComplete
						Log.e("TAG","等待层结束");
					}
				});
		super.show();
	}

    @Override
    public void dismiss() {
		MyAnimationDrawable.stop();
        if(ctx instanceof Activity){
            Activity act = (Activity)ctx;
            if(null!=act&&act.isFinishing()){
                return;
            }
        }
        super.dismiss();
    }
}
