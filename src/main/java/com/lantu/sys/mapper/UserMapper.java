package com.lantu.sys.mapper;

import com.lantu.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lantu
 * @since 2023-09-20
 */
public interface UserMapper extends BaseMapper<User> {

    List<Object> getRoleNamesByUserId(Integer userId);
}
