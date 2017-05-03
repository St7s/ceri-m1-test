package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class IPokemonTrainerFactoryTest {
	
	private PokemonTrainer pokemonTrainerVALOR;
	private PokemonTrainer pokemonTrainerMYSTIC;
	private PokemonTrainer pokemonTrainerINSTINCT;
	
	@Mock private IPokedexFactory iPokedexFactory;
	@Mock private IPokemonTrainerFactory iPokemonTrainerFactory;
	@Mock private IPokedex iPokedexTest;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	
	/**
	 * setup
	 * @throws PokedexException
	 */
	@Before
    public void setUp() throws PokedexException  {
		MockitoAnnotations.initMocks(this);
	
		
		//create trainer VALOR
		setPokemonTrainerVALOR(new PokemonTrainer("Stys", Team.VALOR, getiPokedexTest()));
		when(getiPokemonTrainerFactory().createTrainer("Stys", Team.VALOR, getiPokedexFactory())).thenReturn(getPokemonTrainerVALOR());
		
		//create trainer MYSTIC
		setPokemonTrainerMYSTIC(new PokemonTrainer("Enemy1", Team.MYSTIC, getiPokedexTest()));
		when(getiPokemonTrainerFactory().createTrainer("Enemy1", Team.MYSTIC, getiPokedexFactory())).thenReturn(getPokemonTrainerMYSTIC());
		
		//create trainer INSTINCT
		setPokemonTrainerINSTINCT(new PokemonTrainer("Patricia", Team.INSTINCT, getiPokedexTest()));
		when(getiPokemonTrainerFactory().createTrainer("Patricia", Team.INSTINCT, getiPokedexFactory())).thenReturn(getPokemonTrainerINSTINCT());
    }

	@Test
	public void testCreateNewTrainer() {
		//test for VALOR trainer
		PokemonTrainer pokTrainerVALOR = getiPokemonTrainerFactory().createTrainer("Stys", Team.VALOR, getiPokedexFactory());
		assertEquals("Stys", pokTrainerVALOR.getName());
		assertEquals(0, pokTrainerVALOR.getPokedex().getPokemons().size());
		assertEquals(Team.VALOR, pokTrainerVALOR.getTeam());
		
		//test for MYSTIC trainer
		PokemonTrainer pokTrainerMYSTIC = getiPokemonTrainerFactory().createTrainer("Enemy1", Team.MYSTIC, getiPokedexFactory());
		assertEquals("Enemy1", pokTrainerMYSTIC.getName());
		assertEquals(0, pokTrainerMYSTIC.getPokedex().getPokemons().size());
		assertEquals(Team.MYSTIC, pokTrainerMYSTIC.getTeam());
		
		//test for INSTINCT trainer
		PokemonTrainer pokTrainerINSTINCT = getiPokemonTrainerFactory().createTrainer("Patricia", Team.INSTINCT, getiPokedexFactory());
		assertEquals("Patricia", pokTrainerINSTINCT.getName());
		assertEquals(0, pokTrainerINSTINCT.getPokedex().getPokemons().size());
		assertEquals(Team.INSTINCT, pokTrainerINSTINCT.getTeam());
	}

	public IPokemonTrainerFactory getiPokemonTrainerFactory() {
		return iPokemonTrainerFactory;
	}

	public void setiPokemonTrainerFactory(IPokemonTrainerFactory iPokemonTrainerFactory) {
		this.iPokemonTrainerFactory = iPokemonTrainerFactory;
	}

	public PokemonTrainer getPokemonTrainerVALOR() {
		return pokemonTrainerVALOR;
	}

	public void setPokemonTrainerVALOR(PokemonTrainer pokemonTrainerVALOR) {
		this.pokemonTrainerVALOR = pokemonTrainerVALOR;
	}

	public IPokedex getiPokedexTest() {
		return iPokedexTest;
	}

	public void setiPokedexTest(IPokedex iPokedexTest) {
		this.iPokedexTest = iPokedexTest;
	}

	public PokemonTrainer getPokemonTrainerMYSTIC() {
		return pokemonTrainerMYSTIC;
	}

	public void setPokemonTrainerMYSTIC(PokemonTrainer pokemonTrainerMYSTIC) {
		this.pokemonTrainerMYSTIC = pokemonTrainerMYSTIC;
	}

	public PokemonTrainer getPokemonTrainerINSTINCT() {
		return pokemonTrainerINSTINCT;
	}

	public void setPokemonTrainerINSTINCT(PokemonTrainer pokemonTrainerINSTINCT) {
		this.pokemonTrainerINSTINCT = pokemonTrainerINSTINCT;
	}

	public IPokedexFactory getiPokedexFactory() {
		return iPokedexFactory;
	}

	public void setiPokedexFactory(IPokedexFactory iPokedexFactory) {
		this.iPokedexFactory = iPokedexFactory;
	}
}
