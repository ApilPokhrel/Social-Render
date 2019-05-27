package com.example.render.token;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.render.dao.CustomMongoRepo;
import com.example.render.entity.user.Schema;

@Configuration
@Service
public class CheckAuthImpl implements CheckAuthInterface{

	private static final Logger logger = LoggerFactory.getLogger(CheckAuthImpl.class);



	private HttpServletRequest req;
	private HttpServletResponse res;
	private CustomMongoRepo customMongoRepo;

	@Autowired
	public CheckAuthImpl(HttpServletRequest req, CustomMongoRepo customMongoRepo, HttpServletResponse res) {
		this.req = req;
		this.customMongoRepo = customMongoRepo;
		this.res = res;
	}
	
	
	public String getToken() {
     String token = null;

        token = getFromCookie();

        return token;
	}

    private String getFromCookie() {

		String token = null;
        try {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie: cookies) {
            if(cookie.getName().equals("hijab")) {
                token = cookie.getValue().toString();
            }
        }
        }catch(Exception ex) {}
        return token;
    }







     @Override
	public void Authentication() throws ServletException, IOException {

		String token = null;

        token = getFromCookie();


        if(token != null) {
        Schema user = null;
        try {
        	
           user = customMongoRepo.findByToken(token);
           
        } catch(Exception ex) {}
        
			if(user != null) {
				
				
				boolean checked = user.isChecked();
				
				if(checked == true) {
					req.setAttribute("email", user.getEmail());
					req.setAttribute("user", user);
				}
				else {
					req.getRequestDispatcher("/verification").forward(req, res);
				}
			}
			else {
				res.sendRedirect("/login");
			}
		}
		else {
        	System.out.println("sending redirect");
			res.sendRedirect("/login");
			
		}
		
		
	}
}
