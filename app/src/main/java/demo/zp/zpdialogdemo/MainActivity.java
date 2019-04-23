package demo.zp.zpdialogdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.zp.zpdialogdemo.zpPop.ZpCommonPopWindow;
import demo.zp.zpdialogdemo.zpToast.ZpCustomToast;
import demo.zp.zpdialogdemo.zpdialog.ZpCustemActivity;
import demo.zp.zpdialogdemo.zpdialog.ZpDialogActivity;
import demo.zp.zpdialogdemo.zpdialog.ZpMaterialDialogActivity;
import demo.zp.zpdialogdemo.zpdialog.ZpStackDialogActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button01(View view) {
        Intent intent = new Intent(MainActivity.this, ZpDialogActivity.class);
        startActivity(intent);
    }

    public void button02(View view) {
        Intent intent = new Intent(MainActivity.this, ZpMaterialDialogActivity.class);
        startActivity(intent);
    }

    public void button03(View view) {
        Intent intent = new Intent(MainActivity.this, ZpCustemActivity.class);
        startActivity(intent);
    }

    public void button04(View view){
        Intent intent = new Intent(MainActivity.this, ZpStackDialogActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义 Toast
     */
    public void button05(View view){
        setToset(this, "我是自定义Toast", 2000);
    }

    private void setToset(Context context, String toastMsg, int duration, String... icon){
        ZpCustomToast.getInstance(context).setIcon(icon).setText(toastMsg).setduration(duration).show();
    }

    /**
     * popwindow
     */
    public void button06(View view){
        ZpCommonPopWindow popWindow = new ZpCommonPopWindow(this);
        popWindow.show();
    }

}
