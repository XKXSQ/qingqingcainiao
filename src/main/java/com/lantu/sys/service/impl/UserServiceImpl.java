package com.lantu.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lantu.sys.entity.User;
import com.lantu.sys.mapper.UserMapper;
import com.lantu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lantu
 * @since 2023-09-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.getOne(lambdaQueryWrapper);
        if(loginUser!=null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            //获取token，暂时使用UUID，终极方案是jwt
            String key = "user:" + UUID.randomUUID();
            //存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);
            //返回数据
            Map<String,Object> data = new HashMap<>();
            data.put("token",key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfoById(String token) {
        Object obj = redisTemplate.opsForValue().get(token);
        User loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
        if (loginUser != null) {
            Map<String,Object> data = new HashMap<>();
            data.put("name", loginUser.getUsername());
            data.put("avatar", loginUser.getAvatar());
            data.put("introduction", loginUser.getIntroduction());

            List<Object> roleList = this.getBaseMapper().getRoleNamesByUserId(loginUser.getId());
            data.put("roles",roleList);
            return data;
        }
        return null;
    }

    @Override
    public List<Object> getRoleNamesByUserId(Integer userId) {
        return userMapper.getRoleNamesByUserId(userId);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
