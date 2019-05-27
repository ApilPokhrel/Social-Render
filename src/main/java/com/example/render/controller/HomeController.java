package com.example.render.controller;


import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.opinion;
import com.example.render.entity.statics.StaticsSchema;
import com.example.render.entity.user.Schema;
import com.example.render.notification.EmailService;
import com.example.render.token.CheckAuthImpl;
import com.example.render.token.CheckAuthInterface;
import com.example.render.token.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@Controller
public class HomeController {



    @Autowired
    private UserAccountRepository jrepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private HttpSession httpSession;
    private EmailService emailService;
    Date date = new Date();
    private VerificationToken verificationToken;
    private ComparisionRepository crepo;
    private CheckAuthImpl auth;
    private StaticsRepository srepo;

    public HomeController(StaticsRepository srepo, CheckAuthImpl auth, ComparisionRepository crepo, UserAccountRepository jrepo, BCryptPasswordEncoder bCryptPasswordEncoder, HttpSession httpSession, EmailService emailService){
        this.jrepo = jrepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.httpSession = httpSession;
        this.emailService = emailService;
        this.crepo = crepo;
        this.auth = auth;
        this.srepo = srepo;

    }
    
   

    @RequestMapping(value = {"/","/home"}, method= RequestMethod.GET)
    @CrossOrigin
    public String home(Model model, HttpServletRequest req) throws ServletException, IOException, InterruptedException, ExecutionException{

                auth.Authentication();
            
           Schema user = null;
           String email = null;
            try {
            email = req.getAttribute("email").toString();
            user = (Schema) req.getAttribute("user");
            }catch(NullPointerException ex) {}
               System.out.println("email is in home "+email);
               
               
               List list = new ArrayList();          
               
               
               List<ComparisionSchema> clist = null;
             
               try {
               	
               	clist = crepo.findAllUserComparision(user.getId());
               } catch(Exception ex) {}


        List<StaticsSchema> slist = null;

        try {

            slist = srepo.findAllUserStatics(user.getId());

        } catch(Exception ex) {}

        if(clist != null && slist != null) {

            clist.forEach(new Consumer<ComparisionSchema>() {

                @Override
                public void accept(ComparisionSchema t) {
                    // TODO Auto-generated method stub
                    Optional<Schema> user2 = jrepo.findById((String) t.getRefId());
                    list.add(user2);

                }
            });


            slist.forEach(new Consumer<StaticsSchema>() {

                @Override
                public void accept(StaticsSchema t) {
                    // TODO Auto-generated method stub
                    Optional<Schema> user2 = jrepo.findById(t.getRefId().toString());
                }
            });

        }

        model.addAttribute("schema", new ComparisionSchema());
        model.addAttribute("userlist", req.getAttribute("user"));
        model.addAttribute("cannot");
        model.addAttribute("opinions", new opinion());
            return "index";
       
    }
    
    
    @RequestMapping(value= "/", method=RequestMethod.POST)
    public String homePost() throws ServletException, IOException {
    	
    	auth.Authentication();
    	
    return null;	
    }
    
    

    @RequestMapping(value = "/error_page1", method= RequestMethod.GET)
    public String errorPage1(Model model){
       
        	
           return "error_page";
           
       
		
    }
    
    
    
    
    @RequestMapping(value = "/error_page2", method= RequestMethod.GET)
    public String errorPage2(Model model){
     
           return "error_page";
    

}
}
