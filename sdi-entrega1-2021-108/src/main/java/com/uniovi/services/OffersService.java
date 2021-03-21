package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}
	
	public List<Offer> getOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findAll().forEach(offers::add);
		return offers;
	}
	
	
	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}


	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}
	
	public Offer getOfferOfUser(Long id, String email) {
		return offersRepository.getOfferOfUser(id, email);
	}
	
	public Page<Offer> searchOffersByTitle(Pageable pageable,String searchText, String uEmail){
		searchText = "%"+searchText+"%";

		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		
		offers = offersRepository.searchByTitle(pageable,searchText, uEmail);
		return offers;
	}
	
	public boolean buyOffer(Offer offer, User user) {
		if (user.getMoney() >= offer.getPrice()) {
			user.setMoney(user.getMoney() - offer.getPrice());
			user.getPurchases().add(offer);
			offer.setBuyer(user);
			usersRepository.save(user);
			addOffer(offer);
			return true;

		}
		return false;
	}
	

}
