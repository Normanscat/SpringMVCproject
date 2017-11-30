package com.qingshixun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshixun.model.Profession;
import com.qingshixun.page.Page;
import com.qingshixun.page.Result;
import com.qingshixun.service.ProfessionService;

@Controller
@RequestMapping("/profession")
public class ProfessionController {

	@Autowired

	private ProfessionService professionService;

	/**
	 * 跳转到职业列表页面
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
		Result profession = professionService.getAllPage(page);
		model.addAttribute("profession", profession.getList());
		return "profession/list";
	}

	/**
	 * 跳转到修改职业信息的页面
	 * 
	 * @param id
	 * 
	 * 用户 id
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Profession profession = professionService.getById(id);
		model.addAttribute("profession", profession);
		// 跳转页面到 WEB-INF/views/User/add.jsp
		return "profession/add";
	}

	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id) {
		System.out.println("职业的id为" + id);

		if (professionService.delete(id) == "false") {
			System.out.println("controller层+無法刪除");
			model.addAttribute("message", "該职业已被綁定，無法刪除");
			list(model, 1);
			return "/profession/list";
		}
		model.addAttribute("message", "删除成功");
		list(model, 1);
		// 重定向到 /User/list
		return "/profession/list";
	}

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		// 跳转页面到 WEB-INF/views/User/add.jsp
		return "profession/add";
	}

	/**
	 * 保存职业
	 * 
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(@ModelAttribute Profession profession) {
		professionService.add(profession);

		return "success";
	}

}
