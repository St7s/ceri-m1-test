package fr.univavignon.pokedex.api;

import java.io.Serializable;

/**
 * Trainer POJO.
 * 
 * @author fv
 * @author modify by adrie
 */
public class PokemonTrainer implements Serializable {
	private static final long serialVersionUID = 5676039693652862951L;

	/** Trainer name. **/
	private final String name;

	/** Trainer team. **/
	private final Team team;
	
	/** Trainer pokedex. **/
	private final IPokedex pokedex;
	
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
		return pokedex;
	}
	
}
