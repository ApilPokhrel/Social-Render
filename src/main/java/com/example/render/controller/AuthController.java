package com.example.render.controller;


import com.example.render.dao.CustomMongoRepo;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.dao.user.UserProgressRepository;
import com.example.render.dao.user.UserRepository;
import com.example.render.dao.user.UserSumRepository;
import com.example.render.entity.user.Schema;
import com.example.render.entity.Tokens;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.entity.user.UserSchema;
import com.example.render.entity.user.UserSummed;
import com.example.render.gateway.ApiGateway;
import com.example.render.entity.geo.GeoSchema;
import com.example.render.notification.EmailService;
import com.example.render.token.CheckAuthImpl;
import com.example.render.token.JwtTokenGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;


@Controller
public class AuthController {



    
    private UserAccountRepository jrepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private HttpSession httpSession;
    private EmailService emailService;
    private JwtTokenGen tokengen;
    private CheckAuthImpl auth;
    private CustomMongoRepo customMongoRepo;
    private UserProgressRepository uProgress;
    private UserSumRepository usum;
    private UserRepository userRepository;
    private ApiGateway gateway;
   Date date = new Date();

   @Autowired
    public AuthController(ApiGateway gateway, UserSumRepository usum, UserProgressRepository uProgress, CustomMongoRepo customMongoRepo, CheckAuthImpl auth, JwtTokenGen tokengen, UserRepository userRepository, UserAccountRepository jrepo, BCryptPasswordEncoder bCryptPasswordEncoder, HttpSession httpSession, EmailService emailService){
        this.jrepo = jrepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.httpSession = httpSession;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.tokengen = tokengen;
        this.auth = auth;
        this.customMongoRepo = customMongoRepo;
        this.uProgress = uProgress;
        this.usum = usum;
        this.gateway = gateway;

    }
    /**
     *
     */
    String code = null;



	public String getToken(){
        String token = null;
        try {
            token = auth.getToken();
        }catch(Exception ex){

        }

        return token;
    }


    public Schema checkToken(){
        Schema sc = null;
        try {
            sc = customMongoRepo.findByToken(getToken());
        }catch(Exception ex) {}
        return sc;
    }


    public String validate(String redirectTo){
	    if(getToken() != null){
           if(checkToken() != null){
               if(checkToken().isChecked() == true){
                   return "redirect:/";
               } else{
                   return "redirect:/verification";
               }
           }
        }
        return redirectTo;
    }


    //login

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
       return validate("login");

    }





    //postlogin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postlogin(@RequestParam("email") String email, @RequestParam("password") String password,Model model,RedirectAttributes redirectAttributes, HttpServletResponse res) throws URISyntaxException, IOException {
      
       String token = getToken();
    if(token != null) {
    	Schema sc = checkToken();
        if(sc!=null) {
        if(sc.isChecked() == true){
            return "redirect:/";
        }
        else{
           return "redirect:/verification";
        }
        }
    }
   

    	Schema  sch =  this.jrepo.findByEmail(email);
        redirectAttributes.addFlashAttribute("pass", password);
        redirectAttributes.addFlashAttribute("email", email);
        
        if (sch != null) {
            if (bCryptPasswordEncoder.matches(password, sch.getPassword())) {
                try {
            	Tokens tokens = tokengen.generateAuthToken(email);
                Cookie cookie = new Cookie("hijab", tokens.getToken());
                cookie.setHttpOnly(true);
                cookie.setMaxAge(60*60*60*3);
                res.addCookie(cookie);
                }catch(Exception ex) {}
                if(sch.isChecked()==true) {
                	 //gate ways for sending cookie to node server

                    try {
                        gateway.sendCookie(res);
                    } catch(Exception ex){}
         	       
                    return "redirect:/";
                }
                else{
                  code =  new BigInteger(15, new SecureRandom()).toString(5);
                    try{
                        emailService.sendEmail(sch.getEmail(), code);
                    }catch(MailException em){

                        redirectAttributes.addFlashAttribute("gamil","your gmail is invalid enter a valid gmail");
                        return "redirect:/login";
                    }
                    httpSession.setAttribute("vcode", code);
                    return "redirect:/verification";
                }
            } else {

                redirectAttributes.addFlashAttribute("passfail", "wrong password");
                return "redirect:/login";
            }
        } else {

            redirectAttributes.addFlashAttribute("emailfail", "invalid email");
            return "redirect:/login";
        }


    



    }



