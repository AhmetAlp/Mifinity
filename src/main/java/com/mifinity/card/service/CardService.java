package com.mifinity.card.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.Card;
import com.mifinity.card.repository.CardRepository;
import com.mifinity.card.validator.CardValidator;

@Service
public class CardService {
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private CardValidator cardValidator;
	
	public List<Card> getCards(String numberText) {
		return cardRepository.findByCardNumber(numberText);
	}
	
	public Card createCard(Card card) throws MifinityException {
		cardValidator.validate(card);
		if (cardRepository.findById(card.getNumber()).isPresent()) {
			throw new MifinityException("Card number duplicated :: " + card.getNumber());
		}
		return cardRepository.save(card);
	}
	
	public Map<String, Boolean> deleteCard(String number) throws MifinityException {
		Card card = cardRepository.findById(number)
				.orElseThrow(() -> new MifinityException("Card not found for this number :: " + number));
		cardRepository.delete(card);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;		
	}

	public Card updateCard(String number, Card cardDetails) throws MifinityException {
		Card card = cardRepository.findById(number)
				.orElseThrow(() -> new MifinityException("Card not found for this number :: " + number));

		card.setNumber(cardDetails.getNumber());
		card.setHolderName(cardDetails.getHolderName());
		card.setExpiryDate(cardDetails.getExpiryDate());
		return cardRepository.save(card);		
	}
}
