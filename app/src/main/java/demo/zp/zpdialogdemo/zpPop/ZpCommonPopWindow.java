package demo.zp.zpdialogdemo.zpPop;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import demo.zp.zpdialogdemo.R;

/**
 * Created by Administrator on 2018/1/14 0014.
 */

public class ZpCommonPopWindow {

    private Context mContext;
    private LayoutInflater inflater;
    private PopupWindow popupWindow;
    private LinearLayout popItem;
    private RelativeLayout rootItem;
    private TextView popCancel;
    private float touchY;

    public ZpCommonPopWindow(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    private void initView(){
        View rootView = inflater.inflate(R.layout.popwindow_common_zp, null);
        popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        rootItem = (RelativeLayout) rootView.findViewById(R.id.rl_pop_root_view);
        popItem = (LinearLayout) rootView.findViewById(R.id.ll_pop_view);
        popCancel = (TextView) rootView.findViewById(R.id.tv_pop_cancel);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rootItem.setLayoutParams(lp);

        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });

        rootItem.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (touchY < popItem.getTop()) {
                            dismissPopupWindow();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void show() {
        initView();
        rootItem.setBackgroundColor(Color.parseColor("#000000"));
        rootItem.getBackground().mutate().setAlpha(150);
        popupWindow.showAtLocation(popCancel, Gravity.BOTTOM, 0, 0);
    }

    private void dismissPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
        }
    }

}
