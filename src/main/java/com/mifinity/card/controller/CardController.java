package com.mifinity.card.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.Card;
import com.mifinity.card.service.CardService;
import com.mifinity.card.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CardController {
	@Autowired 
	CardService cardService;
	@Autowired
	SessionService sessionService;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/search/{numbertext}")
	public List<Card> getCards(@PathVariable(value = "numbertext") String numberText) throws MifinityException {
		if (!sessionService.isSessionExists(RequestContextHolder.currentRequestAttributes().getSessionId())) {
			throw new MifinityException("Not logged in");
		}
		return cardService.getCards(numberText);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/create")
	public Card createCard(@Valid @RequestBody Card card) throws MifinityException {
		if (!sessionService.isSessionExists(RequestContextHolder.currentRequestAttributes().getSessionId())) {
			throw new MifinityException("Not logged in");
		}
		return cardService.createCard(card);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/delete/{number}")
	public Map<String, Boolean> deleteVehicle(@PathVariable(value = "number") String number)
			throws MifinityException {
		if (!sessionService.isSessionExists(RequestContextHolder.currentRequestAttributes().getSessionId())) {
			throw new MifinityException("Not logged in");
		}
		return cardService.deleteCard(number);
	}
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/update/{number}")
	public ResponseEntity<Card> updateCard(@PathVariable(value = "number") String number,
			@Valid @RequestBody Card cardDetails) throws MifinityException {
		if (!sessionService.isSessionExists(RequestContextHolder.currentRequestAttributes().getSessionId())) {
			throw new MifinityException("Not logged in");
		}
		final Card updatedVehicle = cardService.updateCard(number, cardDetails);
		return ResponseEntity.ok(updatedVehicle);
	}
	
}