//register
    @RequestMapping(value = "/register", method= RequestMethod.GET)

    public String register(Model model, RedirectAttributes redirectAttributes){
    model.addAttribute("schema", new Schema());

        return validate("register");
    }






    //post register
    @RequestMapping(value = "/register", method= RequestMethod.POST)

    public String postRegister(@Valid @ModelAttribute("schema") Schema sc,BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,  HttpServletRequest request, HttpServletResponse res){

        String token = getToken();

    if(token != null) {
    	Schema scc = checkToken();
        if(scc!=null) {
        	
        if(scc.isChecked() == true){
            return "redirect:/";
        }
        else{
            return "verification";
        }
    }
        }
        Schema schr = jrepo.findByEmail(sc.getEmail());       
        
        redirectAttributes.addAttribute("sc", sc);
        if(bindingResult.hasErrors()) {
        	
    		return "register";
    	}

        if(schr != null){
            redirectAttributes.addFlashAttribute("emailalready","email already blongs to other user try other one");

          return "redirect:/register";
        }
        else {
        	sc.setSlug(UUID.randomUUID().toString());
            sc.setPassword(bCryptPasswordEncoder.encode(sc.getPassword()));
            sc.setImage("images.png");
            sc.setCreateddate(date.toString());
            sc.setChecked(false);
            
            jrepo.save(sc);
            
            Tokens tokens = tokengen.generateAuthToken(sc.getEmail());
            Cookie cookie = new Cookie("hijab", tokens.getToken());
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*60*3);
            
            res.addCookie(cookie);
            
            code =  new BigInteger(15, new SecureRandom()).toString(5);
            try{
                emailService.sendEmail(sc.getEmail(), code);
            }catch(MailException em){
            }
            httpSession.setAttribute("vcode", code);
            
            return "redirect:/verification";
            
        }
 

    }


//verification

@RequestMapping(value = "/verification", method = RequestMethod.GET)
public String verification(Model model){
        String token = getToken();

    if(token != null) {
    	Schema sc = checkToken();
        if(sc!=null) {
        	
        if(sc.isChecked() == true){
            return "redirect:/";
        }
        else{

            model.addAttribute("user", sc);
            return "verification";
        }
    }
    }
    
        return "redirect:/login";


    
}


