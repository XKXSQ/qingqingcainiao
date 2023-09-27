package com.lantu.sys.service;

import com.lantu.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lantu
 * @since 2023-09-20
 */
public interface IUserService extends IService<User> {


    Map<String, Object> login(User user);

    Map<String, Object> getUserInfoById(String token);

    List<Object> getRoleNamesByUserId(Integer userId);

    void logout(String token);
}
