package demo.zp.zpdialogdemo.custem;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.utils.BitmapAndDrawable;

/**
 * Created by zpan on 2017/7/14 0014.
 */

public class ZpCustemDialog extends Dialog implements android.view.View.OnClickListener {

    private Context context;
    private TextView set_dialog_cancle, set_dialog_confirm; // 取消， 确认
    private WheelView set_dialog_start, set_dialog_end; // 开始时间， 结束时间
    private TextView txtStart, txtEnd; // 每日起始时间， 次日结束时间 // 现用于保存时间
    private LinearLayout llBg;

    private CalendarTextAdapter startAdapter;
    private CalendarTextAdapter endAdapter;

    private String[] time; // 时间数组
    private int begainIndex, endIndex; // 默认开始时间22点整，结束时间次日8点整

    private int maxTextSize = 18; // 最大字体
    private int minTextSize = 12; // 最小字体

    public ZpCustemDialog(Context context, int start, int end) {
        super(context, R.style.Dialog);
        this.context = context;
        this.begainIndex = start;
        this.endIndex = end;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_dialog_item);

        llBg = (LinearLayout) findViewById(R.id.llBg);
        Bitmap bitmap = BitmapAndDrawable.createBitmap(context, R.drawable.yuanjiao_bg, 1);
        llBg.setBackgroundDrawable(new BitmapDrawable(bitmap));

        set_dialog_cancle = (TextView) findViewById(R.id.set_dialog_cancle);
        set_dialog_cancle.setOnClickListener(this);
        set_dialog_confirm = (TextView) findViewById(R.id.set_dialog_confirm);
        set_dialog_confirm.setOnClickListener(this);
        set_dialog_start = (WheelView) findViewById(R.id.set_dialog_start);
        set_dialog_end = (WheelView) findViewById(R.id.set_dialog_end);

        txtStart = (TextView) findViewById(R.id.set_dialog_item_txt_start);
        txtEnd = (TextView) findViewById(R.id.set_dialog_item_txt_end);

        txtStart.setText(String.valueOf(begainIndex) + ":00");
        txtEnd.setText(String.valueOf(endIndex) + ":00");

        time = context.getResources().getStringArray(R.array.time);

        startAdapter = new CalendarTextAdapter(context, time, begainIndex, maxTextSize, minTextSize);
        set_dialog_start.setVisibleItems(3);
        set_dialog_start.setViewAdapter(startAdapter);
        set_dialog_start.setCurrentItem(begainIndex);

        set_dialog_start.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) startAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, startAdapter);
                txtStart.setText(time[newValue]);
            }
        });

        set_dialog_start.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) startAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, startAdapter);
            }
        });

        endAdapter = new CalendarTextAdapter(context, time, endIndex, maxTextSize, minTextSize);
        set_dialog_end.setVisibleItems(3);
        set_dialog_end.setViewAdapter(endAdapter);
        set_dialog_end.setCurrentItem(endIndex);

        set_dialog_end.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) endAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, endAdapter);
                txtEnd.setText(time[newValue]);
            }
        });

        set_dialog_end.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) endAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, endAdapter);
            }
        });
    }

    /**
     * 时间滚筒
     */
    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        String[] list;

        protected CalendarTextAdapter(Context context, String[] list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_start, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.length;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list[index];
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
                textvew.setTextColor(context.getResources().getColor(R.color.pickerview_topbar_title));
            } else {
                textvew.setTextSize(minTextSize);
                textvew.setTextColor(context.getResources().getColor(R.color.version2_0_common_defaultpay_notinternet_text_color));
            }
        }
    }

    /**
     * 时间选择监听
     *
     * @author flueky zkf@yitong.com.cn
     * @date 2016年2月25日 下午12:37:56
     */
    public interface OnTimeSelecterListener {
        public void timeSelecter(String start, String end);
    }

    private OnTimeSelecterListener listener;

    public void setListener(OnTimeSelecterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_dialog_cancle: // 取消按钮
                dismiss();
                cancel();
                break;

            case R.id.set_dialog_confirm: // 确认按钮
                dismiss();
                cancel();
                // 点击确定按钮，回调时间
                if (listener != null) {
                    listener.timeSelecter(txtStart.getText().toString(), txtEnd.getText().toString());
                }
                break;
        }
    }

    @Override
    public void dismiss() {
        BitmapAndDrawable.recycleViewBg(llBg);
        super.dismiss();
    }

}
