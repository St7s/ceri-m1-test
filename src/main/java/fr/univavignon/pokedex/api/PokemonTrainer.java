package fr.univavignon.pokedex.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import org.mockito.internal.util.MockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.univavignon.pokedex.core.Pokedex;

/**
 * Trainer POJO.
 * 
 * @author fv
 * @author modify by adrie
 */
public class PokemonTrainer implements Observer, Serializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(PokemonTrainer.class);
	private static final long serialVersionUID = 5676039693652862951L;

	/** Trainer name. **/
	private final String name;

	/** Trainer team. **/
	private final Team team;
	
	/** Trainer pokedex. **/
	private final IPokedex pokedex;
	
	/** Permet de savoir si le pokedex a deja était mis en observable ou non **/
	private boolean setObs;
	
	/**
	 * Default constructor.
	 * 
	 * @param name Trainer name.
	 * @param team Trainer team.
	 * @param pokedex Trainer pokedex.
	 */
	public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
		this.name = name;
		this.team = team;
		this.pokedex = pokedex;
		this.setSetObs(false);
	}
	
	/**
	 * Name getter
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Team getter
	 * @return team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Pokedex getter
	 * @return pokedex
	 */
	public IPokedex getPokedex() {
		//si on est pas deja abone que le podedex n'est pas un mock
		if(!isSetObs() && !new MockUtil().isMock(this.pokedex))
			((Pokedex)this.pokedex).addObserver(this);
		return pokedex;
	}

	private boolean isSetObs() {
		return setObs;
	}

	private void setSetObs(boolean setObs) {
		this.setObs = setObs;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Un nouveau pokemon a était ajoute, on sauvegarde");
		LOGGER.debug("Un nouveau pokemon a était ajoute, on sauvegarde");
		
		StringBuilder sb = new StringBuilder(".");
		sb.append(File.separator);
		sb.append("src");
		sb.append(File.separator);
		sb.append("main");
		sb.append(File.separator);
		sb.append("ressources");
		sb.append(File.separator);
		sb.append("db"+File.separator);
		sb.append(team);
		sb.append(File.separator);
		sb.append(name);
		sb.append(".ser");
		
		File f = new File(sb.toString());
		
		 // ouverture d'un flux sur un fichier
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			 // sérialization de l'objet (on le sauvegarde)
			oos.writeObject(this) ;
		} catch (IOException e) { e.printStackTrace(); }
	}
}
