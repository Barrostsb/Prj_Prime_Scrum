package com.barrostsb.prime_scrum.controller;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadController {

	     
	    private StreamedContent file;
	     
	    public FileDownloadController() {        
	        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
	        file = new DefaultStreamedContent(stream, "download/", "P.rar");
	    }
	 
	    public StreamedContent getFile() {
	        return file;
	    }
	}