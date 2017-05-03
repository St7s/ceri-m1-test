package fr.univavignon.pokedex.core;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokemonFactoryTest;

public class PokemonFactoryTest extends IPokemonFactoryTest {
	
	/**
	 * setUp
	 */
	@Before
    public void setUp(){
		
		//on creer notre Factory
		this.setiPokemonFactory(PokemonFactory.getInstance());
		
		//les 2 pokemons creer grace à la factory sont dans les tests
	}
}