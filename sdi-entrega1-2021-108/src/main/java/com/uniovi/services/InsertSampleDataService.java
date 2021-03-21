package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offerService;

	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("user1@gmail.com", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("user2@gmail.com", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		
		User user3 = new User("user3@gmail.com", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		
		User user4 = new User("user4@gmail.com", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("user5@gmail.com", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		
		User user6 = new User("admin@gmail.com", "Admin", "Admin");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);
		
		
		
		Set<Offer> offers1 = new HashSet<Offer>();
		Offer ou1 = new Offer("Mesa Escritorio","Mesa de escritorio de 80x40 cm", 30, user1);
		Offer ou2 = new Offer("Silla de oficina","Silla perfecta para el ordenador. Muy comoda y ligera.", 70, user1);
		Offer ou3 = new Offer("Monitor","Monitor 1080 para el ordenador", 60, user1);
		offers1.add(ou1);
		offers1.add(ou2);
		offers1.add(ou3);
		
		
		Set<Offer> offers2 = new HashSet<Offer>();
		Offer ou4 = new Offer("Mi Smart Band 4","Reloj Xiaomi Smart Band 4, puesto en muy pocas ocasiones",18,user2);
		Offer ou5 = new Offer("Juego de maletas","Juego de dos maletas o Bolsas de Viaje nuevas sin estrenar en su bolsa.",5,user2);
		Offer ou6 = new Offer("PlayStation 4","PlayStation 4 en muy buen estado.",150,user2);
		offers2.add(ou4);
		offers2.add(ou5);
		offers2.add(ou6);
		
		
		Set<Offer> offers3 = new HashSet<Offer>();
		Offer ou7 = new Offer("IPHONE 7","IPhone 7 en buen estado.",100,user3);
		Offer ou8 = new Offer("Libro El Imperio Final","Libro de Brandon Sanderson el Imperio de Final de la saga Nacidos de la bruma",15,user3);
		Offer ou9 = new Offer("Videojuego FFIX","Precintado con el envoltorio original.",70,user3);
		offers3.add(ou7);
		offers3.add(ou8);
		offers3.add(ou9);
		
		
		Set<Offer> offers4 = new HashSet<Offer>();
		Offer ou10 = new Offer("Opel Corsa","Opel Corsa con 100.000 km",1000,user4);
		Offer ou11 = new Offer("Teclado Logitech","Limpio y en muy buen estado. Casi no usado",50,user4);
		Offer ou12 = new Offer("Zapatos de montaña","Zapatos de montaña sin usar",10,user4);
		offers4.add(ou10);
		offers4.add(ou11);
		offers4.add(ou12);
		
		Set<Offer> offers5 = new HashSet<Offer>();
		Offer ou13 = new Offer("Xiaomi Redmi 7A Matte Blue","Xiaomi Redmi 7A Matte Blue nuevo sin estrenar (precintado) 16GB y 2 Gb de RAM",80,user5);
		Offer ou14 = new Offer("Televisor Samsung","Televisor de 43 pulgadas 4K ",80,user5);
		Offer ou15 = new Offer("Chromecast","Nuevo en el envoltorio original",10,user5);
		offers5.add(ou13);
		offers5.add(ou14);
		offers5.add(ou15);
		
		user1.setOffers(offers1);
		user2.setOffers(offers2);
		user3.setOffers(offers3);
		user4.setOffers(offers4);
		user5.setOffers(offers5);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		user1.setPurchases(new HashSet<Offer>());
		user2.setPurchases(new HashSet<Offer>());
		user3.setPurchases(new HashSet<Offer>());
		user4.setPurchases(new HashSet<Offer>());
		user5.setPurchases(new HashSet<Offer>());
		
		offerService.buyOffer(ou15, user1);
		offerService.buyOffer(ou12, user1);
		
		offerService.buyOffer(ou11, user2);
		offerService.buyOffer(ou5, user2);
		
		offerService.buyOffer(ou3, user3);
		offerService.buyOffer(ou14, user3);
		
		offerService.buyOffer(ou9, user4);
		offerService.buyOffer(ou1, user4);
		
		offerService.buyOffer(ou13, user5);
		offerService.buyOffer(ou1, user5);
		
		
		

	}
}
