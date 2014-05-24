package com.barrostsb.prime_scrum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CarrouselController {
	     
	    private List<String> images;
	    private List<String> technologies;
	     
	    @PostConstruct
	    public void init() {
	        images = new ArrayList<String>();
	        for (int i = 1; i <= 12; i++) {
	            images.add("primescrum" + i + ".png");
	        }
	        
	        technologies = new ArrayList<String>();
	        for (int i = 1; i <= 7; i++) {
	        	technologies.add( i + ".png");
	        }
	    }
	 
	    public List<String> getImages() {
	        return images;
	    }
	    
	    public List<String> getTechnologies() {
	        return technologies;
	    }
}

