package com.example.render.controller;

import com.example.render.dao.CustomMongoRepo;
import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.dao.user.UserProgressRepository;
import com.example.render.dao.user.UserRepository;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.entity.user.UserSchema;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.statics.StaticsSchema;
import com.example.render.notification.EmailService;
import com.example.render.token.CheckAuthImpl;
import com.example.render.uploads.AmazonFileUpload;
import com.example.render.uploads.UserFileUpload;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class UserController {


    
    private UserRepository userRepository;
    private UserAccountRepository jrepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private HttpSession httpSession;
    private EmailService emailService;
    private CheckAuthImpl auth;
    private CustomMongoRepo customMongoRepo;
    private UserFileUpload fileupload;
    private ComparisionRepository comrepo;
    private StaticsRepository statrepo;
    private UserProgressRepository userProgress;
    private AmazonFileUpload afu;
    Date date = new Date();
    
    @Autowired
    public UserController(AmazonFileUpload afu, UserProgressRepository userProgress, ComparisionRepository comrepo, StaticsRepository statrepo, UserFileUpload fileupload, CustomMongoRepo customMongoRepo, CheckAuthImpl auth, UserAccountRepository jrepo, BCryptPasswordEncoder bCryptPasswordEncoder, HttpSession httpSession, EmailService emailService, UserRepository userRepository) {
        this.jrepo = jrepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.httpSession = httpSession;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.auth = auth;
        this.customMongoRepo = customMongoRepo;
        this.fileupload = fileupload;
        this.comrepo = comrepo;
        this.statrepo = statrepo;
        this.userProgress = userProgress;
        this.afu = afu;
        
    }



   
    public String getemail() {
        String email = null;
        try {
            email = httpSession.getAttribute("email").toString();
        } catch (Exception ex) {
        }
        return email;
    }




    String code = null;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model, HttpServletRequest req) throws ServletException, IOException, InterruptedException, ExecutionException {
           
    	auth.Authentication();
    	
    	String email = null;
    	Schema user = null;
    	try {
    		email = req.getAttribute("email").toString();
    		user = (Schema) req.getAttribute("user");
    	}catch(Exception ex) {}
    	
        model.addAttribute("userschema", new UserSchema());
       
            Schema sc = jrepo.findByEmail(email);
            model.addAttribute("userlist", sc);
            
            
            //get All Comparisons
            
          List<ComparisionSchema> clist = null;
            
            try {
            	
            	clist = comrepo.findAllUserComparision(user.getId());
            	
            } catch(Exception ex) {}
            
            
          
            
            
            
            //get All statics

           List<StaticsSchema> slist = null;
            
            try {
            	
            	slist = statrepo.findAllUserStatics(user.getId());
            	
            } catch(Exception ex) {}
            
            
            
        	
           
            //UserProgress list
           UserAccountProgress prog = null;
            try {
            	prog = userProgress.findByRefId(user.getId());
            }catch(NullPointerException ex) {}
           
            
            //UserInfo list
           UserSchema us = new UserSchema();
            try {
                us =  userRepository.findByRefId(user.getId());
            }catch(NullPointerException ex) {
            }
            
            
            
            //Async initialize
//            CompletableFuture.allOf(clist).join();
//            slist.join();
//            prog.join();
            
            //model Attributes
                model.addAttribute("comparisionlist", clist);

            	model.addAttribute("staticslist", slist);
            	
            	model.addAttribute("userprogress", prog);
            	 
                model.addAttribute("userinfolist", us);
                
                

            return "user";

       
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String postuser(@ModelAttribute("userschema") UserSchema userSchema, @RequestParam("act") String act, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,  HttpServletRequest req) throws IOException, ServletException {
        
       	auth.Authentication();
       	
       	String email = null;
       	Schema user = null;
       	try {
       		email = req.getAttribute("email").toString();
       		user = (Schema) req.getAttribute("user");
       	}catch(Exception ex) {}
    	
        if (act.equals("imgpost")) {
        	String filename = null;
            if (file != null) {
//                 filename = fileupload.fileUpload(file);
                 filename = this.afu.fileUpload(file);
                
                 System.out.println("file name is "+filename);
               
                  customMongoRepo.updateUserImg(filename, user.getId());
                    
                  this.afu.deleteFileFromS3Bucket(user.getImage());
                 return "redirect:/user";
            } else {
                redirectAttributes.addFlashAttribute("fileempty", "file is empty try adding another one");
                return "redirect:/user";
            }

        } else {
        	return "redirect:/user";
        }

    }


    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String upadte_user(@ModelAttribute("userschema") UserSchema userSchema, HttpServletRequest req) throws ServletException, IOException {

     auth.Authentication();
       	
       	String email = null;
       	Schema user = null;
       	try {
       		email = req.getAttribute("email").toString();
       		user = (Schema) req.getAttribute("user");
       	}catch(Exception ex) {}
    	
       	
        UserSchema usc = userRepository.findByRefId(user.getId());
        usc.setStudied(userSchema.getStudied());
        usc.setLives(userSchema.getLives());
        usc.setFond(userSchema.getFond());
        usc.setWorksAt(userSchema.getWorksAt());
        usc.setDreamprofession(userSchema.getDreamprofession());
        usc.setRelation(userSchema.getRelation());
        usc.setVisit(userSchema.getVisit());
        usc.setEfield(userSchema.getEfield());
        userRepository.save(usc);
        return "redirect:/user";
    }
    
    

//ForgetAccount
    @RequestMapping(value = "/forgetAccount", method = RequestMethod.GET)
    public String forgetAccount(){
    	
    String token = 	auth.getToken();
    if(token != null) {
    	 Schema sc = null;
         try {
         sc = customMongoRepo.findByToken(token);
         }catch(Exception ex) {}
         if(sc!=null) {
         if(sc.isChecked() == true){
             return "redirect:/";
         }
         
         else{
             return "redirect:/verification";
         }
         }
         
         return "/login";
    }else {
    	return "forgetAccount";
    }
    }

    @RequestMapping(value = "/forgetAccount", method = RequestMethod.POST)
    public String forgetAccountPost(@RequestParam("email") String email){
    	
        String token = 	auth.getToken();
        if(token != null) {
        	 Schema sc = null;
             try {
             sc = customMongoRepo.findByToken(token);
             }catch(Exception ex) {}
             if(sc!=null) {
             if(sc.isChecked() == true){
                 return "redirect:/";
             }
             
             else{
                 return "redirect:/verification";
             }
             }
             
             return "/login";
        }
        else {
        httpSession.setAttribute("forgetPassEmail", email);
        return "redirect:/forgetPass";
        }
    }

    
    
    
    
    
    
    
    //Forget Pass
    @RequestMapping(value = "/forgetPass", method = RequestMethod.GET)
    public String passreset(RedirectAttributes redirectAttributes) {
    	
    	  String token = 	auth.getToken();
          if(token != null) {
          	 Schema sc = null;
               try {
               sc = customMongoRepo.findByToken(token);
               }catch(Exception ex) {}
               if(sc!=null) {
               if(sc.isChecked() == true){
                   return "redirect:/";
               }
               
               else{
                   return "redirect:/verification";
               }
               }
               
               return "/login";
          }
          else {
        String femail = null;


        try {
            femail = httpSession.getAttribute("forgetPassEmail").toString();
        } catch (Exception ea) {
        }
        Schema sc = jrepo.findByEmail(femail);
        if (sc != null) {
          code = new BigInteger(15, new SecureRandom()).toString(5);
            try {
                emailService.sendEmail(sc.getEmail(), code);
            } catch (MailException em) {

                redirectAttributes.addFlashAttribute("gmail", "your gmail is invalid enter a valid gmail");
                return "redirect:/login";
            }
            httpSession.setAttribute("fcode", code);
            return "forgetPass";
        } else {
            redirectAttributes.addFlashAttribute("emailwrong", "email is invalid");
            return "redirect:/forgetAccount";
        }
          }

    }



    @RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
    public String Passresetpost(@RequestParam("fcode") String vcode, RedirectAttributes redirectAttributes) {

    	  String token = 	auth.getToken();
          if(token != null) {
          	 Schema sc = null;
               try {
               sc = customMongoRepo.findByToken(token);
               }catch(Exception ex) {}
               if(sc!=null) {
               if(sc.isChecked() == true){
                   return "redirect:/";
               }
               
               else{
                   return "redirect:/verification";
               }
               }
               
               return "/login";
          }
          else {
                String fcode = null;
                String femail = null;
                try {
                    fcode = httpSession.getAttribute("fcode").toString();
                    femail = httpSession.getAttribute("forgetPassEmail").toString();
                } catch (Exception ex) {
                }
                if (fcode.equals(vcode)) {
                    httpSession.setAttribute("email", femail);
                    httpSession.setAttribute("setPassAuth", "enter_set_pass_route");
                    return "redirect:/setPass";
                }
                else {
                    return "redirect:/forgetPass";
                }
          }
            }



    @RequestMapping(value = "/forgetPass1/{text}", method = RequestMethod.GET)
    public String forgetPassSendCode(@PathVariable("text") String text, RedirectAttributes redirectAttributes) {
    	
    	
    	
    	  String token = 	auth.getToken();
          if(token != null) {
          	 Schema sc = null;
               try {
               sc = customMongoRepo.findByToken(token);
               }catch(Exception ex) {}
               if(sc!=null) {
               if(sc.isChecked() == true){
                   return "redirect:/";
               }
               
               else{
                   return "redirect:/verification";
               }
               }
               
               return "/login";
          }
          else {
        String femail = null;


        try {
            femail = httpSession.getAttribute("forgetPassEmail").toString();
        } catch (Exception ea) {
        }
        Schema sc = jrepo.findByEmail(femail);
        if (sc != null) {
            if (text.equals("send")) {

                    try {
                        emailService.sendEmail(femail, code);
                        System.out.println("verification code "+ code);
                    } catch (MailException em) {

                        redirectAttributes.addFlashAttribute("gmail", "your gmail is invalid enter a valid gmail");
                        
                    }
                    httpSession.setAttribute("fcode", code);


                return "redirect:/forgetPass";
            } else {

                redirectAttributes.addFlashAttribute("emailnotsent", "problem in sending email");
                return "redirect:/forgetpass";
            }
        }
        else{
            redirectAttributes.addFlashAttribute("emailwrong", "email is invalid");
            return "redirect:/forgetAccount";
        }
          }
    }


    //set Pass Route
    @RequestMapping(value = "/setPass", method = RequestMethod.GET)
    public String setPass(){

        String setPassAuth = null;
        try{
            setPassAuth = httpSession.getAttribute("setPassAuth").toString();
        }catch (Exception exxx){
            return "redirect:/forgetPass";
        }

        if(setPassAuth.equals("enter_set_pass_route")){
            return "setPass";
        }
         else{
            return "redirect:/forgetPass";
        }
    }



    @RequestMapping(value = "/setPass", method = RequestMethod.POST)
    public String setPasspost(@RequestParam("newPass") String newPass, @RequestParam("confirmPass") String confirmPassword, RedirectAttributes redirectAttributes) {
        
    	  String setPassAuth = null;
          try{
              setPassAuth = httpSession.getAttribute("setPassAuth").toString();
          }catch (Exception exxx){
              return "redirect:/forgetPass";
          }

          if(setPassAuth.equals("enter_set_pass_route")){

          	String newPassword = newPass.trim();
              String confirmPass = confirmPassword.trim();
             if(newPassword.equals(confirmPass)){
                 Schema sc =jrepo.findByEmail(getemail());
                 System.out.println(newPassword);
                    sc.setPassword(bCryptPasswordEncoder.encode(newPassword));
                    jrepo.save(sc);
                 return "redirect:/";
             }
             else{
            redirectAttributes.addFlashAttribute("pass'snotequal","new pass and confirm pass didn't matched");
            return "redirect:/setPass";
             }

          }
           else{
              return "redirect:/forgetPass";
          }
    	
    }


//Pass Reset route
    @RequestMapping(value = "/PassReset", method = RequestMethod.GET)
    public String passReset(HttpServletRequest req) throws ServletException, IOException{
    	auth.Authentication();
        return "PassReset";
    }

    @RequestMapping(value = "/PassReset", method = RequestMethod.POST)
    public String passResetPost(@RequestParam("password") String pass, RedirectAttributes redirectAttributes, HttpServletRequest req) throws ServletException, IOException{
       auth.Authentication();
       String email = null;
   	try {
   		email = req.getAttribute("email").toString();
   		
   	}catch(Exception ex) {}
   	System.out.println("password rote says email is "+email);
            if (pass != null) {
                Schema sch = jrepo.findByEmail(email);
                if(bCryptPasswordEncoder.matches(pass, sch.getPassword())){
                    httpSession.setAttribute("setPassAuth","enter_set_pass_route");
                    httpSession.setAttribute("email", email);
                    return "redirect:/setPass";
                }
                else{
                    redirectAttributes.addFlashAttribute("passwrong","wrong password");
                    return "redirect:/PassReset";
                }
            } else {
                redirectAttributes.addFlashAttribute("passwrong", "password is required");
                return "redirect:/PassReset";
            }
       
    }
}
