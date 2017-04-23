package fr.univavignon.pokedex.api;

import java.io.Serializable;

/**
 * Pokemon POJO.
 * 
 * @author fv
 * @author modify by adrie
 */

public final class Pokemon  extends PokemonMetadata implements Serializable {
	private static final long serialVersionUID = -6844565099843298492L;

	/** Combat Point of the pokemon. **/
	private final int cp;

	/** HP of the pokemon. **/
	private final int hp;

	/** Required dust for upgrading this pokemon. **/
	private final int dust;

	/** Required candy for upgrading this pokemon. **/
	private final int candy;

	/** IV perfection percentage. **/
	private final double iv;
	
	/**
	 * Default constructor.
	 * 
	 * @param index Pokemon index.
	 * @param name Pokemon name.
	 * @param attack Attack level.
	 * @param defense Defense level.
	 * @param stamina Stamina level.
	 * @param cp Pokemon cp.
	 * @param hp Pokemon hp.
	 * @param dust Required dust for upgrading this pokemon.
	 * @param candy Required candy for upgrading this pokemon.
	 * @param iv IV perfection percentage.
	 */
	public Pokemon( final int index,
					final String name,
					final int attack,
					final int defense,
					final int stamina,
					final int cp,
					final int hp,
					final int dust,
					final int candy,
					final double iv) {
		super(index, name, attack, defense, stamina);
		this.cp = cp;
		this.hp = hp;
		this.dust = dust;
		this.candy = candy;
		this.iv = iv;
	}



	/**
	 * Combat Point getter getter.
	 * @return cp
	 */
	public int getCp() {
		return cp;
	}
	
	
	/**
	 *  HP getter.
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}

	
	/**
	 * Dust getter
	 * @return dust
	 */
	public int getDust() {
		return dust;
	}


	/**
	 *  Candy getter.
	 * @return candy
	 */
	public int getCandy() {
		return candy;
	}
	
	
	/**
	 * IV getter.
	 * @return iv
	 */
	public double getIv() {
		return iv;
	}
	
	/**
	 * surchage du toString
	 */
	/*
	@Override
	public String toString(){
		return  "Name:"+this.getName()+"_"+
				"Attack"+this.getAttack()+"_"+
				"Candy:"+this.getCandy()+"_"+
				"Cp:"+this.getCp()+"_"+
				"Defens:"+this.getDefense()+"_"+
				"Dust:"+this.getDust()+"_"+
				"Hp:"+this.getHp()+"_"+
				"Index:"+this.getIndex()+"_"+
				"Iv:"+this.getIv()+"_"+
				"Stamina:"+this.getStamina()+"_";
		
	}*/
	
}
