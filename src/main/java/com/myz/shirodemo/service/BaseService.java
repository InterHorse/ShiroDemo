package com.myz.shirodemo.service;

import java.util.Map;

public interface BaseService {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 将数据封装到Map类型中
     */
    public Map<String, Object> queryInfoByUsername(String username);

    /**
     * 注册功能
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean registerData(String username, String password);
}
