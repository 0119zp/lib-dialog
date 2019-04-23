package demo.zp.zpdialogdemo.utils;

import android.content.Context;

/**
 * android客户端工具类
 * 
 * @Description
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2013年7月23日
 * @class com.yitong.zjrc.mbank.utils.yitong.AndroidUtil
 */
public class AndroidUtil {

//	/**
//	 * 设备唯一号存储
//	 */
//	public static final String MOBILE_SETTING = "ZJRC_MOBILE_SETTING";
//
//	/**
//	 * 设备设备号存储
//	 */
//	public static final String MOBILE_UUID2DEVICE = "MOBILE_UUID2DEVICE";
//
//	/**
//	 * 设备唯一表示
//	 */
//	public static final String MOBILE_UUID = "ZJRC_MOBILE_UUID";
//
//	/**
//	 * deviceID的组成为：渠道标志[wifi:wifi|imei:imei|sn:sn] 如果wifi imei sn 标识都获取不到 则
//	 * 通过UUID 生成随机码 缓存在客户端作为机器唯一标识 返回 取到标志[id:id]
//	 *
//	 * 渠道标志为： 1，andriod（a）
//	 *
//	 * @Description
//	 * @param context
//	 * @return
//	 * @author 刘国山 lgs@yitong.com.cn
//	 * @version 1.0 2013年7月23日
//	 */
//	public static String getDeviceId(Context context) {
//		StringBuilder deviceId = new StringBuilder();
//		try {
//			// wifi mac地址
//			// WifiManager wifi = (WifiManager) context
//			// .getSystemService(Context.WIFI_SERVICE);
//			// WifiInfo info = wifi.getConnectionInfo();
//			// String wifiMac = info.getMacAddress();
//			// if (StringTools.isNotEmpty(wifiMac)) {
//			// deviceId.append("wifi:");
//			// deviceId.append(wifiMac);
//			// deviceId.append("|");
//			// Logs.d("getDeviceId : ", deviceId.toString());
//			// }
//
//			// IMEI（imei）
//			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//			String imei = tm.getDeviceId();
//			if (!StringTools.isEmpty(imei)) {
//				deviceId.append("id:");
//				deviceId.append(imei);
////				deviceId.append("|");
//				// Log.d("getDeviceId : ", deviceId.toString());
//			}
//
//			// 序列号（sn）
//			// String sn = tm.getSimSerialNumber();
//			// if (StringTools.isNotEmpty(sn)) {
//			// deviceId.append("sn:");
//			// deviceId.append(sn);
//			// deviceId.append("|");
//			// Logs.d("getDeviceId : ", deviceId.toString());
//			// }
//			/**
//			 * 判断是否有拼接到 wifi | imei | sn 如果长度小于3 代表没有， 则 生成随机码
//			 */
//			if (StringTools.isEmpty(deviceId.toString()) || deviceId.toString().length() < 3) {
//				// 如果上面都没有， 则生成一个id：随机码
//				String uuid = getUUID2DEVICE(context);
//				if (!StringTools.isEmpty(uuid)) {
//					deviceId.append("id:");
//					deviceId.append(uuid);
//				}
//			}
//		} catch (Exception e) {
//			//e.printStackTrace();
//			deviceId.append("id:").append(getUUID2DEVICE(context));
//		}
//
//		return deviceId.toString();
//
//	}
//
//	public static String getDeviceUUID(Context context){
//		byte[] b;
//		try {
//			TelephonyManager TelephonyMgr = (TelephonyManager) context
//					.getApplicationContext().getSystemService(
//							Context.TELEPHONY_SERVICE);
//			String m_szImei = TelephonyMgr.getDeviceId(); // Requires
//			// READ_PHONE_STATE
//			// 2 compute DEVICE ID
//			String m_szDevIDShort = "35"
//					+ // we make this look like a valid IMEI
//					Build.BOARD.length() % 10 + Build.BRAND.length() % 10
//					+ Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
//					+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10
//					+ Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10
//					+ Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10
//					+ Build.TAGS.length() % 10 + Build.TYPE.length() % 10
//					+ Build.USER.length() % 10; // 13 digits
//			// 3 android ID - unreliable
//			String m_szAndroidID = Secure.getString(
//					context.getContentResolver(), Secure.ANDROID_ID);
//			// 4 wifi manager, read MAC address - requires
//			// android.permission.ACCESS_WIFI_STATE or comes as null
//			WifiManager wm = (WifiManager) context.getApplicationContext()
//					.getSystemService(Context.WIFI_SERVICE);
//			String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
//			// 5 Bluetooth MAC address android.permission.BLUETOOTH required
//			BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
//			m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//			String m_szBTMAC = m_BluetoothAdapter.getAddress();
//			System.out.println("m_szBTMAC " + m_szBTMAC);
//			// 6 SUM THE IDs
//			String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID
//					+ m_szWLANMAC + m_szBTMAC;
//			System.out.println("m_szLongID " + m_szLongID);
//			MessageDigest m = null;
//			try {
//				m = MessageDigest.getInstance("MD5");
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//			m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
//			byte p_md5Data[] = m.digest();
//			String m_szUniqueID = new String();
//			for (int i = 0; i < p_md5Data.length; i++) {
//				int b1 = (0xFF & p_md5Data[i]);
//				// if it is a single digit, make sure it have 0 in front (proper
//				// padding)
//				if (b1 <= 0xF)
//					m_szUniqueID += "0";
//				// add number to string
//				m_szUniqueID += Integer.toHexString(b1);
//			}
//			m_szUniqueID = m_szUniqueID.toUpperCase();
//			Log.i("--DeviceID----", m_szUniqueID);
//			Log.d("DeviceIdCheck",
//					"DeviceId that generated MPreferenceActivity:"
//							+ m_szUniqueID);
//			byte[] a = null;
//			a = m_szUniqueID.getBytes("utf-8");
//			b = new byte[16];
//			System.arraycopy(a, 0, b, 0, 16);
//			return new String(b);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return "";
//	}
//
//	/**
//	 * 得到全局唯一UUID
//	 */
//	public static String getUUID(Context context) {
//
//		String uuid="";
//		try {
//			SharedPreferences mShare = context.getSharedPreferences(
//					MOBILE_SETTING, 0);
//			if (mShare != null
//					&& !StringTools.isEmpty(mShare.getString(MOBILE_UUID, ""))) {
//				uuid = mShare.getString(MOBILE_UUID, "");
//			}
//			if (StringTools.isEmpty(uuid)) {
//				uuid = UUID.randomUUID().toString();
//				mShare.edit().putString(MOBILE_UUID, uuid).commit();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("UUID", "获取UUID失败");
//		}finally{
//			return uuid;
//		}
//		// Log.d("getUUID", "getUUID : " + uuid);
//
//	}
//
//
//	/**
//	 * 获取不到设备号的情况下创建UUID
//	 */
//	public static String getUUID2DEVICE(Context context) {
//
//		String uuid="";
//		try {
//			SharedPreferences mShare = context.getSharedPreferences(
//					MOBILE_SETTING, 0);
//			if (mShare != null
//					&& !StringTools.isEmpty(mShare.getString(MOBILE_UUID2DEVICE, ""))) {
//				uuid = mShare.getString(MOBILE_UUID2DEVICE, "");
//			}
//			if (StringTools.isEmpty(uuid)) {
//				uuid = UUID.randomUUID().toString();
//				mShare.edit().putString(MOBILE_UUID2DEVICE, uuid).commit();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("UUID", "获取UUID失败");
//		}finally{
//			return uuid;
//		}
//		// Log.d("getUUID", "getUUID : " + uuid);
//
//	}
//
//	public static String getClientDeviceInfo(Context ctx) {
//		String deviceID = "";
//		String serial = "";
//		deviceID = getDeviceId(ctx);
//		try {
//			Class<?> c = Class.forName("android.os.SystemProperties");
//			Method get = c.getMethod("get", String.class);
//			serial = (String) get.invoke(c, "ro.serialno");
//		} catch (Exception e) {
//			// Log.e("TAG", "get the system sn ERROR!", e);
//		}
//		// Log.d("serial", "deviceID:" + deviceID);
//		String buildVersion = android.os.Build.VERSION.RELEASE;
//		return deviceID + "|android" + "|android|" + buildVersion + "|android";
//	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		if(null!=context){
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		}else{
			return 0;
		}
	}

