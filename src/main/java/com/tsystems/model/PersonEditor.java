package com.tsystems.model;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsystems.service.ClientService;
@Component
public class PersonEditor extends PropertyEditorSupport {
	@Autowired
	private ClientService clientService;
	private static String toParse;

	@Override
	public void setAsText(String text) {
		if (!text.isEmpty()) {
			toParse = text;
		}
		System.out.println("in setAsText [" + text + "]");
		Person a = this.clientService.getClientById(Long.valueOf(toParse));
		this.setValue(a);
	}
}
