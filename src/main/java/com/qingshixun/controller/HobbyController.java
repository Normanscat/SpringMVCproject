package com.qingshixun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qingshixun.dao.UserDao;
import com.qingshixun.model.Hobby;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;
import com.qingshixun.page.Result;
import com.qingshixun.service.HobbyService;

@Controller
@RequestMapping("/hobby")
public class HobbyController {

	@Autowired

	private HobbyService hobbyService;


	/**
	 * 跳转到爱好列表页面
	 * 
	 * @param model
	 * @param request
	 * @param currentpage
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam("currentpage") int currentpage) {
		Page page = new Page();
		System.out.println("currentpage数值为" + currentpage);
		page.setCurrentPage(currentpage);
		Result hobby = hobbyService.getAllPage(page);
		model.addAttribute("hobby", hobby.getList());
		return "hobby/list";
	}

	/**
	 * 跳转到修改愛好信息的页面
	 * 
	 * @param id
	 *            
	 * 用户 id
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Hobby hobby = hobbyService.getById(id);
		model.addAttribute("hobby", hobby);
		// 跳转页面到 WEB-INF/views/User/add.jsp
		return "hobby/add";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		System.out.println("爱好的id为" + id);

		if (hobbyService.delete(id) == "false") {
			System.out.println("controller层+無法刪除");
			model.addAttribute("message", "該爱好已被綁定，無法刪除");
			list(model, 1);
			return "/hobby/list";
		}
		model.addAttribute("message", "删除成功");
		list(model, 1);
		// 重定向到 /User/list
		return "/hobby/list";
	}

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		// 跳转页面到 WEB-INF/views/User/add.jsp
		return "hobby/add";
	}

	/**
	 * 保存爱好
	 * 
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@ModelAttribute Hobby hobby) {
		hobbyService.add(hobby);

		return "success";
	}

}
