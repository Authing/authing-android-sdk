package cn.authing.api.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.authing.api.Authing;
import cn.authing.api.data.Config;

public class Util {

    public static String getHost(Config config) {
        if (isIp(Authing.getHost())) {
            return Authing.getHost();
        } else if (config != null) {
            String appHost = config.getIdentifier() + "." + Authing.getHost();
            String ssoHost = config.getRequestHostname();
            return Util.isNull(ssoHost) ? appHost : ssoHost;
        } else {
            ALog.e("Guard", "invalid host");
            return "core." + Authing.getHost();
        }
    }

    public static String getLangHeader() {
        String lang = Locale.getDefault().getLanguage();
        return (!Util.isNull(lang) && lang.contains("zh")) ? "zh-CN" : "en-US";
    }

    public static List<String> toStringList(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        int size = array.length();
        for (int i = 0; i < size; i++) {
            list.add((array.getString(i)));
        }
        return list;
    }

    public static boolean isNull(String s) {
        return TextUtils.isEmpty(s) || "null".equals(s);
    }

    public static boolean isIp(String name) {
        if (name == null || name.length() == 0) {
            return true;
        }

        // ip v6
        if (name.contains(":")) {
            return true;
        }

        // ip v4
        String numRange = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
                + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
                + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])" + "\\."
                + "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";

        Pattern ip_pattern = Pattern.compile(numRange);
        Matcher match = ip_pattern.matcher(name);
        return match.matches();
    }

    public static String randomString(int length) {
        String seed;
        Random rand = new Random();
        int seedLength;
        String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
        String digits = "1234567890";
        seed = asciiUpperCase + asciiLowerCase + digits;
        seedLength = seed.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(seed.charAt(rand.nextInt(seedLength)));
        }
        return sb.toString();
    }

    public static JSONObject pareUnderLine(JSONObject object) {
        if (object == null) {
            return object;
        }
        try {
            JSONObject parsedObject = new JSONObject();
            Iterator<String> sIterator = object.keys();
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                String value = object.getString(key);
                String newKey = underlineToHump(key);
                parsedObject.put(newKey, value);
            }
            return parsedObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String underlineToHump(String str) {
        //正则匹配下划线及后一个字符，删除下划线并将匹配的字符转成大写
        Matcher matcher = Pattern.compile("_([a-z])").matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配的子串替换成指定字符串，并且将替换后的子串及之前到上次匹配的子串之后的字符串添加到StringBuffer对象中
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的字符串也添加到StringBuffer对象中
            matcher.appendTail(sb);
        } else {
            //去除除字母之外的前面带的下划线
            return sb.toString().replaceAll("_", "");
        }
        return underlineToHump(sb.toString());
    }

    public static String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuilder sb = new StringBuilder();
        if (null != userAgent){
            for (int i = 0, length = userAgent.length(); i < length; i++) {
                char c = userAgent.charAt(i);
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", (int) c));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
