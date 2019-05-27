package com.example.render.controller.api;


import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.user.Schema;
import com.example.render.token.CheckAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ComparisionToogleLikeApi {


    private UserAccountRepository jrepo;
    private ComparisionRepository crepo;
    private CheckAuthImpl auth;
    private StaticsRepository srepo;

    @Autowired
    public ComparisionToogleLikeApi(UserAccountRepository jrepo, ComparisionRepository crepo, CheckAuthImpl auth, StaticsRepository srepo){
           this.auth = auth;
           this.crepo = crepo;
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



    @PutMapping("/comparing_like_1/{id}")
    public ResponseEntity<?> toogleLike1(@PathVariable("id") String compId, HttpServletRequest req) throws ServletException, IOException {

        auth.Authentication();

        crepo.addLikeToFile1(compId.trim(), getUser(req).getSlug());

        return ResponseEntity.ok().body("success");
    }



    @PutMapping("/comparing_like_2/{id}")
    public ResponseEntity<?> toogleLike2(@PathVariable("id") String compId, HttpServletRequest req) throws ServletException, IOException {

        auth.Authentication();

        crepo.addLikeToFile2(compId.trim(), getUser(req).getSlug());
        return ResponseEntity.ok().body("success");
    }
}
