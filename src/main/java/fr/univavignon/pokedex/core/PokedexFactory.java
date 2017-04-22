package fr.univavignon.pokedex.core;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

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
	 * 
	 */
	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		return new Pokedex(metadataProvider, pokemonFactory);
	}

}
