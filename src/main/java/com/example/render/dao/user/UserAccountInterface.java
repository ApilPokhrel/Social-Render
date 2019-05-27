package com.example.render.dao.user;

import org.springframework.stereotype.Repository;

import com.example.render.entity.user.Schema;



@Repository
public interface UserAccountInterface {


	Schema findOneBySlug(String slug);



}
