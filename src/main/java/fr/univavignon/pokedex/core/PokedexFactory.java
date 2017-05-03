package fr.univavignon.pokedex.core;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * 
 * @author adrie
 *
 */
public class PokedexFactory implements IPokedexFactory {
	
	private static PokedexFactory INSTANCE;
	
	/**
	 * Constructeur privé
	 */
	private PokedexFactory() {
	}
	
	/**
	 * Point d'accès pour l'instance unique du singleton
	 * @return INSTANCE
	 */
	public static synchronized PokedexFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PokedexFactory();
		}
		return INSTANCE;
	}


	/**
	 * Creates a new pokedex instance using the given 
	 * <tt>metadataProvider</tt> and <tt>pokemonFactory</tt>. 
	 * 
	 * @param metadataProvider Metadata provider the created pokedex will use.
	 * @param pokemonFactory Pokemon factory the created pokedex will use.
	 * @return Created pokedex instance.
	 */
	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		return new Pokedex(metadataProvider, pokemonFactory);//on retourne un pokedex
	}

}
