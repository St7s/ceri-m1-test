package fr.univavignon.pokedex.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex {
	private List<Pokemon> pokemons;
	private IPokemonMetadataProvider pmp;//decorator
	private IPokemonFactory pf;//decorator
	
	
	public Pokedex(){
		this.setPokemons(new ArrayList<>(256));//si c'est pokemon GO, on juge qu'au départ on peut avoir au max 250 pokemon
		this.setPmp(PokemonMetadataProvider.getInstance());//on recupere le singleton
		this.setPf(PokemonFactory.getInstance());//on recupere le singleton
	}

	
	public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		this.setPokemons(new ArrayList<>(256));//si c'est pokemon GO, on juge qu'au départ on peut avoir au max 250 pokemon
		this.setPmp(metadataProvider);
		this.setPf(pokemonFactory);
	}


	/**
	 * Retrieves and returns the metadata for the pokemon
	 * denoted by the given <tt>index</tt>.
	 * 
	 * @param index Index of the pokemon to retrieve metadata for.
	 * @return Metadata of the pokemon.
	 * @throws PokedexException If the given <tt>index</tt> is not valid.
	 */
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		//Utilisation du singleton PokemonMetadataProvider
		return this.getPmp().getPokemonMetadata(index);
	}

	
	/**
	 * Creates a pokemon instance computing it IVs.
	 * @param index Pokemon index.
	 * @param cp Pokemon CP.
	 * @param hp Pokemon HP.
	 * @param dust Required dust for upgrading pokemon.
	 * @param candy Required candy for upgrading pokemon.
	 * @return Created pokemon instance.
	 */
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		//Utilisation du singleton PokemonFactory
		return this.getPf().createPokemon(index, cp, hp, dust, candy);
	}

	/**
	 * Returns the number of pokemon this pokedex contains.
	 * @return Number of pokemon in this pokedex.
	 */
	@Override
	public int size() {
		return this.getPokemons().size();
	}

	
	/**
	 * Adds the given <tt>pokemon</tt> to this pokedex and returns
	 * it unique index.
	 * @param pokemon Pokemon to add to this pokedex.
	 * @return Index of this pokemon relative to this pokedex.
	 */
	@Override
	public int addPokemon(Pokemon pokemon) {
		this.getPokemons().add(pokemon);
		return this.size()-1;//on retourne l'index relatif
	}


	/**
	 * Locates the pokemon identified by the given <tt>id</tt>.
	 * @param id Unique pokedex relative identifier.
	 * @return Pokemon denoted by the given identifier.
	 * @throws PokedexException If the given <tt>index</tt> is not valid.
	 */
	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		if(id >= this.size() ){
			throw new PokedexException("Le pokemon avec l'id "+id+" n'existe pas dans le pokedex");
		}else {
			//on retourne le pokemon correspondant à l'id
			return this.getPokemons().get(id);
		}
	}

	
	/************************** GETTERS && SETTERS **************************/
	
	/**
	 * Returns an unmodifiable list of all pokemons this pokedex contains.
	 * 
	 * @return Unmodifiable list of all pokemons.
	 */
	@Override
	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}


	/**
	 * Returns an unmodifiable list of all pokemons this pokedex contains.
	 * The list view will be sorted using the given <tt>order</tt>.
	 * 
	 * @param order Comparator instance used for sorting the created view.
	 * @return Sorted unmodifiable list of all pokemons.
	 */
	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		//on creer une nouvelle list en partant de celle existante
		List<Pokemon> pokemonsList = new ArrayList<Pokemon>(this.getPokemons());
		//on tri notre list en fonction du comparator
		Collections.sort(pokemonsList, order);
		//on retourne une liste unmodifiable
		return Collections.unmodifiableList(pokemonsList);
	}


	/**
	 * Setters for list of pokemons
	 * @param pokemons
	 */
	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}


	public IPokemonMetadataProvider getPmp() {
		return pmp;
	}


	public void setPmp(IPokemonMetadataProvider pmp) {
		this.pmp = pmp;
	}


	public IPokemonFactory getPf() {
		return pf;
	}


	public void setPf(IPokemonFactory pf) {
		this.pf = pf;
	}
}