package com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_AddOfferView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_SearchOfferView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\nacho\\Desktop\\PL-SDI-Sesion5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offerService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private UsersRepository usersRepository;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() {
		initdb();
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {

		driver.quit();
	}

	private void initdb() {

		usersRepository.deleteAll();

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
		Offer ou1 = new Offer("Mesa Escritorio", "Mesa de escritorio de 80x40 cm", 30, user1);
		Offer ou2 = new Offer("Silla de oficina", "Silla perfecta para el ordenador. Muy comoda y ligera.", 70, user1);
		Offer ou3 = new Offer("Monitor", "Monitor 1080 para el ordenador", 60, user1);
		offers1.add(ou1);
		offers1.add(ou2);
		offers1.add(ou3);

		Set<Offer> offers2 = new HashSet<Offer>();
		Offer ou4 = new Offer("Mi Smart Band 4", "Reloj Xiaomi Smart Band 4, puesto en muy pocas ocasiones", 18, user2);
		Offer ou5 = new Offer("Juego de maletas",
				"Juego de dos maletas o Bolsas de Viaje nuevas sin estrenar en su bolsa.", 5, user2);
		Offer ou6 = new Offer("PlayStation 4", "PlayStation 4 en muy buen estado.", 150, user2);
		offers2.add(ou4);
		offers2.add(ou5);
		offers2.add(ou6);

		Set<Offer> offers3 = new HashSet<Offer>();
		Offer ou7 = new Offer("IPHONE 7", "IPhone 7 en buen estado.", 100, user3);
		Offer ou8 = new Offer("Libro El Imperio Final",
				"Libro de Brandon Sanderson el Imperio de Final de la saga Nacidos de la bruma", 15, user3);
		Offer ou9 = new Offer("Videojuego FFIX", "Precintado con el envoltorio original.", 70, user3);
		offers3.add(ou7);
		offers3.add(ou8);
		offers3.add(ou9);

		Set<Offer> offers4 = new HashSet<Offer>();
		Offer ou10 = new Offer("Opel Corsa", "Opel Corsa con 100.000 km", 1000, user4);
		Offer ou11 = new Offer("Teclado Logitech", "Limpio y en muy buen estado. Casi no usado", 50, user4);
		Offer ou12 = new Offer("Zapatos de montaña", "Zapatos de montaña sin usar", 10, user4);
		offers4.add(ou10);
		offers4.add(ou11);
		offers4.add(ou12);

		Set<Offer> offers5 = new HashSet<Offer>();
		Offer ou13 = new Offer("Xiaomi Redmi 7A Matte Blue",
				"Xiaomi Redmi 7A Matte Blue nuevo sin estrenar (precintado) 16GB y 2 Gb de RAM", 80, user5);
		Offer ou14 = new Offer("Televisor Samsung", "Televisor de 43 pulgadas 4K ", 80, user5);
		Offer ou15 = new Offer("Chromecast", "Nuevo en el envoltorio original", 10, user5);
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

	@Test
	public void Prueba1() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba2() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba3() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "prueba1@gmail.com", "Josefo", "Perez", "77777", "77778");
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba4() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "user1@gmail.com", "Josefo", "Perez", "77777", "77777");
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba5() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_View.checkElement(driver, "text", "admin@gmail.com");
	}

	@Test
	public void Prueba6() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "user1@gmail.com");
	}

	@Test
	public void Prueba7() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		PO_LoginView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba8() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "1");
		PO_LoginView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba9() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "noExist@gmail.com", "123456");
		PO_LoginView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba11() {
		try {
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		} catch (Exception e) {
			System.out.println("El boton de logout no esta visible sin login.");
		}

	}

	@Test
	public void Prueba12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "/user/list", "class", "text");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
	}

	@Test
	public void Prueba13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "/user/list", "class", "text");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = driver.findElements(By.id("checkbox"));
		elementos.get(0).click();
		driver.findElement(By.id("btndelete")).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);

	}

	@Test
	public void Prueba14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "/user/list", "class", "text");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = driver.findElements(By.id("checkbox"));
		elementos.get(elementos.size() - 1).click();
		driver.findElement(By.id("btndelete")).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);

	}

	@Test
	public void Prueba15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "/user/list", "class", "text");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = driver.findElements(By.id("checkbox"));
		elementos.get(0).click();
		elementos.get(1).click();
		elementos.get(2).click();
		driver.findElement(By.id("btndelete")).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	@Test
	public void Prueba16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		elementos.get(0).click();

		PO_AddOfferView.fillForm(driver, "tituloprueba", "descriptionPrueba", "11");

		elementos = PO_View.checkElement(driver, "text", "tituloprueba");

	}

	@Test
	public void Prueba17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		elementos.get(0).click();

		PO_AddOfferView.fillForm(driver, "", "descriptionPrueba", "11");

		PO_AddOfferView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}

	@Test
	public void Prueba18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 3);
	}

	@Test
	public void Prueba19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	@Test
	public void Prueba20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(elementos.size() - 1).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	@Test
	public void Prueba21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		elementos.get(0).click();
		PO_SearchOfferView.fillForm(driver, "");

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(2).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(3).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);

	}

	@Test
	public void Prueba22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		elementos.get(0).click();
		PO_SearchOfferView.fillForm(driver, "aaaaaaaaaaaaaaaaaa");

		elementos = driver.findElements(By.linkText("Comprar"));
		assertTrue(elementos.size() == 0);
		elementos = driver.findElements(By.linkText("Vendido"));
		assertTrue(elementos.size() == 0);
	}

	@Test
	public void Prueba23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		elementos.get(0).click();
		PO_SearchOfferView.fillForm(driver, "Mi Smart Band 4");

		elementos = PO_View.checkElement(driver, "free", "//td/following-sibling::*/a[contains(@href, 'offer/buy')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "62");

	}

	@Test
	public void Prueba24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		elementos.get(0).click();
		PO_SearchOfferView.fillForm(driver, "IPHONE 7");

		elementos = PO_View.checkElement(driver, "free", "//td/following-sibling::*/a[contains(@href, 'offer/buy')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "0");

	}

	@Test
	public void Prueba25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		elementos.get(0).click();
		PO_SearchOfferView.fillForm(driver, "PlayStation 4");

		elementos = PO_View.checkElement(driver, "free", "//td/following-sibling::*/a[contains(@href, 'offer/buy')]");
		elementos.get(0).click();

		PO_View.checkKey(driver, "Error.money", PO_Properties.getSPANISH());

	}

	@Test
	public void Prueba26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-purchases-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/purchase')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Chromecast");
		PO_View.checkElement(driver, "text", "Zapatos de montaña");

	}

	@Test
	public void Prueba27() {
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());
		PO_NavView.changeIdiom(driver, "English");
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getENGLISH());
		PO_NavView.changeIdiom(driver, "Spanish");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@gmail.com", "admin");
		PO_HomeView.clickOption(driver, "/user/list", "class", "text");
		PO_View.checkKey(driver, "user.managment.message", PO_Properties.getSPANISH());
		PO_NavView.changeIdiom(driver, "English");
		PO_View.checkKey(driver, "user.managment.message", PO_Properties.getENGLISH());

		PO_NavView.logOut(driver);

		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");

		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		elementos.get(0).click();

		PO_View.checkKey(driver, "offer.add.title.message", PO_Properties.getSPANISH());
		PO_NavView.changeIdiom(driver, "English");
		PO_View.checkKey(driver, "offer.add.title.message", PO_Properties.getENGLISH());
		PO_NavView.changeIdiom(driver, "Spanish");

		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-managment-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/list')]");
		elementos.get(0).click();

		PO_View.checkKey(driver, "title.message", PO_Properties.getSPANISH());
		PO_NavView.changeIdiom(driver, "English");
		PO_View.checkKey(driver, "title.message", PO_Properties.getENGLISH());

	}

	@Test
	public void Prueba28() {
		driver.navigate().to("http://localhost:8090/user/list");
		PO_View.checkElement(driver, "text", "Contraseña");

	}

	@Test
	public void Prueba29() {
		driver.navigate().to("http://localhost:8090/offer/list");
		PO_View.checkElement(driver, "text", "Contraseña");

	}

	@Test
	public void Prueba30() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "user1@gmail.com", "123456");
		driver.navigate().to("http://localhost:8090/user/list");
		assertEquals(driver.getTitle(), "HTTP Status 403 – Forbidden");

	}

}
