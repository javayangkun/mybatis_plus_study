package com.mp.study;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.study.domain.User;
import com.mp.study.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //selectList
    @Test
    public void testSelectList() {
        //传入参数为null,代表不带任何附加条件查询
        List<User> users = userMapper.selectList(null);
        for (User u : users) {
            System.out.println(u);
        }
    }
    // insert
    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(100);
        user.setName("老毛子");
        user.setEmail("test9@baomidou.com");
        int i = userMapper.insert(user);//返回受影响的行数
        System.out.println(user); //id自动回填了
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setName("猫咪");
        int i = userMapper.updateById(user);
        System.out.println(user);
    }
    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //注意测试乐观锁，要先查询，
        User user = userMapper.selectById(1302136900173213697L);
        //再修改
        user.setAge(701);
        int i = userMapper.updateById(user);
        System.out.println(i);

    }
    // 多个id的批量查询
    @Test
    public void testBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 4L));
        System.out.println(users);
    }
    //分页功能测试
    @Test
    public void testPage(){
        Page<User> page = new Page<>(1,5);
        //返回封装好的page对象
        userMapper.selectPage(page, null);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getPages());//返回总页数
        System.out.println(page.getRecords());//返回数据集合
        System.out.println(page.getSize());//返回每页需要的数据大小
        System.out.println(page.getTotal());//返回总数目

        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }

    //逻辑删除测试
    @Test
    public void testDelete(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }

    //wrapper 包装的方法测试
    @Test
    public void testWrapper(){
        // ge >= gt > le <= lt >
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",30)
        .isNull("version"); //链式编程
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testWrapper1(){
        // eq、ne
        QueryWrapper<User> wrapper = new QueryWrapper<>();
         wrapper.ne("name", "老猫子");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void testWrapper2(){
        //between、notBetween 包含大小边界
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",60,100);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void testWrapper3(){
        //allEq 列出的条件全部相等
        Map<String,Object> map = new HashMap<>();
        map.put("name","老猫子");
        map.put("age",20);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        //注意selectOne 返回多条结果会出错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    public void testWrapper4(){
        //allEq 列出的条件全部相等
        Map<String,Object> map = new HashMap<>();
        map.put("name","老猫子");
        map.put("age",20);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        //注意selectOne 返回多条结果会出错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    public void testWrapper5(){
        //like not like likeRight likeLeft
        QueryWrapper<User> wrapper = new QueryWrapper<>();
       // wrapper.like("name","老"); //name LIKE '%老%'
        wrapper.likeRight("name","t"); //LIKE 't%'
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }
    @Test
    public void testWrapper6(){
        //in、notIn、inSql、notinSql、exists、notExists
        QueryWrapper<User> wrapper = new QueryWrapper<>();
       // wrapper.in("age",Arrays.asList(99,101,701)); 同sql中in
        String sql = "select age from user where name like '%老%' ";
        wrapper.inSql("age",sql); //in (子查询）
        List<User> users = userMapper.selectList(wrapper);
        System.out.println("=======================================");
        System.out.println(users);
    }

    @Test
    public void testWrapper7(){
        //or and 这里使用的是 UpdateWrapper
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();

    }

    @Test
    public void testWrapper8(){
        //orderBy、orderByDesc、orderByAsc
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println("=======================================");
        System.out.println(users);
    }

    @Test
    public void testWrapper9(){
        //指定要查询的列 使用 select
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name","age","email");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println("=======================================");
        System.out.println(users);
    }
}
