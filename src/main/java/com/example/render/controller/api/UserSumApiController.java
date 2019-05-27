package com.example.render.controller.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.render.dao.user.UserAccountRepository;
import com.example.render.dao.user.UserSumRepository;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserRelate;
import com.example.render.token.CheckAuthImpl;

@RestController
public class UserSumApiController {

	
	@Autowired
	private CheckAuthImpl auth;
	private UserSumRepository userSumRepo;
	private UserAccountRepository User;
	
	public UserSumApiController(CheckAuthImpl auth, UserSumRepository userSumRepo, UserAccountRepository User) {
		this.auth = auth;
		this.userSumRepo = userSumRepo;
		this.User = User;
	}
	
	
	@PostMapping("/sumUser/Add")
	public ResponseEntity<?> sumUser(@RequestBody UserRelate ur, HttpServletRequest req) throws ServletException, IOException{
		
         auth.Authentication();
         
         Schema userme = null;
         try {
        	 userme = (Schema) req.getAttribute("user");
        	 
         }catch(NullPointerException ex) {
        	 System.out.println("user is null");
         }
         
//    	 System.out.println("user is"+ userme.getName());

         userSumRepo.addUsers(ur.getUserId(), userme.getId());
         
		return ResponseEntity.ok().body("posted successfuly");
	}
	
	
	@PostMapping("/falUser/Remove")
	public String minusUser() throws ServletException, IOException {
		auth.Authentication();
		
		return "redirect:/user/suggestion";
	}
}
