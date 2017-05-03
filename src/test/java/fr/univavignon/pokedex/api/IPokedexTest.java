package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

public class IPokedexTest  {
	
	private int size;
	private int indice;
	
	public Pokemon pokemonBulbizarre;
	private Pokemon pokemonAquali;
	
	private Pokemon pokemonBulbizarreInit = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
	private Pokemon pokemonAqualiInit = new Pokemon(133, "Vaporeon", 186, 168, 260, 1984, 172, 3500, 4, 69);
	
	@Mock private IPokedex iPokedexTest;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	
	
	/**
	 * setup
	 * @throws PokedexException
	 */

	@Before
	public void setUp() throws PokedexException {	
		MockitoAnnotations.initMocks(this);

		
		//create 2 returns when createPokemon on pokedex
		when(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemonBulbizarreInit);
		when(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4)).thenReturn(pokemonAqualiInit);

		when(getiPokedexTest().getPokemon(1)).thenReturn(pokemonBulbizarreInit);
		when(getiPokedexTest().getPokemon(0)).thenReturn(pokemonAqualiInit);
		when(getiPokedexTest().getPokemon(999)).thenThrow(new PokedexException("No pokemon find at -1"));
		  
		
		//create answer for the size
		size = 0;
		Answer<Object> answerSize = new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				return size;
			}
		};
		//create answer for the indice
		indice = 0;
		Answer<Object> answerIndice = new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				return indice;
			}
		};

		
		// create Answer for size() method
		when(getiPokedexTest().size()).thenAnswer(answerSize);

		// create Answer for addPokemon() method with indice
		when(getiPokedexTest().addPokemon(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4))).thenAnswer(answerIndice);
		when(getiPokedexTest().addPokemon(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4))).thenAnswer(answerIndice);
		
		
		
		//create return for getPokemonMetadata
		when(getiPokedexTest().getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));
		when(getiPokedexTest().getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Vaporeon", 186, 168, 260));
		
	
		
		//create list for NAME
		ArrayList<Pokemon> pokemonListByName = new ArrayList<Pokemon>();
		pokemonListByName.add(getiPokedexTest().getPokemon(1));
		pokemonListByName.add(getiPokedexTest().getPokemon(0));
		//create list for INDEX
		
		ArrayList<Pokemon> pokemonListByIndex = pokemonListByName;//on fait pointer vers une liste identique car c'est le meme ordre
		//list for CP
		ArrayList<Pokemon> pokemonListByCp = pokemonListByName;//on fait pointer vers une liste identiquecar c'est le meme ordre
		
		
		when(getiPokedexTest().getPokemons(PokemonComparators.NAME)).thenReturn(pokemonListByName);
		
		when(getiPokedexTest().getPokemons()).thenReturn(pokemonListByIndex);//On retorune celle la de base
		when(getiPokedexTest().getPokemons(PokemonComparators.INDEX)).thenReturn(pokemonListByIndex);
		
		when(getiPokedexTest().getPokemons(PokemonComparators.CP)).thenReturn(pokemonListByCp);

		
		
	}



	/**
	 * Test de l'ajout de 2 pokemon au pokedex
	 */
	@Test
	public void testAddPokemonIndiceEtSize() {
		// before size=0
		assertEquals(0, getiPokedexTest().size());
		int indice0 = addNewPokemon(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4));
		assertEquals(0, indice0); //index = 0
		// before size=1
		assertEquals(1, getiPokedexTest().size());
		int indice1 = addNewPokemon(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4));
		assertEquals(1, indice1); //index = 0
		// before size=2
		assertEquals(2, getiPokedexTest().size());
		
	}
	
	@Test
	public void testGetPokemonMetadata() throws PokedexException{
		
		PokemonMetadata p1 = getiPokedexTest().getPokemonMetadata(0);
		
		assertEquals(126, p1.getAttack());
		assertEquals(126, p1.getDefense());
		assertEquals(0, p1.getIndex());
		assertEquals("Bulbasaur", p1.getName());
		assertEquals(90, p1.getStamina());

		
		
		PokemonMetadata p2 = getiPokedexTest().getPokemonMetadata(133);
		
		assertEquals(186, p2.getAttack());
		assertEquals(168, p2.getDefense());
		assertEquals(133, p2.getIndex());
		assertEquals("Vaporeon", p2.getName());
		assertEquals(260, p2.getStamina());
	
		
	}
	
	
	
	@Test
	public void testGetPokemon() throws PokedexException {
		
		int indice0 = addNewPokemon(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4));
		pokemonAquali = getiPokedexTest().getPokemon(indice0);
		
		runAssertOnPokemonAquali();//limit duplicate code	
		
		
		int indice1 = addNewPokemon(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4));
		
		pokemonBulbizarre = getiPokedexTest().getPokemon(indice1);
		
		runAssertOnPokemonBulbizarre();//limit duplicate code	
	}
	
	@Test(expected=PokedexException.class)
	public void testPokedexException() throws PokedexException   {
		getiPokedexTest().getPokemon(999);	
	}
	
	@Test
	public void testGetPokemons() throws PokedexException {
		// on ajoute les 2 pokemons
		addNewPokemon(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4));
		addNewPokemon(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4));
		
		List<Pokemon> list = getiPokedexTest().getPokemons();
		
		pokemonBulbizarre = list.get(0);
		
		runAssertOnPokemonBulbizarre();//limit duplicate code	
		
		
		pokemonAquali = list.get(1);
		
		runAssertOnPokemonAquali();//limit duplicate code	
	}
	
	@Test
	public void testGetPokemonsComparator() throws PokedexException {
		// on ajoute les 2 pokemons mais cette fois-ci on inverse pour avoir des cas de comparaisons
		
		int indice0 = addNewPokemon(getiPokedexTest().createPokemon(133, 1984, 172, 3500, 4));
		int indice1 = addNewPokemon(getiPokedexTest().createPokemon(0, 613, 64, 4000, 4));
		
		
		pokemonAquali = getiPokedexTest().getPokemon(indice0);
		pokemonBulbizarre = getiPokedexTest().getPokemon(indice1);
		
		
		List<Pokemon> listName = getiPokedexTest().getPokemons(PokemonComparators.NAME);
		List<Pokemon> listIndex = getiPokedexTest().getPokemons(PokemonComparators.INDEX);
		List<Pokemon> listCp = getiPokedexTest().getPokemons(PokemonComparators.CP);
		
		//verify size of lists
		assertEquals(listName.size(), listIndex.size());
		assertEquals(listName.size(), listCp.size());
		assertEquals(listIndex.size(), listCp.size());
	

		//verify Bulbasaur sort in the first
		assertEquals("Bulbasaur", listName.get(0).getName());
		assertEquals(0, listIndex.get(0).getIndex());
		assertEquals(613, listCp.get(0).getCp());
		
		//verify Vaporeon sort in the second
		assertEquals("Vaporeon", listName.get(1).getName());
		assertEquals(133, listIndex.get(1).getIndex());
		assertEquals(1984, listCp.get(1).getCp());	
	}
	


	
	
	/******************************** HELPER ****************************************/
	
	/**
	 * Méthode pour ajouter un pokemon, changer la taille et l'indice attendu pour les mocks
	 * Method for add Pokemon and change the size and the indice
	 * Rappel, entre chaque méthode, on renitialise
	 * 
	 * @param p
	 * @return
	 */
	public int addNewPokemon(Pokemon p) {
		int id = getiPokedexTest().addPokemon(p);
		size++; //inutile quand c'est pas mocker
		indice++;//inutile quand c'est pas mocker
		return id;
	}
	
	
	/**
	 * limit duplicate code and test getter on aquali generated
	 */
	public void runAssertOnPokemonAquali(){
		assertEquals(186, pokemonAquali.getAttack());
		assertEquals(168, pokemonAquali.getDefense());
		assertEquals(133, pokemonAquali.getIndex());
		assertEquals("Vaporeon", pokemonAquali.getName());
		assertEquals(260, pokemonAquali.getStamina());
		assertEquals(69, pokemonAquali.getIv(), 0.001);
		assertEquals(4, pokemonAquali.getCandy());
		assertEquals(1984, pokemonAquali.getCp());
		assertEquals(3500, pokemonAquali.getDust());
		assertEquals(172, pokemonAquali.getHp());
	}
	
	/**
	 * limit duplicate code and test getter on Bulbizarre generated
	 */
	public void runAssertOnPokemonBulbizarre(){
		assertEquals(126, pokemonBulbizarre.getAttack());
		assertEquals(126, pokemonBulbizarre.getDefense());
		assertEquals(0, pokemonBulbizarre.getIndex());
		assertEquals("Bulbasaur", pokemonBulbizarre.getName());
		assertEquals(90, pokemonBulbizarre.getStamina());
		assertEquals(56, pokemonBulbizarre.getIv(), 0.001);
		assertEquals(4, pokemonBulbizarre.getCandy());
		assertEquals(613, pokemonBulbizarre.getCp());
		assertEquals(4000, pokemonBulbizarre.getDust());
		assertEquals(64, pokemonBulbizarre.getHp());
	}

	

	
	
	
	/****************************** GETTERS && SETTERS ******************************/
	
	/**
	 * 
	 * @return iPokedexTest
	 */
	public IPokedex getiPokedexTest() {
		return iPokedexTest;
	}

	/**
	 * 
	 * @param iPokedexTest
	 */
	public void setiPokedexTest(IPokedex iPokedexTest) {
		this.iPokedexTest = iPokedexTest;
	}


	
	/**
	 * 
	 * @return
	 */
	public Pokemon getPokemonBulbizarreInit() {
		return pokemonBulbizarreInit;
	}


	/**
	 * 
	 * @param pokemonBulbizarreInit
	 */
	public void setPokemonBulbizarreInit(Pokemon pokemonBulbizarreInit) {
		this.pokemonBulbizarreInit = pokemonBulbizarreInit;
	}


	/**
	 * 
	 * @return
	 */
	public Pokemon getPokemonAqualiInit() {
		return pokemonAqualiInit;
	}


	/**
	 * 
	 * @param pokemonAqualiInit
	 */
	public void setPokemonAqualiInit(Pokemon pokemonAqualiInit) {
		this.pokemonAqualiInit = pokemonAqualiInit;
	}

}
