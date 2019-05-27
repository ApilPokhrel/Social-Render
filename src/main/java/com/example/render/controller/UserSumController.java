package com.example.render.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.render.entity.user.UserSummed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.render.dao.user.UserAccountRepository;
import com.example.render.dao.user.UserSumRepository;
import com.example.render.entity.user.Schema;
import com.example.render.token.CheckAuthImpl;

@Controller
@RequestMapping("/user")
public class UserSumController {

	@Autowired
	private CheckAuthImpl auth;
	private UserSumRepository userSumRepo;
	private UserAccountRepository User;
	
	public UserSumController(CheckAuthImpl auth, UserSumRepository userSumRepo, UserAccountRepository User) {
		this.auth = auth;
		this.userSumRepo = userSumRepo;
		this.User = User;
	}
	
	
	@RequestMapping(value = {"/suggestion", "/sug", "/getThat"}, method = RequestMethod.GET)
	public String userSuggest(Model model, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		auth.Authentication();


		List<Schema> users = null;
		Schema me = null;

		try {

			users = User.findAll();

			me = (Schema) req.getAttribute("user");


		} catch (Exception ex) {
			res.sendRedirect("/home");
		}


		final Object id = me.getId();

		HashSet<Schema> userSuggestion = new HashSet<Schema>();
		HashSet<Integer> index = new HashSet<Integer>();

		//Remove me from list
		List<Schema> updated = users.stream()
				.filter(user -> !user.getId().equals(id))
				.collect(Collectors.toList());
		ArrayList<Schema> updated2 = new ArrayList<Schema>();


		for (Schema sc : updated) {
			updated2.add(sc);
		}

		UserSummed userSummed = null;

		try {
			userSummed = userSumRepo.getUserSums(me.getId());
		} catch (Exception ex) {
		}
		if (userSummed.getSummed() != null){
			for (int i = 0; i < updated.size(); i++) {
				for (int k = 0; k < userSummed.getSummed().size(); k++) {
					if (userSummed.getSummed().get(k).getUserId().equals(updated.get(i).getId())) {

						index.add(i);


					}
				}
			}
		Object[] arrIndex = index.toArray();
		for (int i = 0; i < arrIndex.length; i++) {

			int la = (int) arrIndex[(Integer) i];
			System.out.println("....." + updated.get(la).getName() + "...." + la);

			Schema usr = updated.get(la);
			updated2.remove(usr);


		}


		updated.remove(0);


			model.addAttribute("usersug", updated2);

	} else {
			model.addAttribute("usersug", updated);
		}

		model.addAttribute("userlist", me);
		return "usersuggestion";
	}
	
	
	
	@PostMapping("/suggluck")
	public String userSuggestPost() throws ServletException, IOException {
		auth.Authentication();
		return null;
	}
	
	
	
}
