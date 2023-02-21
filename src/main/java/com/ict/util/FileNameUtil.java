package com.ict.util;

import cn.hutool.core.util.IdUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/13:36
 */
public class FileNameUtil {
    private static final SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 根据当前时间配合uuid生成文件名
     *
     * @param filename 原始文件名
     * @return
     */
    public static String createFileNameUseTime(String filename) {
        //文件后缀
        String fileSuffix = filename.substring(filename.indexOf("."));
        //当前时间
        String time = FileNameUtil.format1.format(new Date());
        String uuid = IdUtil.fastSimpleUUID();
        return time + "/" + uuid + fileSuffix;
    }
}
