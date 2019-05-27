package com.example.render.controller.api;


import com.example.render.dao.CustomMongoRepo;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.entity.opinion;
import com.example.render.entity.statics.StaticsFile;
import com.example.render.entity.statics.StaticsSchema;
import com.example.render.entity.statics.StaticsTag;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.token.CheckAuthImpl;
import com.example.render.uploads.AWSStaticFileUpload;
import com.example.render.uploads.StaticsFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class StaticControllerApi {

    private HttpSession httpSession;
    private StaticsRepository sRepo;
    private CheckAuthImpl auth;
    private StaticsFileUpload sfu;
    private AWSStaticFileUpload awsStatic;
    private CustomMongoRepo customMongoRepo;


    @Autowired
    public StaticControllerApi(AWSStaticFileUpload awsStatic, CustomMongoRepo customMongoRepo, StaticsFileUpload sfu, HttpSession httpSession, StaticsRepository sRepo, CheckAuthImpl auth){
        this.httpSession = httpSession;
        this.sRepo = sRepo;
        this.auth = auth;
        this.sfu = sfu;
        this.awsStatic = awsStatic;
        this.customMongoRepo = customMongoRepo;
    }


    @RequestMapping(value = "/tag/static", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> staticFileUploadApi(@Valid @ModelAttribute("schema")StaticsSchema ss, BindingResult bindingResult , @Valid @ModelAttribute("opinions") opinion sc3, BindingResult bindingResult2
            , HttpServletRequest req, @RequestParam("@file1_1[]") MultipartFile[] filei, @RequestParam("@file2_2[]") MultipartFile[] filev, Model model) throws ServletException, IOException {

        auth.Authentication();



        String email = null;
        Schema user = null;
        try {
            email = req.getAttribute("email").toString();
            user = (Schema)req.getAttribute("user");
        }catch(Exception ex) {}

        UserAccountProgress userProgress = null;
        try {
          userProgress = customMongoRepo.findByRefId(user.getId());
        } catch(Exception ex){
            System.out.println("user progress is null");
        }

        if(userProgress != null) {

            if(userProgress.isOfficial() == true || userProgress.getStage() > 0) {

                if((filei.length > 0) || (filev.length > 0)) {

                if(bindingResult.hasErrors()) {
                    System.out.println("error in statics occured");
                    return ResponseEntity.ok().body("fill out properly");
                }


                if(bindingResult2.hasErrors()) {
                    System.out.println("error in statics occured");
                    return ResponseEntity.ok().body("at least two opinions needed");
                }

                ArrayList<String> flike = new ArrayList<String>();

                ss.setRefId(user.getId());
                ss.setRefSlug(user.getSlug());
                ss.setIs("static");
                ss.setCreatedAt(new Date());
                ss.setFilelikes(flike);

                ArrayList<String> f1 = new ArrayList<String>();

                StaticsTag sc2a = new StaticsTag();
                sc2a.setTagvalue(sc3.getA());
                sc2a.setF1(f1);


                StaticsTag sc2b = new StaticsTag();
                sc2b.setTagvalue(sc3.getB());
                sc2b.setF1(f1);


                StaticsTag sc2c = new StaticsTag();
                sc2c.setTagvalue(sc3.getC());
                sc2c.setF1(f1);


                StaticsTag sc2d = new StaticsTag();
                sc2d.setTagvalue(sc3.getD());
                sc2d.setF1(f1);


                StaticsTag sc2e = new StaticsTag();
                sc2e.setTagvalue(sc3.getE());
                sc2e.setF1(f1);


                ArrayList<Object> ar = new ArrayList<Object>();
                ar.add(sc2a);
                ar.add(sc2b);
                ar.add(sc2c);
                ar.add(sc2d);
                ar.add(sc2e);


                ss.setOpinions(ar);

//			String owntag ="*"+ss.getOwntag().trim();
//			ss.setOwntag(owntag);


                ArrayList<StaticsFile> filenames = null;





                 if(filei.length > 0) {
                    filenames = awsStatic.multipleUploads(filei);
                    System.out.println("file are "+filei.length);
                }
                else {
                    filenames = awsStatic.uploadFile2(filev[0]);
                    System.out.printf("filenames2", filev.hashCode());

                }

                if(filenames == null) {
                    return ResponseEntity.ok().body("Post not Submitted");
                }

                ss.setFile(filenames);


                sRepo.save(ss);
                System.out.println("saved");
                return ResponseEntity.ok().body("posted");

                } else {

                    return ResponseEntity.ok().body(" please upload files");

                }
            } else {

                return ResponseEntity.ok().body("to submit post must be on stage 1 or greater");
            }

        }

        else {


            return null;
        }
    }
}
