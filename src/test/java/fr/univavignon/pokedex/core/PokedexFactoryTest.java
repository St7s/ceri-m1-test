package fr.univavignon.pokedex.core;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import fr.univavignon.pokedex.api.IPokedexFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;

public class PokedexFactoryTest extends IPokedexFactoryTest {
	
	/**
	 * setup
	 * @throws PokedexException
	 */
	@Before
    public void setUp() throws PokedexException  {
		//on garde nos mock pour IPokemonFactory et IPokemonMetadataProvider
		MockitoAnnotations.initMocks(this);
		
		//on recupere notre Singleton pour notre pokedexFactory
		this.setiPokedexFactory(PokedexFactory.getInstance());
    }

}
