package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.AddOfferValidator;

@Controller
public class OffersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;
	
	@Autowired
	private AddOfferValidator addOfferValidator;

	@RequestMapping("/offer/list")
	public String getList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());

		return "offer/list";

	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setMark(@Validated Offer offer, BindingResult result, Model model) {
		addOfferValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "offer/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		offer.setUser(activeUser);
		offersService.addOffer(offer);
		return "redirect:/offer/list";

	}
	
	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("offer", new Offer());
		return "/offer/add";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String email = auth.getName();
//		User activeUser = usersService.getUserByEmail(email);
//		
//		if (activeUser.getOffers().contains(offersService.getOffer(id))) {
//			offersService.deleteOffer(id);
//		}
		
		offersService.deleteOffer(id);
		return "redirect:/offer/list";

	}

}
