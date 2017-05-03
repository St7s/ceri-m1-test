package fr.univavignon.pokedex.core.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;


/**
 * Class permettant de recuper l'i d'un pokemon à partir d'un site web
 * @author adrie
 *
 */
public class FindIVOnWebWithSelenium {
	private final String BASE_URL = "https://pokeassistant.com/main/ivcalculator?locale=en";
	private WebDriver driver;
	
	/**
	 * Constructeur
	 */
	public FindIVOnWebWithSelenium(){
		ChromeDriverManager.getInstance().setup(); //recupération de l'instance de chrome (pas reussi à faire marcher PhontomJS)
		driver = new ChromeDriver(); //creation du driver de chrome
		driver.get(BASE_URL);
	}


	/**
	 * Recuperer iv d'un pokemon sur site à l'aide de selenium
	 * @param pok
	 * @return
	 */
	public int findIv(String name, int cp, int hp, int dust) {
		
		int n = -1;
		try {
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_pokemon_name\"]"))).sendKeys(name);
			((WebElement) driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/span/div/div/div"))).click();
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_cp\"]"))).sendKeys(String.valueOf(cp));
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_hp\"]"))).sendKeys(String.valueOf(hp));
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_dust\"]"))).sendKeys(String.valueOf(dust));
			((WebElement) driver.findElement(By.xpath("//*[@id=\"calculatebtn\"]"))).click();
	
			// La seule solution car le site effectue le calcul en ajax et on ne
			// peut pas checker un element DOM de la page
			// car l'ensemble des éléments sont déja présent. Et un "wait.until" ne
			// peut pas avoir de condition approprié
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			//on recupere la valeur
			String res = ((WebElement) driver.findElement(By.xpath("//*[@id=\"possibleCombinationsStringmax\"]//b")))
					.getText();
			//on arrondi la valeur
			n = Math.round(Float.parseFloat(res.replace("%", "")));
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_pokemon_name\"]"))).sendKeys(n + "");

		} catch (NoSuchElementException e) { e.printStackTrace(); }
		
		//on retorune l'iv
		return n;
	}


	/**
	 * On ferme le driver
	 */
	public void quit() {
		driver.quit();	
	}
}
