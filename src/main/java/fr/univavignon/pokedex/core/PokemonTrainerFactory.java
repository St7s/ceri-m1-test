package fr.univavignon.pokedex.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonTrainerFactory.class);
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
		try {
			return trainerExist(name, team, pokedexFactory);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private PokemonTrainer trainerExist(final String name, final Team team, final IPokedexFactory pokedexFactory) throws FileNotFoundException, ClassNotFoundException, IOException{
		StringBuilder sb = new StringBuilder(".\\src\\main\\ressources\\db\\");
		sb.append(team);
		sb.append("\\");
		sb.append(name);
		sb.append(".ser");
		
		LOGGER.info("we must find : " + sb.toString());
		
		File f = new File(sb.toString());
		//si il existe
		if (f.exists()){
			LOGGER.info("the trainer EXIST in " + f.getAbsolutePath());
			return deserializePokemonTrainer(f);
		} else {
			LOGGER.info("the trainer doesn't EXIST in " + f.getAbsolutePath());
			
			//on le creer
			PokemonTrainer p = new PokemonTrainer(name, team, pokedexFactory.createPokedex(PokemonMetadataProvider.getInstance(), PokemonFactory.getInstance()));
			
			 // ouverture d'un flux sur un fichier
			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(f)) ;
					
			 // sérialization de l'objet (on le sauvegarde)
			oos.writeObject(p) ;
			return p;
		}	
		
	}
	
	private PokemonTrainer deserializePokemonTrainer(final File fichier) throws FileNotFoundException, IOException, ClassNotFoundException{
		 // ouverture d'un flux sur un fichier
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
				
		 // désérialization de l'objet
		return (PokemonTrainer)ois.readObject() ;
	
	}
	
}
