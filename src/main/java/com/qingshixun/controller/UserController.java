/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.qingshixun.dao.HobbyDao;
import com.qingshixun.dao.ProfessionDao;
import com.qingshixun.dao.UserDao;
import com.qingshixun.model.User;
import com.qingshixun.page.Page;
import com.qingshixun.page.Result;
import com.qingshixun.service.HobbyService;
import com.qingshixun.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService UsertService;

	@Autowired
	private HobbyDao hobbydao;

	@Autowired
	private ProfessionDao professionDao;

	/**
	 * 跳转到用户列表页面
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
		Result Users = UsertService.getAllPage(page);
		model.addAttribute("Users", Users.getList());
		return null;
	}

	@RequestMapping("/lists")
	public String lists(Model model) {
		Page pages = new Page();
		Result Users = UsertService.getAllPage(pages);
		model.addAttribute("pages", Users.getPage());
		return "User/page";
	}

	/**
	 * 跳转到修改用户信息的页面
	 * 
	 * @param id
	 *            用户 id
	 */
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		logger.info("跳转到修改用户页面，用户 id=" + id);
		User User = UsertService.getById(id);
		model.addAttribute("User", User);
		model.addAttribute("hobby", hobbydao.getAll());
		// 跳转页面到 WEB-INF/views/User/edit.jsp
		return "User/edit";
	}

	/**
	 * 执行修改用户
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(HttpServletRequest request, @ModelAttribute User User, MultipartFile imageFile)
			throws IllegalStateException, IOException {
		System.out.println("判断文件为不为空" + imageFile);
		if (imageFile != null && !imageFile.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("/") + "file/";
			// 文件名
			String fileName = imageFile.getOriginalFilename();
			// 创建文件目录
			File file = new File(filePath + fileName);
			file.mkdirs();
			// 转存文件
			imageFile.transferTo(file);
			// 保存文件名
			User.setImage(fileName);
		}
		logger.info("修改用户：" + User);
		UsertService.update(User);

		return "success";
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {

		logger.info("删除用户， id=" + id);
		UsertService.delete(id);
		list(model, 1);
		// 重定向到 /User/list
		return "/User/list";
	}

	/**
	 * 跳转到新增页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {

		model.addAttribute("hobby", hobbydao.getAll());
		model.addAttribute("profession", professionDao.getAll());
		// 跳转页面到 WEB-INF/views/User/add.jsp
		return "User/add";
	}

	/**
	 * 保存用户
	 * 
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, @ModelAttribute User User, MultipartFile imageFile)
			throws IllegalStateException, IOException {
		String filePath = request.getServletContext().getRealPath("/") + "file/";
		// 文件名
		String fileName = imageFile.getOriginalFilename();
		// 创建文件目录
		File file = new File(filePath + fileName);
		file.mkdirs();
		// 转存文件
		imageFile.transferTo(file);
		// 保存文件名
		User.setImage(fileName);
		UsertService.add(User);

		return "success";
	}

	@RequestMapping("/delete")
	public String delete(Integer[] ids) {
		logger.info("删除多个：" + ids);

		UsertService.delete(ids);

		// 重定向到 /User/list
		return "redirect:/User/list";
	}

}
