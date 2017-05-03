package fr.univavignon.pokedex.core;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;



public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {
	
	
	/**
	 * setup
	 * @throws PokedexException
	 */
	@Before
    public void setUp() throws PokedexException  {
		//on set une pokemonTrainerFactory
		setiPokemonTrainerFactory(PokemonTrainerFactory.getInstance());
		
		//on set une pokedexFactory
		setiPokedexFactory(PokedexFactory.getInstance());
		
		}
	
	/**
	 * Ici lors du build, on recréerera 3 Trainer qui existe deja
	 * @throws PokedexException
	 */
	@Test
    public void testSerializationSiTrainerExiste() throws PokedexException  {
		//test for VALOR trainer
		PokemonTrainer pokTrainerVALOR = getiPokemonTrainerFactory().createTrainer("Stys_A_Serialize", Team.VALOR, PokedexFactory.getInstance());
		assertEquals("Stys_A_Serialize", pokTrainerVALOR.getName());
		assertEquals(0, pokTrainerVALOR.getPokedex().getPokemons().size());
		assertEquals(Team.VALOR, pokTrainerVALOR.getTeam());
		
		//test for MYSTIC trainer
		PokemonTrainer pokTrainerMYSTIC = getiPokemonTrainerFactory().createTrainer("Enemy1_A_Serialize", Team.MYSTIC, PokedexFactory.getInstance());
		assertEquals("Enemy1_A_Serialize", pokTrainerMYSTIC.getName());
		assertEquals(0, pokTrainerMYSTIC.getPokedex().getPokemons().size());
		assertEquals(Team.MYSTIC, pokTrainerMYSTIC.getTeam());
		
		//test for INSTINCT trainer
		PokemonTrainer pokTrainerINSTINCT = getiPokemonTrainerFactory().createTrainer("Patricia_A_Serialize", Team.INSTINCT, PokedexFactory.getInstance());
		assertEquals("Patricia_A_Serialize", pokTrainerINSTINCT.getName());
		assertEquals(0, pokTrainerINSTINCT.getPokedex().getPokemons().size());
		assertEquals(Team.INSTINCT, pokTrainerINSTINCT.getTeam());
	}

}
