/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qingshixun.model.User;
import com.qingshixun.page.Page;

@Repository("userDao")
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	/**
	 * 根据 id 查询商品对象
	 * 
	 * @param id
	 *            商品 id
	 * @return 商品对象
	 */
	public User getById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	/**
	 * 查询所有的商品
	 * 
	 * @return 商品集合
	 */
	public List<User> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	
	/**
	 * 查询所有记录条数
	 * @return
	 */
	public int all(){
			String queryString = "from User";
			Query queruobject = sessionFactory.getCurrentSession().createQuery(queryString);
			return queruobject.list().size();
	}

	/**
	 * 删除多个
	 * 
	 * @param ids
	 */
	public void delete(Integer[] ids) {
		String hql = "delete from User where id in (:ids)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();

	}
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public List queryALLCounts(Page page) {
			String queryString = "from User";
			Query queruobject = sessionFactory.getCurrentSession().createQuery(queryString);
			queruobject.setFirstResult(page.getBeginIndex());
			queruobject.setMaxResults(page.getEveryPage());
			return queruobject.list();
	}
}
