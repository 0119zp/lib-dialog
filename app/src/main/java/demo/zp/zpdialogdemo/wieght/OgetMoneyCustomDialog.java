package demo.zp.zpdialogdemo.wieght;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import demo.zp.zpdialogdemo.R;
import demo.zp.zpdialogdemo.info.AresMangeVo;

public class OgetMoneyCustomDialog extends Dialog implements View.OnClickListener{

	private Context context;
	private String title;
	private String names;
	List<AresMangeVo> list;
	TextView animation_mantitle;
	TextView animation_manone;
	TextView animation_mantwo;
	TextView animation_manthree;
	TextView animation_manfour;
	TextView animation_manfive;
	TextView animation_mansix;
	TextView animation_manseven;
	TextView animation_maneight;
	LinearLayout linone;
	LinearLayout lintwo;
	LinearLayout linthree;
	LinearLayout linfour;
	LinearLayout linfive;
	LinearLayout linsix;
	LinearLayout linseven;
	LinearLayout lineight;

	public OgetMoneyCustomDialog(Context context, int theme, String title, List<AresMangeVo> list, String names) {
		super(context, theme);
		this.context = context;
		this.title = title;
		this.names = names;
		this.list=list;
		createDialog();
	}

	protected OgetMoneyCustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
		createDialog();
	}

	public OgetMoneyCustomDialog(Context context) {
		super(context);
		this.context = context;
		createDialog();
	}
	public OgetMoneyCustomDialog(Context context, int theme) {
		super(context,theme);
		this.context = context;
		createDialog();
	}

	private void createDialog(){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.omoney_managercard_dialog, null);
		setContentView(view);
		RelativeLayout animation_mancard_ll = (RelativeLayout)findViewById(R.id.animation_mancard_ll);//外部空白布局

		animation_mantitle= (TextView)findViewById(R.id.animation_mantitle);//第一行文字描述
		 animation_manone = (TextView)findViewById(R.id.animation_manone);//第一行文字描述
		animation_manone.setOnClickListener(this);
		 animation_mantwo = (TextView)findViewById(R.id.animation_mantwo);//第2行文字描述
		animation_mantwo.setOnClickListener(this);
		 animation_manthree = (TextView)findViewById(R.id.animation_manthree);//第3行文字描述
		animation_manthree.setOnClickListener(this);
		 animation_manfour = (TextView)findViewById(R.id.animation_manfour);//第一行文字描述
		animation_manfour.setOnClickListener(this);
		 animation_manfive = (TextView)findViewById(R.id.animation_manfive);//第2行文字描述
		animation_manfive.setOnClickListener(this);
		 animation_mansix = (TextView)findViewById(R.id.animation_mansix);//第3行文字描述
		animation_mansix.setOnClickListener(this);
		 animation_manseven = (TextView)findViewById(R.id.animation_manseven);//第一行文字描述
		animation_manseven.setOnClickListener(this);
		 animation_maneight = (TextView)findViewById(R.id.animation_maneight);//第2行文字描述
		animation_maneight.setOnClickListener(this);
		linone= (LinearLayout) findViewById(R.id.liner_manone);
		lintwo= (LinearLayout) findViewById(R.id.liner_mantwo);
		linthree= (LinearLayout) findViewById(R.id.liner_manthree);
		linfour= (LinearLayout) findViewById(R.id.liner_manfour);
		linfive= (LinearLayout) findViewById(R.id.liner_manfive);
		linsix= (LinearLayout) findViewById(R.id.liner_mansix);
		linseven= (LinearLayout) findViewById(R.id.liner_manseven);
		lineight= (LinearLayout) findViewById(R.id.liner_maneight);

		TextView animation_mancard_cancel_bt = (TextView) findViewById(R.id.animation_mancard_cancel); // 取消按钮
		animation_mancard_cancel_bt.setText(names);
		animation_mancard_cancel_bt.setOnClickListener(this);
		animation_mancard_ll.setOnClickListener(this);
	//	animation_mancard_ll.setAlpha(100f/255);
		intView();
	}

	public interface AnimationCustomDialogInterface{
		public void CancelOne();
		public void CancelTwo();
		public void CancelThree();
		public void CancelFour();
		public void CancelFive();
		public void CancelSix();
		public void CancelSeven();
		public void CancelEight();
		public void CancelCard();
	}

	private AnimationCustomDialogInterface animationCustomDialogInterface;


	public void setAnimationCustomDialogInterface(
			AnimationCustomDialogInterface animationCustomDialogInterface) {
		this.animationCustomDialogInterface = animationCustomDialogInterface;
	}

     private void intView(){
		 if (title != null && title.length() > 0){
			 animation_mantitle.setVisibility(View.VISIBLE);
			 animation_mantitle.setText(title);
		 }
		 for (int i=0;i<list.size();i++){
			 if (i==0){
				 linone.setVisibility(View.VISIBLE);
				 animation_manone.setText(list.get(0).getName());
				 intcolor(list.get(i).getIsRed(),animation_manone);
			 }else if (i==1){
				 lintwo.setVisibility(View.VISIBLE);
				 animation_mantwo.setText(list.get(1).getName());
				 intcolor(list.get(i).getIsRed(),animation_mantwo);
			 }else if (i==2){
				 linthree.setVisibility(View.VISIBLE);
				 animation_manthree.setText(list.get(2).getName());
				 intcolor(list.get(i).getIsRed(),animation_manthree);
			 }else if (i==3){
				 linfour.setVisibility(View.VISIBLE);
				 animation_manfour.setText(list.get(3).getName());
				 intcolor(list.get(i).getIsRed(),animation_manfour);
			 }else if (i==4){
				 linfive.setVisibility(View.VISIBLE);
				 animation_manfive.setText(list.get(4).getName());
				 intcolor(list.get(i).getIsRed(),animation_manfive);
			 }else if (i==5){
				 linsix.setVisibility(View.VISIBLE);
				 animation_mansix.setText(list.get(5).getName());
				 intcolor(list.get(i).getIsRed(),animation_mansix);
			 }else if (i==6){
				 linseven.setVisibility(View.VISIBLE);
				 animation_manseven.setText(list.get(6).getName());
				 intcolor(list.get(i).getIsRed(),animation_manseven);
			 }else if (i==7){
				 animation_maneight.setText(list.get(7).getName());
				 lineight.setVisibility(View.VISIBLE);
				 intcolor(list.get(i).getIsRed(),animation_maneight);
			 }
		 }

   }
	private void intcolor(String n, TextView view){
		if (n.equals("1")){
			view.setTextColor(Color.parseColor("#66ff0000"));
		}else{
			view.setTextColor(Color.parseColor("#6600ff00"));
		}
	}

	@Override
	public void dismiss() {
		if(null!=list){
			list.clear();
			list = null;
		}
		super.dismiss();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.animation_mancard_ll://点击外部隐藏alertdialog
				dismiss();
				cancel();
				break;
			case R.id.animation_manone:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelOne();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_mantwo:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelTwo();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_manthree:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelThree();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_manfour:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelFour();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_manfive:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelFive();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_mansix:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelSix();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_manseven:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelSeven();
				}
				dismiss();
				cancel();
				break;
			case R.id.animation_maneight:
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelEight();
				}
				dismiss();
				cancel();
				break;

			case R.id.animation_mancard_cancel: // 点击取消
				if(null!=animationCustomDialogInterface){
					animationCustomDialogInterface.CancelCard();
				}
				dismiss();
				cancel();
				break;
		}

	}

}
