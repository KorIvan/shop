package com.tsystems.model;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsystems.service.CategoryService;
import com.tsystems.service.ManagerService;
@Component
public class CategoryEditor extends PropertyEditorSupport{
	@Autowired
	private ManagerService managerService;
	private static String toParse;
	@Override
    public void setAsText(String text) {
		if(!text.isEmpty()){
			toParse=text;
		}
		System.out.println("in setAsText ["+text+"]");
        Category c = this.managerService.getCategoryById(Long.valueOf(toParse));
        this.setValue(c);
    }
}
