package demo.zp.zpdialogdemo.zpdialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.bean.ZpStackDialogBean;
import demo.zp.zpdialogdemo.wieght.ZpStackDialog;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class ZpStackDialogActivity extends Activity {

    private ArrayList<ZpStackDialogBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        setData();
    }

    // 显示弹框栈
    public void button(View view){
        showStackDialog();
    }

    // 重置数据
    public void button1(View view){
        setData();
    }

    // 显示单个弹框
    public void button2(View view){
        ZpStackDialogBean bean = new ZpStackDialogBean();
        bean.setId(3);
        bean.setShow(false);

        ZpStackDialog stackDialog = new ZpStackDialog(this);
        stackDialog.show();
        stackDialog.setDialogType(bean);
        stackDialog.setOnClickDismissListner(new ZpStackDialog.OnClickDismissListner() {

            @Override
            public void dismissDialog(ZpStackDialogBean stackDialogBean) {
                Toast.makeText(ZpStackDialogActivity.this, "关闭弹框 - " + stackDialogBean.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showStackDialog() {
        ZpStackDialog stackDialog = new ZpStackDialog(this);
        stackDialog.show();
        stackDialog.setDialogList(list);
        stackDialog.setOnClickDismissListner(new ZpStackDialog.OnClickDismissListner() {

            @Override
            public void dismissDialog(ZpStackDialogBean stackDialogBean) {
                Toast.makeText(ZpStackDialogActivity.this, "关闭弹框 - " + stackDialogBean.getId(), Toast.LENGTH_SHORT).show();
                stackDialogBean.setShow(true);
                if (stackDialogBean.getId() < list.size()){
                    showStackDialog();
                }
            }
        });
        stackDialog.setOnClickSkipListner(new ZpStackDialog.OnClickSkipListner() {

            @Override
            public void clickSkip(ZpStackDialogBean stackDialogBean) {
                Toast.makeText(ZpStackDialogActivity.this, "跳转 == ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    ////////////////////////////////    假数据    /////////////////////////////////
    public void setData(){

        list = new ArrayList<>();
        if (list != null && list.size() > 0){
            list.clear();
        }

        ZpStackDialogBean bean1 = new ZpStackDialogBean();
        bean1.setId(1);
        bean1.setShow(false);
        list.add(bean1);

        ZpStackDialogBean bean2 = new ZpStackDialogBean();
        bean2.setId(2);
        bean2.setShow(false);
        list.add(bean2);

        ZpStackDialogBean bean3 = new ZpStackDialogBean();
        bean3.setId(3);
        bean3.setShow(false);
        list.add(bean3);

        ZpStackDialogBean bean4 = new ZpStackDialogBean();
        bean4.setId(4);
        bean4.setShow(false);
        list.add(bean4);
    }


}
