package com.example.render.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.render.dao.folio.FolioRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.dao.user.UserProgressRepository;
import com.example.render.entity.folio.FolioSchema;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.filehandeling.MainTagFileHandler;
import com.example.render.filehandeling.SubTagFileHandler;
import com.example.render.token.CheckAuthImpl;

@Controller
public class UserFolioController {

	
	private FolioRepository folioRepo;
    private UserAccountRepository jrepo;
    private CheckAuthImpl auth;
	private UserProgressRepository userProgressRepo;
    private SubTagFileHandler stf;
    private MainTagFileHandler mtf;


    
	public UserFolioController(MainTagFileHandler mtf, SubTagFileHandler stf, FolioRepository folioRepo, UserAccountRepository jrepo, CheckAuthImpl auth, UserProgressRepository userProgressRepo) {
		this.folioRepo = folioRepo;
		this.jrepo = jrepo;
		this.auth = auth;
		this.userProgressRepo = userProgressRepo;
		this.stf = stf;
		this.mtf = mtf;
	}
	
	//Get user info Safely
	public Schema getUser(HttpServletRequest req) {
		Schema user = null;
        try {
        user = (Schema) req.getAttribute("user");
        }catch(NullPointerException ex) {}
        
		return user;
	}
	
	
	//Folio Create Page GET
	@RequestMapping(value= {"/make/folio","/create/page", "/createfolio"}, method=RequestMethod.GET)
	public String createPage(Model model) throws ServletException, IOException {
		
		auth.Authentication();
		model.addAttribute("folio", new FolioSchema());
		model.addAttribute("cannot", null);

		return "folioCreate";
	}
	
	
	//Folio Create Post
	@RequestMapping(value= {"/createfolio"}, method=RequestMethod.POST)
	public String createPagePost(@Valid @ModelAttribute("folio") FolioSchema folioSchema,Model model,  BindingResult bindingResult, HttpServletRequest req) throws ServletException, IOException {
		
		auth.Authentication();
		
		UserAccountProgress uprog = userProgressRepo.findByRefId(getUser(req).getId());
		if(bindingResult.hasErrors()) {
			return "folioCreate";
		}
		
	FolioSchema fs = null;
	try {
		fs = folioRepo.findbyName(folioSchema.getFolioname().toLowerCase());
	}catch(NullPointerException ex) {}
		
		if(uprog.getStage() > 1) {
		if(fs == null){
     folioSchema.setRefuserId(getUser(req).getId());	
     folioSchema.setOwner(getUser(req).getEmail());
     folioSchema.setFolioname(folioSchema.getFolioname().toLowerCase());
     folioSchema.setFolioimg("folio.png");
     folioSchema.setCreatedAt(new Date());
      folioRepo.save(folioSchema);
		}
		else {
			model.addAttribute("cannot", "Name already taken");
		}
		}
		
		else {
			model.addAttribute("cannot", "to create folio must be in stage 2 or greater");
		}
		
		return "redirect:/allFolios";
	}
	
	
	
	//My Folio
	@RequestMapping(value= {"/myfolio", "/myFolio"}, method=RequestMethod.GET)
	public String myFolioPage(HttpServletRequest req, Model model) throws ServletException, IOException, ClassNotFoundException {
		auth.Authentication();
		
//		mtf.writeMainTagFile();
		stf.writeSubTagFiles();
		
		FolioSchema fs = null;
		try {
			System.out.println("folio user has id "+ getUser(req).getId());
			fs = folioRepo.findByRefId(getUser(req).getId());
		}catch(NullPointerException ex) {}
		model.addAttribute("myfolio", fs);
		System.out.println("folio has "+ fs);
		if(fs != null) {
		model.addAttribute("official", fs.isOfficial());
		}

		return "MyFolio";
	}




	//allFolio display

	@RequestMapping(value="/allFolios", method = RequestMethod.GET)
	public String allFolios(Model model, HttpServletRequest req) throws ServletException, IOException {
		auth.Authentication();

		List<FolioSchema> allFolio = null;
		try{
			allFolio = folioRepo.userAllFolios(getUser(req).getId());
		}catch (Exception err){}

      if(allFolio != null) {

			model.addAttribute("folios", allFolio);
		    model.addAttribute("userlist", getUser(req));
		    return "AllFolios";

	} else{ return "redirect:/createfolio";}
	}



	@RequestMapping("myFolio/{id}")
	public String getFolio(@PathVariable("id") String folioId, Model model, HttpServletRequest req) throws ServletException, IOException {
		auth.Authentication();
		FolioSchema folioSchema = null;
		try{
			folioSchema = folioRepo.findOneById(folioId);
		}catch(Exception ex){}
		model.addAttribute("myfolio", folioSchema);
		model.addAttribute("official", folioSchema.isOfficial());
		model.addAttribute("userlist", getUser(req));

		return "MyFolio";
	}
}
