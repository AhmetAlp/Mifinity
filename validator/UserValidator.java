package com.mifinity.card.validator;

import org.springframework.stereotype.Component;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.User;

@Component
public class UserValidator {

    public void validate(User u) throws MifinityException {
    	if (u.getUsername() == null && u.getUsername().trim().isEmpty()) {
    		throw new MifinityException("User name can not be empty");
    	}
    	if (u.getPassword() == null && u.getPassword().trim().isEmpty()) {
    		throw new MifinityException("Password can not be empty");
    	}

    }
}