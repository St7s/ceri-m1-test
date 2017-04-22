package fr.univavignon.pokedex.core.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.univavignon.pokedex.core.PokedexFactoryTest;
import fr.univavignon.pokedex.core.PokedexTest;
import fr.univavignon.pokedex.core.PokemonFactoryTest;
import fr.univavignon.pokedex.core.PokemonMetadataProviderTest;

@RunWith(Suite.class)

/**
 * Test suite with mock
 * @author adrie
 *
 */
@Suite.SuiteClasses({PokemonMetadataProviderTest.class,
					PokemonFactoryTest.class,
					PokedexTest.class,
					PokedexFactoryTest.class
					
})

public class TestSuiteWithMockImpl { }
