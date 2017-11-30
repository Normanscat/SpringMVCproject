package com.qingshixun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.dao.HobbyDao;
import com.qingshixun.dao.ProfessionDao;
import com.qingshixun.dao.UserDao;
import com.qingshixun.model.Hobby;
import com.qingshixun.model.Profession;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;
import com.qingshixun.page.PageUtil;
import com.qingshixun.page.Result;

@Service("professionService")
public class ProfessionService {

	@Autowired
	private ProfessionDao professionDao;

	public void add(Profession profession) {
		professionDao.add(profession);
	}

	/**
	 * 根据 id 查询愛好对象
	 * 
	 * @param id
	 *            愛好 id
	 * @return 愛好对象
	 */
	public Profession getById(int id) {
		return professionDao.getById(id);
	}

	/**
	 * 分頁查詢 获取page对象和所有对象
	 * 
	 * @param page
	 * @return
	 */
	public Result getAllPage(Page page) {
		page = PageUtil.creatPage(page, professionDao.all());
		List<Profession> all = professionDao.queryALLCounts(page);
		Result result = new Result();
		result.setList(all);
		result.setPage(page);
		return result;
	}

	public List<Profession> getlist() {
		return professionDao.getAll();
	}

	public String delete(int id) {
		if (professionDao.deleteProfession(id) != 0) {
			System.out.println("service层+不可删除");
			return "false";
		}
		System.out.println("可刪除");
		Profession profession = new Profession();
		profession.setId(id);
		professionDao.delete(profession);
		return "success";
	}

}
