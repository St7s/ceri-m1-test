package fr.univavignon.pokedex.core;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;

public class PokedexTest extends IPokedexTest {
	
	

	private ObjectInputStream ois;
	private ObjectOutputStream oos;


	/**
	 * setup
	 */
	@Before
    public void setUp()   {
		//create pokemon metadata provider
		this.setiPokedexTest(new Pokedex());
		
		//les pokemons sont créer dans chaque tests car il faut pouvoir tester les tailles et lse indices
    }
	
	
	/**
	 * On test la serialisation d'un pokedex avec un pokemon
	 * @throws PokedexException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testSerialization() throws PokedexException, FileNotFoundException, IOException, ClassNotFoundException {
		Pokemon p = getiPokedexTest().createPokemon(0, 613, 64, 4000, 4);
		getiPokedexTest().addPokemon(p);
		
		this.pokemonBulbizarre = getiPokedexTest().getPokemon(0);
		assertEquals(1, getiPokedexTest().size());//on verifie qu'on a un pokemon
		runAssertOnPokemonBulbizarre(); //on verifie les données de bulbisaur
		
		
		File fichier =  new File("./src/main/ressources/db/pokedex.ser") ;
		System.out.println(fichier.getAbsolutePath());
		oos = new ObjectOutputStream(new FileOutputStream(fichier));
		 // sérialization de l'objet
		oos.writeObject(getiPokedexTest()) ;
		
			
		//Maintenant on récupere l'objet et on test
		File fichier2 =  new File("./src/main/ressources/db/pokedex.ser") ;
		ois = new ObjectInputStream(new FileInputStream(fichier2));
		 // désérialization de l'objet
		IPokedex podekekDuFichier = (IPokedex)ois.readObject() ;
		
		
		
		this.pokemonBulbizarre = podekekDuFichier.getPokemon(0);
		assertEquals(1, podekekDuFichier.size());//on verifie qu'on a un pokemon
		runAssertOnPokemonBulbizarre(); //on verifi les données de bulbisaur sont bien récuperer depuis le fichier
		
	}

}
