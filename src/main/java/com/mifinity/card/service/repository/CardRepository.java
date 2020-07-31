package com.mifinity.card.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mifinity.card.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String>{
	@Query("SELECT t FROM Card t WHERE t.number like %?1%")
	List<Card> findByCardNumber(String number);	
}
