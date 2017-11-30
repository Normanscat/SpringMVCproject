package com.qingshixun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingshixun.dao.HobbyDao;
import com.qingshixun.dao.UserDao;
import com.qingshixun.model.Hobby;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;
import com.qingshixun.page.PageUtil;
import com.qingshixun.page.Result;

@Service("hobbyService")
public class HobbyService {

	@Autowired
	private HobbyDao hobbydao;
	
	
    public void add(Hobby hobby) {
    	hobbydao.add(hobby);
    }
    
    /**
     * 根据 id 查询愛好对象 
     * @param id 愛好 id
     * @return 愛好对象
     */
    public Hobby getById(int id) {
        return hobbydao.getById(id);
    }

	/**
	 * 分頁查詢 获取page对象和所有对象
	 * 
	 * @param page
	 * @return
	 */
	public Result getAllPage(Page page) {
		page = PageUtil.creatPage(page, hobbydao.all());
		List<Hobby> all = hobbydao.queryALLCounts(page);
		Result result = new Result();
		result.setList(all);
		result.setPage(page);
		return result;
	}

	public List<Hobby> getlist() {
		return hobbydao.getAll();
	}

	public String delete(int id) {
		if (hobbydao.deleteHobby(id) != 0) {
			System.out.println("service层+不可删除");
			return "false";
		}
		System.out.println("可刪除");
		Hobby hobby = new Hobby();
		hobby.setId(id);
		hobbydao.delete(hobby);
		return "success";
	}

}
