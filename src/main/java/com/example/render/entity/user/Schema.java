package com.example.render.entity.user;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "useraccounts")
public class Schema {
    
    @Id
    @JsonIgnore
    private
    String id;
    
    @NotNull
    @Size(min=10, message="email cannot be less than 10 characters")
    @TextIndexed
    @JsonIgnore
    private String email;
    
    @Size(min=6, max=30, message="Name cannot be empty or exceed 30 characters")
    private String name;
    @Size(min=6, max=30, message="Fill out proper contact number")
    private String contact;
    @NotNull
    private String gender;
    private boolean checked;
    
    @Size(min=3, message="profession cannot be empty")
    private String profession;
    @NotNull
    @Size(min=8, max=13, message="Fill out proper Birth details")
    private String birthdate;
    
    private String image;
    
    @Size(min=6, max=30, message="Password should be Atleast 6 characters")
    @JsonIgnore
    private String password;
    private String createddate;
    
    @JsonIgnore
    private ArrayList tokens;
     
    private String slug;



    
    public Schema(String id, String email, String name, String contact, String gender, String profession, boolean checked, String birthdate, String image, String password, String createddate, ArrayList tokens, String slug) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.profession = profession;
        this.checked = checked;
        this.birthdate = birthdate;
        this.image = image;
        this.password = password;
        this.createddate = createddate;
        this.tokens = tokens;
        this.slug = slug;
    }

    public Schema() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }
    
    public ArrayList getTokens() {
		return tokens;
	}
	/**
	 * @param tokens the tokens to set
	 */
	public void setTokens(ArrayList tokens) {
		this.tokens = tokens;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
}
