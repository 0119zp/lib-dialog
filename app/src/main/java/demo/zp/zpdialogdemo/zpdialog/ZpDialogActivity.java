package demo.zp.zpdialogdemo.zpdialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.info.AresMangeVo;
import demo.zp.zpdialogdemo.wieght.Animationdialog;
import demo.zp.zpdialogdemo.wieght.IosSureCancleDialog;
import demo.zp.zpdialogdemo.wieght.OgetMoneyCustomDialog;
import demo.zp.zpdialogdemo.wieght.WaitDialog;
import demo.zp.zpdialogdemo.wieght.ZpCommonDialog;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ZpDialogActivity extends Activity{


    private ZpCommonDialog zpDialog;
    private WaitDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zp_dialog);
    }

    // 提示dialog
    public void button01(View view){
        zpDialog = new ZpCommonDialog(this, 0, 0);
        zpDialog.setMode(2);
        zpDialog.setDescrib("我是自定义dialog");
        zpDialog.show();
    }

    // 加载dialog
    public void button02(View view){
        showOrCloseWaitDialog(ZpDialogActivity.this);
    }

    // 确定&取消dialog
    public void button03(View view){

        new IosSureCancleDialog(ZpDialogActivity.this, "标题", "我是内容", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        }, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        },0).show();

    }

    public void button04(View view){

        new IosSureCancleDialog(ZpDialogActivity.this, "我是内容", "取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        }, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        },1).show();

    }

    public void button05(View view){

        new IosSureCancleDialog(ZpDialogActivity.this, "我是内容", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        },4).show();

    }

    public void button06(View view){

        new IosSureCancleDialog(ZpDialogActivity.this, "标题", "我是内容",  "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZpDialogActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        },3).show();

    }

    public void button07(View view){

        final Animationdialog animationdialog = new Animationdialog(ZpDialogActivity.this,R.style.Transparents,"删除卡片后下次使用该卡支付需重新添加该卡，是否继续删除？","狠心删除");
        animationdialog.setAnimationCustomDialogInterface(new Animationdialog.AnimationCustomDialogInterface() {
            @Override
            public void DeleteCard() {
                animationdialog.dismiss();
                Toast.makeText(ZpDialogActivity.this,"删除",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelCard() {
                animationdialog.dismiss();
                Toast.makeText(ZpDialogActivity.this,"取消",Toast.LENGTH_SHORT).show();
            }
        });

        // dialog的一些设置
        Window window = animationdialog.getWindow();
        // 设置dialog的显示位置
//        window.setGravity(Gravity.BOTTOM);
        // 添加动画
        window.setWindowAnimations(R.style.mystyle);

        animationdialog.show();

    }

    public void button08(View view){

        List<AresMangeVo> list = new ArrayList<AresMangeVo>();
        if (list != null && list.size() > 0){
            list.clear();
        }
        list.add(new AresMangeVo("从相册中选取", ""));
        list.add(new AresMangeVo("手机拍照", ""));
        list.add(new AresMangeVo("屠龙宝刀", ""));
        list.add(new AresMangeVo("世纪佳缘", ""));
        list.add(new AresMangeVo("倚天剑", ""));
        list.add(new AresMangeVo("葵花宝典", ""));

        OgetMoneyCustomDialog dialog = new OgetMoneyCustomDialog(this, R.style.Transparents, "", list, "取消");
        dialog.setAnimationCustomDialogInterface(new OgetMoneyCustomDialog.AnimationCustomDialogInterface() {
            @Override
            public void CancelOne() {
                Toast.makeText(ZpDialogActivity.this, "从相册中选取", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelTwo() {
                Toast.makeText(ZpDialogActivity.this, "手机拍照", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelThree() {
                Toast.makeText(ZpDialogActivity.this, "屠龙宝刀", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelFour() {
                Toast.makeText(ZpDialogActivity.this, "世纪佳缘", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelFive() {
                Toast.makeText(ZpDialogActivity.this, "倚天剑", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelSix() {
                Toast.makeText(ZpDialogActivity.this, "葵花宝典", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void CancelSeven() {

            }

            @Override
            public void CancelEight() {

            }

            @Override
            public void CancelCard() {

            }
        });
        dialog.show();

    }




    // 加载dialog 的显示与隐藏
    protected void showOrCloseWaitDialog(Activity activity){

        if(null== waitDialog){
            waitDialog = new WaitDialog(this, R.style.dialog);
        }
        if(!isFinishing()){
            if(waitDialog.isShowing()){
                waitDialog.dismiss();
            }else{
                waitDialog.show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        showOrCloseWaitDialog(ZpDialogActivity.this);
//        zpDialog.dismiss();
    }
}
