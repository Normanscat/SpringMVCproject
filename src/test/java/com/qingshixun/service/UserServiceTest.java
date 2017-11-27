/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingshixun.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService productService;

    @Test
    public void testAdd() {
        User product = new User();
        product.setName("张三");
        product.setSex("男");

        User product2 = new User();
        product2.setName("李四");
        product2.setSex("男");
        productService.add(product);
        productService.add(product2);
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetById() {
        User product = productService.getById(1);
        System.out.println(product);
    }

    @Test
    public void testGetAll() {
        List<User> all = productService.getAll();
        System.out.println(all);
    }

}
