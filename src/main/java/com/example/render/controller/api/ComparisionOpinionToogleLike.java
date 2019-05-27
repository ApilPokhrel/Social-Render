package com.example.render.controller.api;


import com.example.render.dao.comparision.ComparisionRepository;
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
public class ComparisionOpinionToogleLike {

    private CheckAuthImpl auth;
    private ComparisionRepository comparisionRepository;


    @Autowired
    public ComparisionOpinionToogleLike(CheckAuthImpl auth, ComparisionRepository comparisionRepository){
        this.auth = auth;
        this.comparisionRepository = comparisionRepository;
    }


    public Schema getUser(HttpServletRequest req){
        Schema user = null;
        try{
            user =(Schema) req.getAttribute("user");
        }catch(Exception ex){}

        return user;
    }


    @GetMapping("/opinionLikeA/{compId}/{index}")
    public ResponseEntity<?> opinionLikeToogle(@PathVariable("compId") String id,
                                                @PathVariable("index") int i,
                                                HttpServletRequest req) throws ServletException, IOException {
        auth.Authentication();

        comparisionRepository.addLikeToOpn(id, getUser(req).getSlug(),i,"f1");
        return ResponseEntity.ok().body("success mann");
    }

    @PutMapping("/opinionLikeA/{compId}/{index}")
    public ResponseEntity<?> opinionLikeToogleA(@PathVariable("compId") String id,
                                               @PathVariable("index") int i,
                                               HttpServletRequest req) throws ServletException, IOException {
           auth.Authentication();

           comparisionRepository.toogleOpnLike(id, getUser(req).getSlug(),i,"f1");
         return ResponseEntity.ok().body("success mann");
    }



    @PutMapping("/opinionLikeB/{compId}/{index}")
    public ResponseEntity<?> opinionLikeToogleB(@PathVariable("compId") String id,
                                               @PathVariable("index") int i,
                                               HttpServletRequest req) throws ServletException, IOException {
        auth.Authentication();

        comparisionRepository.toogleOpnLike(id, getUser(req).getSlug(),i,"f2");
        return ResponseEntity.ok().body("success mann");
    }
}
