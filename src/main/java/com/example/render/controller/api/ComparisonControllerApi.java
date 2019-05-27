package com.example.render.controller.api;


import com.example.render.dao.CustomMongoRepo;
import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.comparision.ComparisionTags;
import com.example.render.entity.opinion;
import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;
import com.example.render.token.CheckAuthImpl;
import com.example.render.uploads.ComparisionFileUploadInterface;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Controller
public class ComparisonControllerApi {



    private ComparisionRepository cRepo;
    private HttpSession httpSession;
    private ComparisionFileUploadInterface comparisionFileUploadInterface;
    private CheckAuthImpl auth;
    private CustomMongoRepo customMongoRepo;


    @Autowired
    public ComparisonControllerApi(CustomMongoRepo customMongoRepo, CheckAuthImpl auth, ComparisionRepository cRepo, ComparisionFileUploadInterface comparisionFileUploadInterface, HttpSession httpSession) {
        this.cRepo = cRepo;
        this.httpSession = httpSession;
        this.auth = auth;
        this.customMongoRepo = customMongoRepo;
        this.comparisionFileUploadInterface = comparisionFileUploadInterface;
    }


    @RequestMapping(value = "/tag/comparison",method =  RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> comparisonApiPost(@Valid @ModelAttribute("schema") ComparisionSchema comparisionSchema , BindingResult bindingResult, @Valid @ModelAttribute("opinions") opinion opn, BindingResult bindingResult3,
                                               @RequestParam("@file1_1") MultipartFile[] file1, @RequestParam("@file2_2") MultipartFile[] file2, Model model
            , HttpServletRequest req) throws ServletException, IOException, ExecutionException, InterruptedException {

        auth.Authentication();

        try {

            String email = null;
        Schema user = null;
        try {
            email = req.getAttribute("email").toString();
            user = (Schema)req.getAttribute("user");
        }catch(Exception ex) {}


        UserAccountProgress userProgress =  customMongoRepo.findByRefId(user.getId());




            if(userProgress.isOfficial() == true || userProgress.getStage() > 0) {

                    if (!file1[0].isEmpty() && !file2[0].isEmpty()) {


                        if (bindingResult.hasErrors()) {

                            return ResponseEntity.ok().body("fill out all fields properly");
                        }

                        if (bindingResult3.hasErrors()) {

                            return ResponseEntity.ok().body("at least 2 opinions are necessary");
                        }


                        ArrayList<String> f1like = new ArrayList<String>();
                        ArrayList<String> f2like = new ArrayList<String>();


                        comparisionSchema.setRefId(user.getId());
                        comparisionSchema.setRefSlug(user.getSlug());
                        comparisionSchema.setCreatedAt(new Date());
                        comparisionSchema.setIs("comparision");
                        comparisionSchema.setF1likes(f1like);
                        comparisionSchema.setF2likes(f2like);

                        ArrayList<String> f1 = new ArrayList<String>();
                        ArrayList<String> f2 = new ArrayList<String>();


                        ComparisionTags sc2a = new ComparisionTags();
                        sc2a.setTagvalue(opn.getA());
                        sc2a.setF1(f1);
                        sc2a.setF2(f2);

                        ComparisionTags sc2b = new ComparisionTags();
                        sc2b.setTagvalue(opn.getB());
                        sc2b.setF1(f1);
                        sc2b.setF2(f2);

                        ComparisionTags sc2c = new ComparisionTags();
                        sc2c.setTagvalue(opn.getC());
                        sc2c.setF1(f1);
                        sc2c.setF2(f2);

                        ComparisionTags sc2d = new ComparisionTags();
                        sc2d.setTagvalue(opn.getD());
                        sc2d.setF1(f1);
                        sc2d.setF2(f2);

                        ComparisionTags sc2e = new ComparisionTags();
                        sc2e.setTagvalue(opn.getE());
                        sc2e.setF1(f1);
                        sc2e.setF2(f2);


                        ArrayList<Object> ar = new ArrayList<Object>();
                        ar.add(sc2a);
                        ar.add(sc2b);
                        ar.add(sc2c);
                        ar.add(sc2d);
                        ar.add(sc2e);


                        comparisionSchema.setOpinions(ar);


                        CompletableFuture<String[]> firstfile = comparisionFileUploadInterface.uploadFile1(file1[0]);

                        CompletableFuture<String[]> secondfile = comparisionFileUploadInterface.uploadFile2(file2[0]);


//			CompletableFuture.allOf(firstfile, secondfile).join();
                        comparisionSchema.setFile1(firstfile.get()[0]);
                        comparisionSchema.setFile1type(firstfile.get()[1]);


                        comparisionSchema.setFile2(secondfile.get()[0]);
                        comparisionSchema.setFile2type(secondfile.get()[1]);

                        cRepo.save(comparisionSchema);
                        return ResponseEntity.ok().body("posted");

                    } else {

                        return ResponseEntity.ok().body("please upload image or video!");
                    }



            }

            else {

                return ResponseEntity.ok().body("To submit Post Must be on stage 1 or Above!");
            }

        }catch (Exception ex){ return ResponseEntity.ok().body("please upload image or video!"); }

    }


}
