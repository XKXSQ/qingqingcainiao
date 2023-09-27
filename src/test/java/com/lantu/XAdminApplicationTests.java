//package com.lantu;
//
//import com.lantu.sys.entity.User;
//import com.lantu.sys.mapper.UserMapper;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//class XAdminApplicationTests {
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Test
//    public void testSelect() {
//        System.out.println("........................................");
//        List<User> userList = userMapper.selectList(null);
//        userList.forEach(System.out::println);
//    }
//
//    @Test
//    public void testAdd(){
//        User user = new User(null,"胡伟","123456","123@qq.com","123456789",1,"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",0);
//        userMapper.insert(user);
//
//    }
//
//    @Test
//    public void testUpdate(){
//        User user = new User(7,"江峰","123456","123@qq.com","123456789",1,"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",0);
//        userMapper.updateById(user);
//    }
//
//    @Test
//    public void testDelete() {
//        userMapper.deleteById(7);
//    }
//}
