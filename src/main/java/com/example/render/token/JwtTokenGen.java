package com.example.render.token;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.user.Schema;
import com.example.render.entity.Tokens;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenGen {
	
	private HttpServletRequest req;
    private UserAccountRepository User;
    
    public JwtTokenGen (HttpServletRequest req, UserAccountRepository User){
    	this.req = req;
    	this.User = User;
    }
    
    
    long milisec = System.currentTimeMillis();
    
    Date now = new Date(milisec);
    
    
    public Tokens generateAuthToken(String email) {
    	
    	Tokens tokens = new Tokens();
    	
    	Schema user = null;
    	try {
    		user = User.findByEmail(email);
    	}catch(Exception ex) {}
    	if(user != null) {
    		
    		String access = "auth";
    		String token = Jwts.builder()
    				.claim("_id", user.getId())
    				.claim("access", access)
    				.setIssuedAt(now)
    				.signWith(SignatureAlgorithm.HS256, "mysecrethere...".getBytes())
    				.setHeaderParam("alg", "HS256")
    				.setHeaderParam("typ", "JWT")
    				.compact();
    		
    		tokens.setAccess(access);
    		tokens.setToken(token);
    		
    		ArrayList<Tokens> arr = new  ArrayList<Tokens>();
    		arr.add(tokens);
    		user.setTokens(arr);
    		
    		User.save(user);
    		
    		return tokens;
    	}
    	return null;
    }
}
