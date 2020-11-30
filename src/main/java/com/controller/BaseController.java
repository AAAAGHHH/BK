package com.controller;

import com.entity.UserDomain;
import com.utils.Cache;
import com.utils.Tale;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {


    protected Cache cache = Cache.single();


    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public UserDomain user(HttpServletRequest request) {
        return Tale.getLoginUser(request);
    }

    /**
     * 获取请求绑定登录用户Uid
     * @param request
     * @return
     */
    public Integer getUid(HttpServletRequest request) {
        return this.user(request).getUid();
    }


    /**
     * 数组转字符串
     * @param arr 数组
     * @return String
     */
    public String join(String[] arr) {
        StringBuffer buffer = new StringBuffer();
        String[] temp = arr;
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            String item = temp[i];
            buffer.append(",").append(item);
        }

        return buffer.length() > 0 ? buffer.substring(1) : buffer.toString();
    }


}
