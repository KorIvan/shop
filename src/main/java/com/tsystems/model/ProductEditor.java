package com.tsystems.model;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsystems.service.ManagerService;
import com.tsystems.service.PersonService;


@Component
public class ProductEditor extends PropertyEditorSupport{
	@Autowired
	private ManagerService managerService;
	private static String toParse;

	@Override
	public void setAsText(String text) {
		if (!text.isEmpty()) {
			toParse = text;
		}
		System.out.println("in setAsText [" + text + "]");
		Product a = this.managerService.getProductById(Long.valueOf(toParse));
		this.setValue(a);
	}
}
