package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexFactoryTest {
	
	@Mock private IPokemonFactory pokemonFactory;
	@Mock private IPokemonMetadataProvider iPokemonMetadataProvider;
	@Mock private IPokedexFactory iPokedexFactory;
	@Mock private IPokedex iPokedex;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	
	/**
	 * setup
	 * @throws PokedexException
	 */
	@Before
    public void setUp() throws PokedexException  {
		MockitoAnnotations.initMocks(this);
		
		when(iPokedexFactory.createPokedex(iPokemonMetadataProvider, pokemonFactory)).thenReturn(iPokedex);
    }

	/**
	 * Test de creation de l'objet du pokedex
	 */
	@Test
	public void testCreatePokedex()  {
		//verify object is not null
		assertNotNull(iPokedexFactory.createPokedex(iPokemonMetadataProvider, pokemonFactory));
	}

	/****************************** GETTERS && SETTERS ******************************/
	public IPokemonFactory getPokemonFactory() {
		return pokemonFactory;
	}

	public void setPokemonFactory(IPokemonFactory pokemonFactory) {
		this.pokemonFactory = pokemonFactory;
	}

	public IPokemonMetadataProvider getiPokemonMetadataProvider() {
		return iPokemonMetadataProvider;
	}

	public void setiPokemonMetadataProvider(IPokemonMetadataProvider iPokemonMetadataProvider) {
		this.iPokemonMetadataProvider = iPokemonMetadataProvider;
	}

	public IPokedexFactory getiPokedexFactory() {
		return iPokedexFactory;
	}

	public void setiPokedexFactory(IPokedexFactory iPokedexFactory) {
		this.iPokedexFactory = iPokedexFactory;
	}

	public IPokedex getiPokedex() {
		return iPokedex;
	}

	public void setiPokedex(IPokedex iPokedex) {
		this.iPokedex = iPokedex;
	}
}
