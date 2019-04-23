package demo.zp.zpdialogdemo.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class ZpUtils {

    public static final Pattern STR = Pattern.compile("(\\S+?)#+");


    /**
     * String转换成集合
     * @return
     */
    public static ArrayList<String> String2List(String value){
        ArrayList<String> list = new ArrayList<>();

        Matcher matcher = STR.matcher(value);
        while (matcher.find()){
            String str = matcher.group(1);

            Log.e("zhang","==== str ==== " + str);
            if (str != null && str.length() > 0){
                list.add(str);
            }
        }
        return list;
    }


    /**
     * 集合转换成String
     * @param stringList
     * @return
     */
    public static String listToString(ArrayList<String> stringList){
        if (stringList==null) {
            return null;
        }

        StringBuilder result=new StringBuilder();

        for (String string : stringList) {
            result.append(string + "#");
        }
        return result.toString();
    }

}
