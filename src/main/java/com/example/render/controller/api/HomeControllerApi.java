package com.example.render.controller.api;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.render.entity.statics.StaticsSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.render.dao.comparision.ComparisionRepository;
import com.example.render.dao.statics.StaticsRepository;
import com.example.render.dao.user.UserAccountRepository;
import com.example.render.entity.comparision.ComparisionSchema;
import com.example.render.entity.user.Schema;
import com.example.render.token.CheckAuthImpl;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class HomeControllerApi {


    private UserAccountRepository jrepo;
    private ComparisionRepository crepo;
    private CheckAuthImpl auth;
    private StaticsRepository srepo;

    
    @Autowired
    public HomeControllerApi(StaticsRepository srepo, CheckAuthImpl auth, ComparisionRepository crepo, UserAccountRepository jrepo){
        this.jrepo = jrepo;
        this.crepo = crepo;
        this.auth = auth;
        this.srepo = srepo;

    }
    
    
    
    public Schema getUser(HttpServletRequest req) {
    	Schema user = null;
    	try {
    		user = (Schema) req.getAttribute("user");
    	}catch(Exception ex) {}
    	
    	return user;
    }
    
    
    
    
    




    private void getUserAllComparingPost(HttpServletRequest req, List<ArrayList<Object>> list,List<ComparisionSchema> clist) throws InterruptedException, ExecutionException {


            clist.forEach(new Consumer<ComparisionSchema>() {

                int index = 0;

                @Override
                public void accept(ComparisionSchema t) {
                    // TODO Auto-generated method stub
//                    Schema userBySlug = jrepo.findOneBySlug(t.getRefSlug());
//
//
//
//                    ArrayList<Object> ar = new ArrayList<Object>();
//
//                    ar.add(userBySlug);
//                    ar.add(t);
//
//                    list.add(ar);

                    addPost(t.getRefSlug(), t, list);

                }
            });

    }









    @GetMapping("/allfeed")
    public ResponseEntity<?> allFeeds(HttpServletRequest req,
                                      @RequestParam("skip") int s,
                                      @RequestParam("limit") int l) throws ServletException, IOException, ExecutionException, InterruptedException {

        auth.Authentication();


        ArrayList<Schema> users = new ArrayList<Schema>();

        List<ArrayList<Object>> list = new ArrayList<>();


        List<ComparisionSchema> clist = null;

        try {

            clist =  crepo.getAllPost(l, s);
        } catch(Exception ex) {}


        if(clist != null) {
            getUserAllComparingPost(req, list, clist);
        }

        List<StaticsSchema> slist = null;

        try{

            slist =  srepo.getAllPost(l, s);
        }catch(NullPointerException ex){}

        if(slist != null) {

            getUserAllStaticPost(list, slist);
        }

        return ResponseEntity.ok().body(Arrays.asList(list, getUser(req)));
    }







    private void getUserAllStaticPost(List<ArrayList<Object>> list,List<StaticsSchema> slist) throws InterruptedException, ExecutionException {
            slist.forEach(new Consumer<StaticsSchema>() {
                @Override
                public void accept(StaticsSchema staticsSchema) {
//                    Schema user2 = jrepo.findOneBySlug(staticsSchema.getRefSlug());
//
//
//
//                    ArrayList<Object> ar = new ArrayList<Object>();
//
//                    ar.add(user2);
//                    ar.add(staticsSchema);
//
//                    list.add(ar);

                    addPost(staticsSchema.getRefSlug(), staticsSchema, list);

                }
            });

    }



    void addPost(String slug, Object schema, List<ArrayList<Object>> list){

        Schema user2 = jrepo.findOneBySlug(slug);



        ArrayList<Object> ar = new ArrayList<Object>();

        ar.add(user2);
        ar.add(schema);

        list.add(ar);

    }





// from node
    @GetMapping("/from_node")
    public ResponseEntity<?> fromNode() throws ServletException, IOException {
        auth.Authentication();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("hijab", auth.getToken());
        HttpEntity<String> httpEntity = new HttpEntity<String>("tonode", httpHeaders);

        ResponseEntity responseEntity = restTemplate.exchange("http://localhost:3000/api/allpost", HttpMethod.GET, httpEntity, String.class);
        return responseEntity;
    }
}



