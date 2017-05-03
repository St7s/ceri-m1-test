package fr.univavignon.pokedex.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * 
 * @author adrie
 *
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {

	private static final long serialVersionUID = 6109431017454626938L;

	/**
	 * Instance unique non préinitialisée
	 */
	private static PokemonMetadataProvider INSTANCE = null;
	
	private List<PokemonMetadata> cacheListPokemonMetadata;
	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMetadataProvider.class);
	
	private final String URL = "https://raw.githubusercontent.com/PokemonGo-Enhanced/node-pokemongo-data/master/data.json";
	

	/**
	 * Constructeur privé
	 */
	private PokemonMetadataProvider() {
		this.cacheListPokemonMetadata = new ArrayList<>(256);
		this.generateCachePokemonMetadata();
	}
	
	/**
	 * Point d'accès pour l'instance unique du singleton
	 */
	public static synchronized PokemonMetadataProvider getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PokemonMetadataProvider();
		}
		return INSTANCE;
	}

	/**
	 * On récupere en cache (dans un objet) toutes les données des pokemons
	 */
	private void generateCachePokemonMetadata() {
		String genreJson;
		try (final InputStream in = new URL(URL).openConnection().getInputStream()) {
			genreJson = IOUtils.toString(in, "UTF-8");
			JSONArray genreArray = new JSONArray(genreJson);	
			for (Object object : genreArray) {
				 JSONObject pok =  (JSONObject)object;
				 LOGGER.info("Pokemon n° " + (pok.getInt("PkMn")-1) + " added");
				 this.cacheListPokemonMetadata.add(new PokemonMetadata((pok.getInt("PkMn")-1), pok.getString("Identifier"), pok.getInt("BaseAttack"), pok.getInt("BaseDefense"), pok.getInt("BaseStamina")));		 														
			}
		} catch (IOException e) { e.printStackTrace(); }
	}

	

	/**
	 * on recupere les metadata d'un pokemon en fonction de son id
	 */
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		if(index < 0 || index >= this.cacheListPokemonMetadata.size()){
			throw new PokedexException("l'index "+ index +" ne corresponds à aucun pokemon");	
		}else{
			return this.cacheListPokemonMetadata.get(index);
		}	
	}
	
	/**
	 * méthode readObject, utilisée lors de la sérialization
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		//comme on a rien serialize de ce singleton, on recreer l'instance
		PokemonMetadataProvider.getInstance();
	}
	

	/**
	 * méthode writeObject, utilisée lors de la sérialization
	 * @param oos
	 * @throws IOException
	 */
	private  void writeObject(ObjectOutputStream oos) throws IOException { 
		//on serialize rien car c'est un singleton
	}
}
