package com.scau.zwp.elevmanage.common;

import com.scau.zwp.elevmanage.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/23
 */
@Data
public class TokenUser implements Serializable {

    private String token;//用户登录生成对的token
    private User user;//用户的信息

    /**
     * 功能描述: 无参构造
     *
     * @return :
     */
    public TokenUser() {
    }

    /**
     * 功能描述: 有参构造
     *
     * @param token token
     * @param user  用户
     * @return : 返回结果对象
     */
    public TokenUser(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
