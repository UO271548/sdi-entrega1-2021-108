package com.uniovi.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/offer/list")
	public String getList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("offersList", activeUser.getOffers());

		return "offer/list";

	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@Validated Offer offer, BindingResult result, Model model) {
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
	public String deleteOffer(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Offer o =offersService.getOfferOfUser(id,email);
		if (o != null) {
			offersService.deleteOffer(id);
		}
		return "redirect:/offer/list";

	}

	@RequestMapping(value = "/offer/search", method = RequestMethod.GET)
	public String findOffer(Model model, @RequestParam(value = "", required = false) String searchText,
			Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		if (searchText != null && !searchText.isEmpty()) {

			offers = offersService.searchOffersByTitle(pageable, searchText, activeUser.getEmail());
		} else {
			offers = offersService.searchOffersByTitle(pageable, "%" + "%", activeUser.getEmail());
		}

		model.addAttribute("offersList", offers.getContent());
		model.addAttribute("page", offers);

		return "/offer/search";
	}

	@RequestMapping(value = "/offer/buy/{id}")
	public String buyOffer(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Offer offer = offersService.getOffer(id);
		
		if (offersService.buyOffer(offer, activeUser)) {
			httpSession.setAttribute("uMoney", activeUser.getMoney());
			redirectAttributes.addFlashAttribute("error", false);
		}else {
			redirectAttributes.addFlashAttribute("error", true);
		}
		
		
		return "redirect:/offer/search";
	}
	
	@RequestMapping("/offer/purchase")
	public String getPurchases(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("purchasesList", activeUser.getPurchases());

		return "offer/purchase";

	}

}