//verification Post
@RequestMapping(value = "/verification", method = RequestMethod.POST)
public String postVerification(@RequestParam("vcode") String vcode, RedirectAttributes redirectAttributes,HttpServletResponse res) throws URISyntaxException, IOException {

	String token = getToken();
if(token != null) {
	Schema sc = checkToken();
    if(sc!=null) {
    if(sc.isChecked() == true){
        return "redirect:/";
    }
    else{
    	
    	
    	String code2 =null;
    	try {
    	code2 = httpSession.getAttribute("vcode").toString();
    	}catch(Exception ex) {}
    	
    	if(code2 == null) {
    		 return "redirect:/verification";
    	}
    	else {
    	if(code2.equals(vcode)){
       
    		
    	   sc.setChecked(true);
   	       jrepo.save(sc);
   	       
   	       
    		  GeoSchema geo = null;
    		
    		try {
        RestTemplate restTemplate = new RestTemplate();
         geo = restTemplate.getForObject("http://ip-api.com/json", GeoSchema.class);
      
    		} catch(Exception ex) {}
    		
    		 UserSchema userSchema =new UserSchema();
    		 
    		 UserAccountProgress userProgress = new UserAccountProgress();
    		 
    		 UserSummed sum = new UserSummed();
    		 
    		 
    		 userProgress.setRefId(sc.getId());
    		 userProgress.setOfficial(false);
             userProgress.setSocialworker(false);
             userProgress.setSuperior(false);
             userProgress.setIsfunded(false);
             userProgress.setIsworker(false);
             userProgress.setStage(0);
             
             userProgress.setSpentTimeSummedHrs("0");
             
             
             
             sum.setRefId(sc.getId());
             
             System.out.println("geo is "+geo);
             
    		if(geo != null && geo.getStatus().equalsIgnoreCase("success")) {
    		
    		
    		    System.out.println("contry is "+geo.getCountry());
    	        System.out.println("lat is "+geo.getLat());
    	        System.out.println("lon is "+geo.getLon());
    	        
    	       userSchema.setLat(geo.getLat());
     	       userSchema.setLng(geo.getLon());
     	       userSchema.setCountry(geo.getCountry());
     	       userSchema.setCity(geo.getCity());
     	       userSchema.setZip(geo.getZip());
    	     
    	       
    		}
    	       
    	      
    	       userSchema.setRefId(sc.getId());
    	       
    	       uProgress.save(userProgress);
    	       usum.save(sum);
    	       userRepository.save(userSchema);
    	       
    	       
    	       //gate ways for sending cookie to node server
//    	       gateway.sendCookie(res);
    	       
    	       return "redirect:/";

    	}
    	else{
    	    redirectAttributes.addFlashAttribute("msg", "The verification code you entered didn't matched ! Try Again");
    	    return "redirect:/verification";
    	}
    	}
    }
    }
}

    return "redirect:/login";



}



//delete Account in verification
    @RequestMapping(value = "/deleteAccount/{id}")
    public String deleteAccount(@PathVariable String id){
        String token = getToken();

        if(token != null) {
            Schema sc = checkToken();
            if(sc!=null) {

                if(sc.isChecked() == true){
                    return "redirect:/";
                }
                else{
                     customMongoRepo.findByIdAndRemove(id.trim());
                     return "redirect:/register";
                }
            }
        }

        return "redirect:/login";


    }





//verification sendcode again
    @RequestMapping(value = "/verification/{text}", method = RequestMethod.GET)
    public String sendCode(@PathVariable("text") String text, RedirectAttributes redirectAttributes){

    	String token = getToken();

    if(token != null) {
    	Schema sc = checkToken();
        if(sc!=null) {
        if(sc.isChecked() == true){
            return "redirect:/";
        }
        else{
        	 if (text.equals("send")) {
        	        code =  new BigInteger(15, new SecureRandom()).toString(5);
        	        try{
        	            emailService.sendEmail(sc.getEmail(), code);
                        System.out.println("code is "+ code);
        	        }catch(MailException em){


        	            redirectAttributes.addFlashAttribute("gmail","your gmail is invalid enter a valid gmail");
        	           
        	        }
        	        httpSession.setAttribute("vcode", code);
        	        return "redirect:/verification";
        	    }else {
        	    	return "redirect:/verification";
        	    }
        }
        
        }
    }
    
        return "redirect:/login";


    
   

    }






    //logout

    @RequestMapping(value = "/logoutt", method = RequestMethod.GET)
    public String logout(HttpServletRequest req, HttpServletResponse res){
    	 String token = getToken();

     if(token != null) {
    	 Schema sc = null;
         try {
         sc = customMongoRepo.findByToken(token);
         }catch(Exception ex) {}
         if(sc!=null) {
         if(sc.isChecked() == true){
             customMongoRepo.removeToken(sc.getEmail(), token);
             try {
         		Cookie[] cookies = req.getCookies();
         		for(Cookie cookie: cookies) {
         			if(cookie.getName().equals("hijab")) {
         				cookie.setMaxAge(0);
         				res.addCookie(cookie);
         			}
         		}
         		}catch(Exception ex) {}
             return "redirect:/login";
         }
         else{
             return "verification";
         }
     }
     }
  
         return "redirect:/login";


  
    }
}
