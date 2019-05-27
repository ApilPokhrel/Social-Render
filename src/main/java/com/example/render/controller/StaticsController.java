package com.example.render.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.render.dao.CustomMongoRepo;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.entity.opinion;
import com.example.render.entity.statics.StaticsFile;
import com.example.render.entity.statics.StaticsSchema;
import com.example.render.entity.statics.StaticsTag;
import com.example.render.token.CheckAuthImpl;
import com.example.render.uploads.AWSStaticFileUpload;
import com.example.render.uploads.StaticsFileUpload;

@Controller
@RequestMapping("/tag")
public class StaticsController {

	
	
	private HttpSession httpSession;
	private StaticsRepository sRepo;
	private CheckAuthImpl auth;
	private StaticsFileUpload sfu;
	private AWSStaticFileUpload awsStatic;
	private CustomMongoRepo customMongoRepo;
	
	@Autowired
	public StaticsController(AWSStaticFileUpload awsStatic, CustomMongoRepo customMongoRepo, StaticsFileUpload sfu, HttpSession httpSession, StaticsRepository sRepo, CheckAuthImpl auth) {
		this.httpSession = httpSession;
		this.sRepo = sRepo;
		this.auth = auth;
		this.sfu = sfu;
		this.awsStatic = awsStatic;
		this.customMongoRepo = customMongoRepo;
	}
	

	
	@RequestMapping(value="/statics", method=RequestMethod.GET)
	public String staticsPage(Model model, HttpServletRequest req) throws ServletException, IOException {
		auth.Authentication();
		model.addAttribute("schema", new StaticsSchema());
		model.addAttribute("userlist", req.getAttribute("user"));
		model.addAttribute("opinions", new opinion());
		model.addAttribute("cannot");
			return "statics";
		
		
		
	}
	
	@RequestMapping(value="/statics",method=RequestMethod.POST)
	public String staticsPost(@Valid @ModelAttribute("schema")StaticsSchema ss,BindingResult bindingResult , @Valid @ModelAttribute("opinions") opinion sc3,BindingResult bindingResult2 
			,HttpServletRequest req, @RequestParam("@file1_1[]") MultipartFile[] filei, @RequestParam("@file2_2[]") MultipartFile filev, Model model) throws ServletException, IOException {
	
			
			auth.Authentication();
			
			if(bindingResult.hasErrors()) {
				System.out.println("error in statics occured");
			return "statics";
			}
			
			
			if(bindingResult2.hasErrors()) {
				System.out.println("error in statics occured");
			return "statics";
			}
			
			
			String email = null;
			Schema user = null;
			try {
				email = req.getAttribute("email").toString();
				user = (Schema)req.getAttribute("user");
			}catch(Exception ex) {}
			
			UserAccountProgress userProgress =  customMongoRepo.findByRefId(user.getId());
			
			if(userProgress != null) {
		    
				if(userProgress.isOfficial() == true || userProgress.getStage() > 0) {


					ArrayList<String> flike = new ArrayList<String>();

					ss.setRefId(user.getId());
			ss.setRefSlug(user.getSlug());
			ss.setIs("static");
			ss.setCreatedAt(new Date());
			ss.setFilelikes(flike);
			
			ArrayList<String> f1 = new ArrayList<String>();
			
			StaticsTag sc2a = new StaticsTag();
			sc2a.setTagvalue(sc3.getA());
			sc2a.setF1(f1);
			
			
			StaticsTag sc2b = new StaticsTag();
		    sc2b.setTagvalue(sc3.getB());
		    sc2b.setF1(f1);
		  
		
		    StaticsTag sc2c = new StaticsTag();
		    sc2c.setTagvalue(sc3.getC());
		    sc2c.setF1(f1);
		  
		
		    StaticsTag sc2d = new StaticsTag();
		    sc2d.setTagvalue(sc3.getD());
		    sc2d.setF1(f1);
		    
		
		    StaticsTag sc2e = new StaticsTag();
		    sc2e.setTagvalue(sc3.getE());
		    sc2e.setF1(f1);
			
		    
		    ArrayList<Object> ar = new ArrayList<Object>();
			ar.add(sc2a);
			ar.add(sc2b);
			ar.add(sc2c);
			ar.add(sc2d);
			ar.add(sc2e);
			
			
			ss.setOpinions(ar);
			
//			String owntag ="*"+ss.getOwntag().trim();
//			ss.setOwntag(owntag);
			
			
			ArrayList<StaticsFile> filenames = null;
			if(filei[0].isEmpty() && filev.isEmpty()) {
				model.addAttribute("cannot", "upload at least video or image");

				return "statics";
			}
			else if(!filei[0].isEmpty()) {
				filenames = awsStatic.multipleUploads(filei);
				System.out.println("file are "+filei.length);
			}
			else {
				filenames = awsStatic.uploadFile2(filev);
				System.out.printf("filenames2", filev.hashCode());

			}
			
			if(filenames == null) {
				model.addAttribute("cannot", "Post not Submitted!");
				return "statics";
			}
			
			ss.setFile(filenames);
			
			
			sRepo.save(ss);
			System.out.println("saved");
			model.addAttribute("cannot", "Post Submitted!");
		} else {
			model.addAttribute("cannot", "To submit Post Must be on stage 1 or Above!");
		}
			
			}
			
			
			return "statics";
		
		
		
			
	
	}
}
