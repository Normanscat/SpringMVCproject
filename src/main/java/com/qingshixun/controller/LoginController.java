/********************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*********************************************/
package com.qingshixun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qingshixun.model.User;
import com.qingshixun.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService UsertService;

	private String message;

    @RequestMapping("/login")
    public String index(Model model) {
        // 跳转页面
        return "login";
    }
    /**
     * 登入验证
     * 
     * @param username
     * @return
     */
    @RequestMapping("/vail")
    public String login(Model model,@RequestParam("username") String username,@RequestParam("password") String password){
    	
    	System.out.println("查询用户为"+username);
    	
    	String a = UsertService.login(username, password);
    	if(a=="false"){
    		model.addAttribute("message","账号或密码错误");
    		return "login";
    	}
    	
		return "index";
    }
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
