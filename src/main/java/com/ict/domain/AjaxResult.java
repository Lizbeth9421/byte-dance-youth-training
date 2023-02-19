package com.ict.domain;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.http.HttpStatus;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author ruoyi
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "status_code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "status_msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    public static final int SUCCESS_CODE = 0;


    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (ObjUtil.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }


    /**
     * 返回成功消息
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 数据
     * @return
     */
    public static AjaxResult success(int code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  提示信息
     * @param data 数据
     * @return
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(SUCCESS_CODE, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 提示信息
     * @return
     */
    public static AjaxResult success(String msg) {
        return new AjaxResult(SUCCESS_CODE, msg, null);
    }

    /**
     * 返回成功消息
     *
     * @return
     */
    public static AjaxResult success() {
        return new AjaxResult(SUCCESS_CODE, "操作成功", null);
    }

    /**
     * 返回失败消息
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 数据
     * @return
     */
    public static AjaxResult error(int code, String msg, Object data) {
        return new AjaxResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, null);
    }

    /**
     * 返回失败消息
     *
     * @param msg  提示信息
     * @param data 数据
     * @return
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    /**
     * 返回失败消息
     *
     * @param msg 提示信息
     * @return
     */
    public static AjaxResult error(String msg) {
        return new AjaxResult(HttpStatus.HTTP_INTERNAL_ERROR, msg, null);
    }

    /**
     * 返回成功消息
     *
     * @return
     */
    public static AjaxResult error() {
        return new AjaxResult(SUCCESS_CODE, "操作失败", null);
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
