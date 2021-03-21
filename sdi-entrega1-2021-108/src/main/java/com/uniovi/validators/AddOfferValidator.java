package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Component
public class AddOfferValidator implements Validator {


	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");
		if (offer.getDescription().length() > 60) {
			errors.rejectValue("description", "Error.add.description.length");
		}
		if(offer.getTitle().length() <8 || offer.getTitle().length() > 24) {
			errors.rejectValue("title", "Error.add.title.length");
		}
		if (offer.getPrice() < 0) {
			errors.rejectValue("price", "Error.add.price.negative");
		}
		
	}
}
