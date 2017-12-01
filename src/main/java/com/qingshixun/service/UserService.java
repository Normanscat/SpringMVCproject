/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.UserDao;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;
import com.qingshixun.page.PageUtil;
import com.qingshixun.page.Result;

@Service("userService")
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public String login(String name, String password) {

		if (userDao.login(name, password) != 1) {
			return "false";
		}
		return "success";
	}

	/**
	 * 分頁查詢 获取page对象和所有对象
	 * 
	 * @param page
	 * @return
	 */
	public Result getAllPage(Page page) {
		page = PageUtil.creatPage(page, userDao.all());
		List<User> all = userDao.queryALLCounts(page);
		Result result = new Result();
		result.setList(all);
		result.setPage(page);
		return result;
	}

	/**
	 * 模糊查询
	 * 
	 * @param user
	 * @return 
	 */
	public List<User> Search(String search){
		return userDao.search(search);
	}

	public void add(User user) {
		userDao.add(user);
	}

	public void delete(int id) {
		User user = new User();
		user.setId(id);
		userDao.delete(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 根据 id 查询商品对象
	 * 
	 * @param id
	 *            商品 id
	 * @return 商品对象
	 */
	public User getById(int id) {
		return userDao.getById(id);
	}

	/**
	 * 查询所有的商品
	 * 
	 * @return 商品集合
	 */
	public List<User> getAll() {
		return userDao.getAll();
	}

	public void delete(Integer[] ids) {
		userDao.delete(ids);
	}

}
