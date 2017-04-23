package fr.univavignon.pokedex.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMetadataProvider.class);
	private static PokemonTrainerFactory INSTANCE;
	
	
	/**
	 * Constructeur privé
	 */
	private PokemonTrainerFactory() {
	}

	
	/**
	 * Point d'accès pour l'instance unique du singleton
	 * @return INSTANCE
	 */
	public static synchronized PokemonTrainerFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PokemonTrainerFactory();
		}
		return INSTANCE;
	}


	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
	
		System.out.println("Results : "+ 	trainerExist(name, team));
		
		return new PokemonTrainer(name, team, pokedexFactory.createPokedex(PokemonMetadataProvider.getInstance(), PokemonFactory.getInstance()));
	}
	
	private String trainerExist(final String name, final Team team){
		StringBuilder sb = new StringBuilder(".\\src\\main\\ressources\\db\\");
		sb.append(team);
		
		String answer = null;
		System.out.println(sb.toString());
		
		
		//lister les fichiers
		File folder = new File(sb.toString());
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	LOGGER.info("search in db/"+ team + " : " + file.getName());
		        if(file.getName().equals(name)){
		        	LOGGER.info("the trainer EXIST in db/"+ team + " : " + file.getName());
	        		answer = file.getName();
	        		break;//on sort
	        	}
		    }
		}
		return answer;
	}
	
	/*public static void main(String[] args) {
		PokemonTrainerFactory ptf = PokemonTrainerFactory.getInstance();
		PokemonTrainer t = ptf.createTrainer("3", Team.MYSTIC, PokedexFactory.getInstance());
		//t.getPokedex().
		
	}*/

}