//	/**
//	 * 获取手机宽度
//	 */
//	public static int getWidth(Activity context) {
//		DisplayMetrics dm = new DisplayMetrics();
//		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//		return dm.widthPixels;
//	}
//
//	/**
//	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
//	 */
//	public static int px2dip(Context context, float pxValue) {
//		final float scale = context.getResources().getDisplayMetrics().density;
//		return (int) (pxValue / scale + 0.5f);
//	}
//
//	/**
//	 * 取得操作系统版本号
//	 */
//	public static String getOSVersion(Context ctx) {
//		return android.os.Build.VERSION.RELEASE;
//	}
//
//	/**
//	 * 获取应用版本号
//	 *
//	 * @param ctx
//	 * @return
//	 */
//	public static String getAppVersion(Context ctx) {
//		PackageInfo pi = null;
//		try {
//			pi = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
//		} catch (NameNotFoundException e) {
//			// Log.e(TAG, e.getMessage(), e);
//		}
//		if(null!=pi){
//			return pi.versionName;
//		}
//		return null;
//	}
//
//	private static AlertDialog dialog;
//	private static AlertDialog.Builder builder;
//
//	/**
//	 * 显示dialog,只有一个确定按钮
//	 */
//	public static void showDialogOk(Context ctx, String message) {
//		dialog = new AlertDialog.Builder(ctx).setTitle("系统提示").setMessage(message)
//				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//					}
//				}).create();
//		showDialog(dialog);
//		return;
//	}
//
//	// 用于屏蔽home键,和back键
//	public static void showDialog(AlertDialog dialog) {
//		AndroidUtil.dialog = dialog;
//		try {
//			dialog.show();
//			dialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {
//				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//					switch (keyCode) {
//					// 屏蔽home键
//					case KeyEvent.KEYCODE_HOME:
//						return true;
//					}
//					return false;
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * @param context
//	 * @return获取手机ip地址
//	 */
////	public static String getLocalIpAddress(Context context) {
////		try {
////            WifiManager wifiManager = (WifiManager) context
////                    .getSystemService(Context.WIFI_SERVICE);
////            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
////            int i = wifiInfo.getIpAddress();
////            StringBuilder sb = new StringBuilder();
////            sb.append(i & 0xFF).append(".");
////            sb.append((i >> 8) & 0xFF).append(".");
////            sb.append((i >> 16) & 0xFF).append(".");
////            sb.append((i >> 24) & 0xFF);
////            return sb.toString();
////        } catch (Exception ex) {
////            return "" ;
////        }
////	}
//
//	public static String getImei(Context context) {
//
//		String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//
//		return imei;
//	}
//	/**
//	 * @author mysterylove caiyu.happy@163.com
//	 * @param String data,String format
//	 * @return Date
//	 * @description data时间，format为格式如yyyyMMddHHmmss或者yyyy-MM-dd等
//	 * */
//
//	public static Date strToDateLongx(String strDate, String format) {//时间格式化
//		  SimpleDateFormat formatter = new SimpleDateFormat(format);//yyyy-MM-ddHHmmss
//		  ParsePosition pos = new ParsePosition(0);
//		  Date strtodate = formatter.parse(strDate, pos);
//		  return strtodate;
//		}
//	/**
//	 * @author mysterylove caiyu.happy@163.com
//	 * @param Date data1,Date data2,String mode
//	 * @return long
//	 * @description data1,date2时间,d2-d1，mode为模式值为day,minute
//	 * */
//	public static long CalculateTime(Date d1, Date d2, String mode){
//		//计算差值，分钟数
//		 long minutes=(d2.getTime()-d1.getTime())/(1000*60);
//		 //计算差值，天数
//		 long days=(d2.getTime()-d1.getTime())/(1000*60*60*24);
//		 if(mode.equals("day")){
//			 return days;
//		 }else if(mode.equals("minute")){
//			 return minutes;
//		 }else{
//			 return 0;
//		 }
//	}
//
//	public static String getTwoDay(String sj1, String sj2) {
//        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH");
//        long day = 0;
//        try {
//            Date date = myFormatter.parse(sj1);
//            Date mydate = myFormatter.parse(sj2);
//            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
//        } catch (Exception e) {
//            return "";
//        }
//        return day + "";
//    }
//
//	public static String getVersionCode(Context context) {
//		try {
//			PackageInfo pinfo = context.getPackageManager()
//					.getPackageInfo(context.getPackageName(),
//							PackageManager.GET_CONFIGURATIONS);
//			return pinfo.versionName;
//		} catch (NameNotFoundException e) {
//		}
//
//		return "";
//	}
//
//
//	/**
//	 * 手机号正则判断
//	 * @param phoneNum
//	 * @return boolean
//	 */
//	public static boolean matchesPhoneNum(final String phoneNum){
//		//定义判别用户手机号的正则表达式
//		Pattern idNumPattern = Pattern.compile("1\\d{10}");
//        //通过Pattern获得Matcher
//		Matcher idNumMatcher = idNumPattern.matcher(phoneNum);
//		return idNumMatcher.matches();
//	}
//
////	/**
////	 * 验证身份证号码
////	 * $identity       身份证号码
////	 * @return        boolean
////	 */
////	public  static boolean checkIdentity(String card)
////	{
////		char[] identity=card.toCharArray();
////		int[]iW = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
////		char[] szVerCode = new char[]{'1','0','X','9','8','7','6','5','4','3','2'};
////		int sum = 0;
////		for (int i = 0 ; i < 17 ; i++)
////			sum += Integer.parseInt(identity[i]+"") * iW[i];
////		return szVerCode[sum % 11] == identity[17];
////	}
//
//	/**
//	 *
//	 * @param str
//	 * @return 判断字符串是否可以强转为int类型
//	 */
//	public static boolean stringOfInteger(String str){
//		Pattern pattern = Pattern.compile("^[0-9]*$");
//		Matcher matcher = pattern.matcher(str);
//		return matcher.matches();
//	}
//
//
//		public static Double getResultJ(double a, String b){
//			BigDecimal bshouldpay = new BigDecimal(a);
//			bshouldpay = bshouldpay.setScale(2, BigDecimal.ROUND_HALF_UP);
//
//			BigDecimal bs = new BigDecimal(b);
//			bs = bs.setScale(2, BigDecimal.ROUND_HALF_UP);
//			bshouldpay = bshouldpay.subtract(bs);
//			double result= Double.parseDouble(bshouldpay.toString());
//			return result;
//		}
//		//判断同名
//		public static boolean IsEquals(String receive, String pay) {
//			String rType = "";
//			String pType = "";
//			Pattern p = Pattern.compile("[0-9]*");
//			for (int i = receive.length(); i > 0; i--) {
//				Matcher m = p.matcher(receive.substring(i - 1, i));
//				if (m.matches()) {
//					rType = receive.substring(i - 1, i).toString()+rType.toString();
//				}
//			}
//			rType="000000" + rType.toString();
//			rType = rType.substring(rType.length() - 6, rType.length());
//			for (int i = pay.length(); i > 0; i--) {
//				Matcher m = p.matcher(pay.substring(i - 1, i));
//				if (m.matches()) {
//					pType = pay.substring(i - 1, i).toString()+pType.toString();
//				}
//			}
//			pType="000000" + pType.toString();
//			pType = pType.substring(pType.length() - 6, pType.length());
//			if (rType.equals(pType)) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//
//		/**
//		 *
//		 * @param editText 卡号显示方式，中间用空格隔开
//		 * @param num 总位数
//		 * @param each 每组位数
//		 */
//		public static void initEdit(final EditText editText , final int num , final int each) {
//			// TODO Auto-generated method stub
//			/**
//			 * 每隔四位用空格隔开
//			 */
//			editText.addTextChangedListener(new TextWatcher() {
//
//				int beforeTextLength = 0;
//	            int onTextLength = 0;
//	            boolean isChanged = false;
//
//	            int location = 0;// 记录光标的位置
//	            private char[] tempChar;
//	            private StringBuffer buffer = new StringBuffer();
//	            int konggeNumberB = 0;
//
//				@Override
//				public void onTextChanged(CharSequence s, int start, int before, int count) {
//					 onTextLength = s.length();
//		                buffer.append(s.toString());
//		                if (onTextLength == beforeTextLength || onTextLength <= each || isChanged) {
//		                    isChanged = false;
//		                    return;
//		                }
//		                isChanged = true;
//
//				}
//
//				@Override
//				public void beforeTextChanged(CharSequence s, int start, int count,
//											  int after) {
//					// TODO Auto-generated method stub
//	                beforeTextLength = s.length();
//	                if (buffer.length() > 0) {
//	                    buffer.delete(0, buffer.length());
//	                }
//	                konggeNumberB = 0;
//	                for (int i = 0; i < s.length(); i++) {
//	                    if (s.charAt(i) == ' ') {
//	                        konggeNumberB++;
//	                    }
//	                }
//
//				}
//
//				@Override
//				public void afterTextChanged(Editable s) {
//					if (isChanged) {
//	                    location = editText.getSelectionEnd();
//	                    int index = 0;
//	                    while (index < buffer.length()) {
//	                        if (buffer.charAt(index) == ' ') {
//	                            buffer.deleteCharAt(index);
//	                        } else {
//	                            index++;
//	                        }
//	                    }
//
//	                    index = 0;
//	                    int konggeNumberC = 0;
//						while (index < buffer.length()) {
//							if (index % (each+1) == each) {
//								buffer.insert(index, ' ');
//								konggeNumberC++;
//							}
//							index++;
//						}
//
//	                    if (konggeNumberC > konggeNumberB) {
//	                        location += (konggeNumberC - konggeNumberB);
//	                    }
//
//	                    tempChar = new char[buffer.length()];
//	                    buffer.getChars(0, buffer.length(), tempChar, 0);
//	                    String str = buffer.toString();
//	                    if (location > str.length()) {
//	                        location = str.length();
//	                    } else if (location < 0) {
//	                        location = 0;
//	                    }
//
//	                    editText.setText(str);
//	                    Editable etable = editText.getText();
//
//	                    int lenght;//Edit总共占用的位数
//	                    if(num % each == 0){
//	                    	lenght = num + num/each - 1;
//	                    }else{
//	                    	lenght = num + num/each;
//	                    }
//	                    if(location>=lenght){
//	                    	location = lenght;
//	                    }
//	                    Selection.setSelection(etable, location);
//	                    isChanged = false;
//	                }
//
//				}
//			});
//
//		}
//
//	/**
//	 * 防止用户连续点击导致的重复事件
//	 */
//	private static long lastClickTime;
//	public synchronized static boolean isFastClick() {
//		long time = System.currentTimeMillis();
//		if ( time - lastClickTime < 500) {
//			return true;
//		}
//		lastClickTime = time;
//		return false;
//	}
//	/**
//	 * param yyyy-MM-dd
//	 * 根据日期计算星期几
//	 * */
//	public static String getWeekDay(String time){
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar= Calendar.getInstance();
//		StringBuffer stringBuffer=null;
//		try {
//			calendar.setTime(simpleDateFormat.parse(time));
//			stringBuffer=new StringBuffer();
//		}catch (Exception e){
//			Log.e("System_Error",e.getMessage());
//			return null;
//		}
//		switch (calendar.get(Calendar.DAY_OF_WEEK)){
//			case 1:
//				stringBuffer.append("周日");
//				break;
//			case 2:
//				stringBuffer.append("周一");
//				break;
//			case 3:
//				stringBuffer.append("周二");
//				break;
//			case 4:
//				stringBuffer.append("周三");
//				break;
//			case 5:
//				stringBuffer.append("周四");
//				break;
//			case 6:
//				stringBuffer.append("周五");
//				break;
//			case 7:
//				stringBuffer.append("周六");
//				break;
//			default:
//				break;
//		}
//		return stringBuffer.toString();
//	}

}
