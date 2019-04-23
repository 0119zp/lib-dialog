package demo.zp.zpdialogdemo.zpdialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.custem.ZpCustemDialog;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class ZpCustemActivity extends Activity implements ZpCustemDialog.OnTimeSelecterListener {

    private ZpCustemDialog zpCustemDialog;
    private final String SLEEPFORMAT = "从%s至%s"; // 通配字段
    private TextView txtSleep;
    private int start = 22, end = 8; // 默认开始时间22点整，结束时间次日8点整

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custem);

        txtSleep = (TextView) findViewById(R.id.tv_time);
    }

    public void start(View view){
        zpCustemDialog = new ZpCustemDialog(ZpCustemActivity.this, 1, 2);
        zpCustemDialog.setListener(this);
        zpCustemDialog.show();
    }

    public void end(View view){
        zpCustemDialog.dismiss();
    }


    @Override
    public void timeSelecter(String start, String end) {
        // 选中的时间
        txtSleep.setText(String.format(SLEEPFORMAT, start, end));
        // 保证按返回键发接口时的时间入参为最新
        if (start.length() == 4) {
            String s = start.substring(0, 1);
            this.start = Integer.parseInt(s);
        } else {
            String s = start.substring(0, 2);
            this.start = Integer.parseInt(s);
        }
        if (end.length() == 4) {
            String e = end.substring(0, 1);
            this.end = Integer.parseInt(e);
        } else {
            String e = end.substring(0, 2);
            this.end = Integer.parseInt(e);
        }
    }
}
