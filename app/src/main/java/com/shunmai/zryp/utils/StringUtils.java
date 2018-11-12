package com.shunmai.zryp.utils;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by lixingjun on 7/1/16.
 */
public class StringUtils {

    /**
     * 获取当前时间 如：2016-07-01 18:01:10
     *
     * @return
     */
    public static String getFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }


    /**
     * 判断字符串是否没有值
     *
     * @param s
     * @return
     */
    public static boolean isEmptyString(String s) {
        boolean ret = false;
        if ((s == null) || (s.length() == 0))
            ret = true;
        return ret;
    }

    /**
     * 字符串转int
     *
     * @param str
     * @return
     */
    public static int str2int(String str) {
        int val = 0;
        try {
            val = Integer.parseInt(str);
        } catch (NumberFormatException e) {

        }
        return val;
    }


    public static Spanned htmlFontClor(Map<String, String> map) {
        return Html.fromHtml(convertToHtml("<font color='#145A14'>text</font>"));
    }

    public static String convertToHtml(String htmlString) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<![CDATA[");
        stringBuilder.append(htmlString);
        stringBuilder.append("]]>");
        return stringBuilder.toString();
    }

    public static SpannableStringBuilder changeStringColor(String Content, String ColorRGB, int Start, int End) {
        SpannableStringBuilder builder = new SpannableStringBuilder(Content);
        ForegroundColorSpan blueColorSpan = new ForegroundColorSpan(Color.parseColor(ColorRGB));
        builder.setSpan(blueColorSpan, Start, End, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 随机生成字符串
     *
     * @param length 字符串的长度
     * @param source 规定的字符
     * @return 随机字符串
     */
    public static String createRandomString(int length, String source) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int position = random.nextInt(source.length());
            builder.append(source.charAt(position));
        }
        return builder.toString();
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static String DoubleFormat(double num) {
        return String.format("%.2f", num);
    }
}
