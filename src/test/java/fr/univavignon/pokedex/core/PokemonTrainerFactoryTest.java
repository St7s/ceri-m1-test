package fr.univavignon.pokedex.core;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;


import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	 * Ici lors du build, on recréerera 3 Trainer qui existe deja dont "Stys" qui aura deja un pokemon aquali
	 * @throws PokedexException
	 */
	@Test
    public void test1SerializationSiTrainerExiste() throws PokedexException  {
		//test pour recuperer VALOR trainer qui existe deja
		PokemonTrainer pokTrainerVALOR = getiPokemonTrainerFactory().createTrainer("Stys_A_Serialize", Team.VALOR, PokedexFactory.getInstance());
		assertEquals("Stys_A_Serialize", pokTrainerVALOR.getName());
		assertEquals(1, pokTrainerVALOR.getPokedex().getPokemons().size());//deja un pokemon
		assertEquals("Vaporeon", pokTrainerVALOR.getPokedex().getPokemon(0).getName());
		assertEquals(Team.VALOR, pokTrainerVALOR.getTeam());
		
		//on ajoute un deuxieme pokemon
		Pokemon pok = pokTrainerVALOR.getPokedex().createPokemon(0, 613, 64, 4000, 4);
		pokTrainerVALOR.getPokedex().addPokemon(pok);
		assertEquals(2, pokTrainerVALOR.getPokedex().getPokemons().size());
		assertEquals("Bulbasaur", pokTrainerVALOR.getPokedex().getPokemon(1).getName());
		
		
		//test pour recuperer MYSTIC trainer qui existe deja
		PokemonTrainer pokTrainerMYSTIC = getiPokemonTrainerFactory().createTrainer("Enemy1_A_Serialize", Team.MYSTIC, PokedexFactory.getInstance());
		assertEquals("Enemy1_A_Serialize", pokTrainerMYSTIC.getName());
		assertEquals(0, pokTrainerMYSTIC.getPokedex().getPokemons().size());
		assertEquals(Team.MYSTIC, pokTrainerMYSTIC.getTeam());
		
		//test pour recuperer INSTINCT trainer qui existe deja
		PokemonTrainer pokTrainerINSTINCT = getiPokemonTrainerFactory().createTrainer("Patricia_A_Serialize", Team.INSTINCT, PokedexFactory.getInstance());
		assertEquals("Patricia_A_Serialize", pokTrainerINSTINCT.getName());
		assertEquals(0, pokTrainerINSTINCT.getPokedex().getPokemons().size());
		assertEquals(Team.INSTINCT, pokTrainerINSTINCT.getTeam());
	}
	
	/**
	 * ce test est executé aprés test1SerializationSiTrainerExiste
	 * on verifie que le deuxieme pokemon de "Stys_A_Serialize" a bien était sauvegardé
	 * @throws PokedexException 
	 */
	@Test
	public void test2SerializationPokedex() throws PokedexException{
		
		//test pour recuperer VALOR trainer qui existe deja et qui doit avoir 2 pokemon
		PokemonTrainer pokTrainerVALOR = getiPokemonTrainerFactory().createTrainer("Stys_A_Serialize", Team.VALOR, PokedexFactory.getInstance());
		assertEquals("Stys_A_Serialize", pokTrainerVALOR.getName());
		assertEquals(2, pokTrainerVALOR.getPokedex().getPokemons().size());//2 pokemon
		assertEquals("Vaporeon", pokTrainerVALOR.getPokedex().getPokemon(0).getName());
		assertEquals("Bulbasaur", pokTrainerVALOR.getPokedex().getPokemon(1).getName());
		assertEquals(Team.VALOR, pokTrainerVALOR.getTeam());
	}
}
