package demo.zp.zpdialogdemo.zpdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.materialdialogs.GravityEnum;
import com.materialdialogs.MaterialDialog;

import demo.zp.zpdialogdemo.R;

/**
 * Created by 通用dialog
 */

public class ZpMaterialDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_dialog);
    }

    public void button01(View view){
        new MaterialDialog.Builder(ZpMaterialDialogActivity.this)
                .title("提示")
                .iconRes(R.mipmap.ic_launcher)
                .content("测试一下")
                .positiveText("确定")
                .positiveColor(Color.BLUE)
                .negativeText("取消")
                .negativeColor(Color.GREEN)
                .autoDismiss(true)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        Toast.makeText(ZpMaterialDialogActivity.this, "确定按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        Toast.makeText(ZpMaterialDialogActivity.this, "取消按钮", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAny(MaterialDialog dialog) {
                        super.onAny(dialog);
                        Toast.makeText(ZpMaterialDialogActivity.this, "onAny", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        super.onNeutral(dialog);
                        Toast.makeText(ZpMaterialDialogActivity.this, "onNeutral", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    /**
     * 单选列表
     */
    private int selectedIndex = 0;
    private String[] types = {"葵花宝典","独孤九剑","七星剑","降龙十八掌"};

    public void button02(View view){
        new MaterialDialog.Builder(ZpMaterialDialogActivity.this)
                .title("选择武功秘籍")
                .titleColor(Color.parseColor("#aaff0000"))
                .items(types)
                .itemsCallbackSingleChoice(selectedIndex, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        selectedIndex = which;
                        Toast.makeText(ZpMaterialDialogActivity.this, "你选的武功是 "+ types[selectedIndex], Toast.LENGTH_SHORT).show();
                        return true;
                    }
                })
                .titleGravity(GravityEnum.CENTER)
                .positiveText("确定")
                .show();
    }

    /**
     * 自定义
     */
    public void button03(View view){
        new MaterialDialog.Builder(ZpMaterialDialogActivity.this)
                .customView(R.layout.dialog_demo, false)
                .showListener(new DialogInterface.OnShowListener() {
                    private MaterialDialog materialDialog;
                    private View.OnClickListener clickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view.getId() == R.id.btn_save){
                                Toast.makeText(ZpMaterialDialogActivity.this, "选择武功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ZpMaterialDialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                            materialDialog.dismiss();
                        }
                    };
                    @Override
                    public void onShow(DialogInterface dialog) {
                        if (dialog instanceof MaterialDialog) {
                            // 初始化对话框
                            materialDialog = (MaterialDialog) dialog;
                            materialDialog.findViewById(R.id.btn_save).setOnClickListener(clickListener);
                            materialDialog.findViewById(R.id.btn_cancel).setOnClickListener(clickListener);
                            // 设置显示在底部 背景透明
                            Window dialogWindow = materialDialog.getWindow();
                            dialogWindow.setGravity(Gravity.BOTTOM);
                            dialogWindow.setBackgroundDrawableResource(R.color.md_divider_black);
                        }
                    }
                })
                .show();
    }


    public void button04(View view){
        new MaterialDialog.Builder(ZpMaterialDialogActivity.this)
                .title("修改性别")
                .items(R.array.gender_tab)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        switch (which){
                            case 0:
                                Toast.makeText(ZpMaterialDialogActivity.this, "男", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(ZpMaterialDialogActivity.this, "女", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .show();
    }

    // 动画设置
    public void button05(View view){
        new MaterialDialog.Builder(ZpMaterialDialogActivity.this)
                .forceBackgroundTransparent(true)
                .forceWrapContent(true)
                .forceDimAmount(true)
                .forceCustomizedAnimation(true)
                .windowAnimation(R.style.dialogWindowAnim)
                .setDimAmount(.8f)
                .setWindowWidth(340)
                .setWindowHeight(370)
                .cancelable(true)
                .customView(R.layout.dialog_demo, false)
                .contentGravity(GravityEnum.CENTER)
                .backgroundColor(Color.parseColor("#00000000"))
                .widgetColor(Color.parseColor("#00000000"))
                .show();

    }


}
