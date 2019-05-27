package com.example.render.controller.api;


import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.user.Schema;
import com.example.render.token.CheckAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class StaticToogleLikeApi {


    private UserAccountRepository jrepo;
    private CheckAuthImpl auth;
    private StaticsRepository srepo;


    @Autowired
    public StaticToogleLikeApi(UserAccountRepository jrepo, ComparisionRepository crepo, CheckAuthImpl auth, StaticsRepository srepo){
        this.auth = auth;
        this.jrepo = jrepo;
        this.srepo = srepo;
    }



    public Schema getUser(HttpServletRequest req){
        Schema user = null;

        try {
            user = (Schema) req.getAttribute("user");
        }catch (NullPointerException ex){}
        return  user;
    }




    @PutMapping("/static_like/{id}")
    public ResponseEntity<?> toogleLike(@PathVariable("id") String statId, HttpServletRequest req) throws ServletException, IOException {

        auth.Authentication();
       System.out.println("iam in static like toogle");
        srepo.addLikeToFile(statId.trim(), getUser(req).getSlug());

        return ResponseEntity.ok().body("success");
    }


}
