package fr.univavignon.pokedex.core.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class PokemonFactorySelenium {
	private final String BASE_URL = "https://pokeassistant.com/main/ivcalculator?locale=en";


	/**
	 * Recuperer iv d'un pokemon sur site à l'aide de selenium
	 * @param pok
	 * @return
	 */
	public int findIv(String name, int cp, int hp, int dust) {
		
		//ChromeDriverManager.getInstance().setup(); //Firefox car impossible de faire marcher phantomJS ou chrome depuis circleCI
		//WebDriver driver = new ChromeDriver();
		
		FirefoxDriverManager.getInstance().setup(); //Firefox car impossible de faire marcher phantomJS ou chrome depuis circleCI
		WebDriver driver = new FirefoxDriver();
		
		driver.get(BASE_URL);
		
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
			
			String res = ((WebElement) driver.findElement(By.xpath("//*[@id=\"possibleCombinationsStringmax\"]//b")))
					.getText();
			n = Math.round(Float.parseFloat(res.replace("%", "")));
			((WebElement) driver.findElement(By.xpath("//*[@id=\"search_pokemon_name\"]"))).sendKeys(n + "");

		} catch (NoSuchElementException e) { e.printStackTrace(); }
		
		driver.quit();
		
		return n;
	}
}
