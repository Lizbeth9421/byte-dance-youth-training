package com.ict.util;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lizbeth9421
 */
public class IpUtil {
    public static String getIP(final HttpServletRequest request) {
        String ip = request.getHeader("X-Real-Ip");
        if (!checkIP(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private static boolean checkIP(final String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)) {
            return false;
        }
        return true;
    }

    /**
     * ip地址格式转化
     *
     * @param ip
     * @return
     */
    public static String formatAddress(final String ip) {
        //https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&co=&resource_id=6006&t=1596733806235&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110208767641115282117_1596733275441&_=1596733275479"
        //System.out.println("ip = " + ip);
//        String url = "https://sp1.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&co=&resource_id=5809&t=1629176363177&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery1102017900022110034453_1629176198400&_=1629176198404";
        final String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + ip + "&co=&resource_id=6006&oe=utf8";
        final HttpRequest get = HttpUtil.createGet(url);
        final HttpResponse response = get.execute();
        final String body = response.body();
        String location = "本地局域网";
        if (!"127.0.0.1".equals(ip) && JSONUtil.isJsonObj(body)) {
            final JSONObject jsonObject = JSONUtil.parseObj(body);
            final JSONArray data = jsonObject.getJSONArray("data");
            if (data.size() > 0) {
                location = data.getJSONObject(0).getStr("location", "");
            }
        }
        //使用正则匹配*/
        return location;
    }


}
