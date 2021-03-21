package com.uniovi.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;

public interface OffersRepository extends CrudRepository<Offer, Long> {

	@Query("SELECT o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1) AND LOWER(o.user.email) NOT LIKE LOWER(?2))")
	Page<Offer> searchByTitle(Pageable pageable, String searchText, String uEmail);
	
	@Query("SELECT o FROM Offer o WHERE (o.id = ?1 AND LOWER(o.user.email) LIKE LOWER(?2))")
	Offer getOfferOfUser(Long id, String email);
}
