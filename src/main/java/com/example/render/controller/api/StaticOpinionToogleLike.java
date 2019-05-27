package com.example.render.controller.api;


import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.statics.StaticsSchema;
import com.example.render.entity.user.Schema;
import com.example.render.token.CheckAuthImpl;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class StaticOpinionToogleLike {




    private UserAccountRepository jrepo;
    private CheckAuthImpl auth;
    private StaticsRepository srepo;

    @Autowired
    public StaticOpinionToogleLike(UserAccountRepository jrepo, CheckAuthImpl auth, StaticsRepository srepo){
        this.auth = auth;
        this.jrepo = jrepo;
        this.srepo = srepo;
    }



    public Schema getUser(HttpServletRequest req){
        Schema user = null;
        try{

            user = (Schema) req.getAttribute("user");

        } catch(Exception ex){}

        return user;
    }


    @PutMapping("/opinionLike/{statId}/{index}")
    public ResponseEntity<?> opinionLikeToogleA(@PathVariable("statId") String id,
                                                @PathVariable("index") int i,
                                                HttpServletRequest req) throws ServletException, IOException {
        auth.Authentication();

       StaticsSchema updateResult = srepo.toogleOpnLike(id, getUser(req).getSlug(),i,"f1");
        return ResponseEntity.ok().body(updateResult);
    }


}
