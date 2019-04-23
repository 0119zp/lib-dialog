package demo.zp.zpdialogdemo.zpToast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import demo.zp.zpdialogdemo.R;

/**
 * Created by Administrator on 2018/1/14 0014.
 */

public class ZpCustomToast extends Toast {

    private static ZpCustomToast mCustomToast;
    private static Context mContext;
    // 带有icon的toast上方的图标
    View mCustomToastView = null;
    TextView mToastIconView = null;
    TextView mToastTextView = null;


    private ZpCustomToast(Context context) {
        super(context);
        // 避免多次重复填充toast的布局
        if (mCustomToastView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            mCustomToastView = inflater.inflate(R.layout.widget_custom_toast, null);
            mToastIconView = (TextView) mCustomToastView.findViewById(R.id.icon);
            mToastTextView = (TextView) mCustomToastView.findViewById(R.id.content);

            setGravity(Gravity.CENTER, 0, 0);
            setDuration(Toast.LENGTH_SHORT);
            setView(mCustomToastView);
        }
    }

    public static ZpCustomToast getInstance(Context ctx) {
        mContext = ctx;
        if (mCustomToast == null) {
            mCustomToast = new ZpCustomToast(ctx);
        }
        return mCustomToast;
    }

    public ZpCustomToast setIcon(String... icon) {
        // 设置icon的图标
        if (icon == null || icon.length == 0) {
            // 不带icon的toast
            mToastIconView.setVisibility(View.GONE);
            mToastTextView.setMaxWidth(dip2px(mContext, 160));
        } else {// 带icon的toast
            mToastIconView.setVisibility(View.VISIBLE);
            mToastTextView.setMaxWidth(dip2px(mContext, 64));
        }
        return mCustomToast;
    }

    public ZpCustomToast setText(String str) {
        mToastTextView.setText(str);
        return mCustomToast;
    }

    public ZpCustomToast setduration(int duration) {
        setDuration(duration);
        return mCustomToast;
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }

}
