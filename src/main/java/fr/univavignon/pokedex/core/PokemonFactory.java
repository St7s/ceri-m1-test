package fr.univavignon.pokedex.core;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.core.helper.FindIVOnWebWithSelenium;

/**
 * 
 * @author adrie
 *
 */
public class PokemonFactory implements IPokemonFactory, Serializable {
	private static final long serialVersionUID = -3937465193257293713L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonFactory.class);
	private static PokemonFactory INSTANCE;
	
	
	/**
	 * Constructeur privé
	 */
	private PokemonFactory() {
	}

	
	/**
	 * Point d'accès pour l'instance unique du singleton
	 * @return INSTANCE
	 */
	public static synchronized PokemonFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PokemonFactory();
		}
		return INSTANCE;
	}

	/**
	 * Création d'un pokemon
	 * @throws IOException 
	 */
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadataProvider pmp = PokemonMetadataProvider.getInstance();
		PokemonMetadata pm = null;
		Pokemon p = null;
		try {
			pm = pmp.getPokemonMetadata(index);
			FindIVOnWebWithSelenium pfs = new FindIVOnWebWithSelenium();
			int iv = pfs.findIv(pm.getName(), cp, hp, dust); //on cherche l'iv
			p = new Pokemon(index, pm.getName(), pm.getAttack(), pm.getDefense(), pm.getStamina(), cp, hp, dust, candy, iv);
			pfs.quit();
		} catch (PokedexException e) { LOGGER.error(e.getMessage() + "Impossible de créer le pokemon en raison d'un index invalide"); }
		return p;
	}
}
