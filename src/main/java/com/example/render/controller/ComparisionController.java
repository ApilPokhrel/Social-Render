package com.example.render.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.CustomMongoRepo;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.entity.opinion;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.comparision.ComparisionTags;
import com.example.render.token.CheckAuthImpl;

import com.example.render.uploads.ComparisionFileUploadInterface;

@Controller
@RequestMapping("/tag")
public class ComparisionController {

private ComparisionRepository cRepo;
private HttpSession httpSession;
private ComparisionFileUploadInterface comparisionFileUploadInterface;
private CheckAuthImpl auth;
private CustomMongoRepo customMongoRepo;

public ComparisionController(CustomMongoRepo customMongoRepo, CheckAuthImpl auth, ComparisionRepository cRepo, ComparisionFileUploadInterface comparisionFileUploadInterface, HttpSession httpSession) {
this.cRepo = cRepo;
this.httpSession = httpSession;
this.auth = auth;
this.customMongoRepo = customMongoRepo;
this.comparisionFileUploadInterface = comparisionFileUploadInterface;
}


@RequestMapping(value="/comparision/v1", method=RequestMethod.GET)
public String comparisionPage(Model model, HttpServletRequest req) throws ServletException, IOException{
auth.Authentication();
  model.addAttribute("schema", new ComparisionSchema());
	model.addAttribute("userlist", req.getAttribute("user"));
  model.addAttribute("cannot");
	model.addAttribute("opinions", new opinion());

	return "comparision";
	
	
	
	
}

@RequestMapping(value="/comparision/v1", method=RequestMethod.POST)
public String comparisionPost(@Valid @ModelAttribute("schema") ComparisionSchema cs , BindingResult bindingResult,@Valid @ModelAttribute("opinions") opinion sc3,BindingResult bindingResult3,
		@RequestParam("@file1_1") MultipartFile file1, @RequestParam("@file2_2") MultipartFile file2,Model model 
		,HttpServletRequest req) throws ServletException, IOException, InterruptedException, ExecutionException{
	

		auth.Authentication();

	
		
	String email = null;
	Schema user = null;
	try {
		email = req.getAttribute("email").toString();
		user = (Schema)req.getAttribute("user");
	}catch(Exception ex) {}
	
	System.out.println("email is "+ email);
	
	UserAccountProgress userProgress =  customMongoRepo.findByRefId(user.getId());
	
	System.out.println("here is official "+userProgress.getId() );
	if(userProgress != null) {
    
		if(userProgress.isOfficial() == true || userProgress.getStage() > 0) {
			
		 if(bindingResult.hasErrors()) {
		     	
		 		return "index";
		 	}
		 
		 if(bindingResult3.hasErrors()) {
		     	
		 		return "index";
		 	}

		 	ArrayList<String> f1like = new ArrayList<String>();
			ArrayList<String> f2like = new ArrayList<String>();



			cs.setRefId(user.getId());
		    cs.setRefSlug(user.getSlug());
		    cs.setCreatedAt(new Date());
		    cs.setIs("comparision");
		    cs.setF1likes(f1like);
		    cs.setF2likes(f2like);

		ArrayList<String> f1 = new ArrayList<String>();
		ArrayList<String> f2 = new ArrayList<String>();
		
		
		
	ComparisionTags sc2a = new ComparisionTags();
		sc2a.setTagvalue(sc3.getA());
		sc2a.setF1(f1);
		sc2a.setF2(f2);
		
	ComparisionTags sc2b = new ComparisionTags();
	    sc2b.setTagvalue(sc3.getB());
	    sc2b.setF1(f1);
	    sc2b.setF2(f2);
	
	ComparisionTags sc2c = new ComparisionTags();
	    sc2c.setTagvalue(sc3.getC());
	    sc2c.setF1(f1);
	    sc2c.setF2(f2);
	
	ComparisionTags sc2d = new ComparisionTags();
	    sc2d.setTagvalue(sc3.getD());
	    sc2d.setF1(f1);
	    sc2d.setF2(f2);
	
	ComparisionTags sc2e = new ComparisionTags();
	    sc2e.setTagvalue(sc3.getE());
	    sc2e.setF1(f1);
		sc2e.setF2(f2);
		
		
		ArrayList<Object> ar = new ArrayList<Object>();
		ar.add(sc2a);
		ar.add(sc2b);
		ar.add(sc2c);
		ar.add(sc2d);
		ar.add(sc2e);
		
		
		cs.setOpinions(ar);
		
//		String owntag ="*"+cs.getOwntag().trim();
//		cs.setOwntag(owntag);

		if(!file1.isEmpty() && !file2.isEmpty()) {

		    
			CompletableFuture<String[]> firstfile = comparisionFileUploadInterface.uploadFile1(file1);
			
			CompletableFuture<String[]> secondfile = comparisionFileUploadInterface.uploadFile2(file2);
			
			
//			CompletableFuture.allOf(firstfile, secondfile).join();
			cs.setFile1(firstfile.get()[0]);
			cs.setFile1type(firstfile.get()[1]);
			
			
			cs.setFile2(secondfile.get()[0]);
			cs.setFile2type(secondfile.get()[1]);
			
			
		}
		
		else if(file1.isEmpty() || file2.isEmpty()) {
			model.addAttribute("cannot", "please upload image or video!");

			return "index";
		}
		
		
		cRepo.save(cs);
		model.addAttribute("cannot", "Post submitted!");
	}
		else {
			model.addAttribute("cannot", "To submit Post Must be on stage 1 or Above!");
		}
	}
	
	
	return "index";
	
	
	
}










//compare-detail page

	@RequestMapping("/compare-detail")
	public String compareDetail(HttpServletRequest req) throws ServletException, IOException {
	auth.Authentication();

	return "detail/compare-detail";

	}
}
