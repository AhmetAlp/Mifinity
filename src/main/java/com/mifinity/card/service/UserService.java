package com.mifinity.card.service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.User;
import com.mifinity.card.repository.UserRepository;
import com.mifinity.card.validator.UserValidator;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserValidator userValidator;

    public User createUser(User user) throws MifinityException {
    	userValidator.validate(user);
    	if (userRepository.findById(user.getUsername()).isPresent()) {
    		throw new MifinityException("User name already created");
    	}
        user.setPassword(getMD5EncryptedValue(user.getPassword()));
        return userRepository.save(user);
    }

    public void loginUser(String userName, String pass) throws MifinityException {    	
        if (userRepository.findById(userName).isPresent()) {
        	if (userRepository.findById(userName).get().getPassword().equals(getMD5EncryptedValue(pass))) {
        		sessionService.putSession(RequestContextHolder.currentRequestAttributes().getSessionId(), userName);
        	} else {
        		throw new MifinityException("Login no valid");
        	}
        } else {
        	throw new MifinityException("Login not valid");
        }
    }
    
    public void logoutUser() {
    	sessionService.removeSession(RequestContextHolder.currentRequestAttributes().getSessionId());
    }
    
    private String getMD5EncryptedValue(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();

            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }
}