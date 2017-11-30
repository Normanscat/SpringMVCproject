package com.qingshixun.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.model.Profession;
import com.qingshixun.page.Page;

@Transactional
@Repository("professionDao")
public class ProfessionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void add(Profession Profession) {
		System.out.println("保存的名字为" + Profession.getName());
		if (Profession.getId() != null) {
			sessionFactory.getCurrentSession().update(Profession);
		}
		sessionFactory.getCurrentSession().save(Profession);
	}

	/**
	 * 根据 id 查询职业对象
	 * 
	 * @param id
	 * 
	 * @return 职业对象
	 */
	public Profession getById(int id) {
		return (Profession) sessionFactory.getCurrentSession().get(Profession.class, id);
	}

	public void delete(Profession Profession) {
		sessionFactory.getCurrentSession().delete(Profession);
	}

	/**
	 * 查询所有职业总数
	 * 
	 * @return
	 */
	public int all() {
		String sql = "from Profession";
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
		String queryString = "from Profession";
		Query queruobject = sessionFactory.getCurrentSession().createQuery(queryString);
		queruobject.setFirstResult(page.getBeginIndex());
		queruobject.setMaxResults(page.getEveryPage());
		return queruobject.list();
	}

	/**
	 * 查询职业集合
	 * 
	 * @return
	 */
	public List<Profession> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Profession").list();
	}

	public int deleteProfession(int id) {
		String sql = "from User where profession=(:id)";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		System.out.println("查詢的职业為" + id);
		return query.list().size();
	}

}
