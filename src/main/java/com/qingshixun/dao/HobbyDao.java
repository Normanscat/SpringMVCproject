package com.qingshixun.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.model.Hobby;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;

@Transactional
@Repository("hobbyDao")
public class HobbyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserDao userDao;

	public void add(Hobby hobby) {
		System.out.println("保存的名字为" + hobby.getName());
		if (hobby.getId() != null) {
			sessionFactory.getCurrentSession().update(hobby);
		}
		sessionFactory.getCurrentSession().save(hobby);
	}

	/**
	 * 根据 id 查询愛好对象
	 * 
	 * @param id
	 * 
	 * @return 愛好对象
	 */
	public Hobby getById(int id) {
		return (Hobby) sessionFactory.getCurrentSession().get(Hobby.class, id);
	}

	public void delete(Hobby hobby) {
		sessionFactory.getCurrentSession().delete(hobby);
	}

	/**
	 * 查询所有爱好总数
	 * 
	 * @return
	 */
	public int all() {
		String sql = "from Hobby";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return query.list().size();
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List queryALLCounts(Page page) {
		String queryString = "from Hobby";
		Query queruobject = sessionFactory.getCurrentSession().createQuery(queryString);
		queruobject.setFirstResult(page.getBeginIndex());
		queruobject.setMaxResults(page.getEveryPage());
		return queruobject.list();
	}

	public List<Hobby> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Hobby").list();
	}

	public int deleteHobby(int id) {
		String sql = "from User where hobby=(:id)";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		System.out.println("查詢的愛好為" + id);
		return query.list().size();
	}

}
