package com.myz.shirodemo.service;


import com.myz.shirodemo.common.utils.UUIDUtil;
import com.myz.shirodemo.dao.UserMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> queryInfoByUsername(String username) {
        return userMapper.queryInfoByUsername(username);
    }

    @Override
    public boolean registerData(String username, String password) {
        // 生成uuid
        String id = UUIDUtil.getOneUUID();

        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("id", id);
        dataMap.put("username", username);
        dataMap.put("password", newPs);

        // 看数据库中是否存在该账户
        Map<String, Object> userInfo = queryInfoByUsername(username);
        if(userInfo == null) {
            userMapper.insertData(dataMap);
            return true;
        }
        return false;
    }
}
