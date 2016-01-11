package com.tsystems.model;

import java.beans.PropertyEditorSupport;
import java.net.ConnectException;

import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsystems.model.excep.ExceptionMessages;
import com.tsystems.repository.AddressRepository;
import com.tsystems.service.ClientService;

@Component
public class AddressEditor extends PropertyEditorSupport {
	private static Logger log = Logger.getLogger(AddressEditor.class.getName());
	@Autowired
	private AddressRepository addressRepository;
	private static String toParse;

	@Override
	public void setAsText(String text) {
		if (!text.isEmpty()) {
			toParse = text;
		}
		System.out.println("in setAsText [" + text + "]");
		Address a=null;
			try {
				a = this.addressRepository.findAddressById(Long.valueOf(toParse));
			} catch (EntityNotFoundException e) {
	log.error(ExceptionMessages.RESOURCE_DOES_NOT_EXISTS);
			} catch (ConnectException e) {
				log.error(ExceptionMessages.DATABASE_FAILED_CONNECT);
			} catch (NumberFormatException e) {
				log.error(ExceptionMessages.WRONG_ID);

			}
		this.setValue(a);
	}
}
