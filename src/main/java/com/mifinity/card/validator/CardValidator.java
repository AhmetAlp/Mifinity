package com.mifinity.card.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.Card;

@Component
public class CardValidator {

	private final static Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}");
	 
    public void validate(Card card) throws MifinityException {
    	if (!onlyDigits(card.getNumber())) {
    		throw new MifinityException("Card number can contain only digits and space");
    	} else if (card.getHolderName() == null || card.getHolderName().trim().isEmpty()) {
    		throw new MifinityException("Card holder name can not be empty");
    	} else if (!DATE_PATTERN.matcher(card.getExpiryDate()).matches()) {
    		throw new MifinityException("Card expiry date wrong format. YY/MM");
    	}

    }
    
    private boolean onlyDigits(String str) 
    { 
        for (int i = 0; i < str.length(); i++) { 
            if ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) != ' ') { 
                return false; 
            } 
        } 
        return true; 
    } 
}
