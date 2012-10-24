package com.LoLCompanionApp.includes;

public class ChampionObject extends BaseObject {

	private String title;
	private String tags;
	private String description;

	private int healthBase;
	private int healthLevel;
	private int attackLevel;
	private int attackBase;
	private int range;

	private int moveSpeed;
	private int armorBase;
	private int armorLevel;
	private int manaBase;
	private int manaLevel;
	private int criticalChangeBase;
	private int criticalChangeLevel;
	private int manaRegenBase;
	private int manaRegenLevel;
	private int healthRegenBase;
	private int healthRegenLevel;
	private int magicResistBase;
	private int magicResistLevel;

	private int ratingDefense;
	private int ratingMagic;
	private int ratingDifficulty;
	private int ratingAttack;

	public ChampionObject(int id, String name, String iconPath, int price,
			String title, String tags, String description, int healthBase,
			int healthLevel, int attackLevel, int attackBase, int range,
			int moveSpeed, int armorBase, int armorLevel, int manaBase,
			int manaLevel, int criticalChangeBase, int criticalChangeLevel,
			int manaRegenBase, int manaRegenLevel, int healthRegenBase,
			int healthRegenLevel, int magicResistBase, int magicResistLevel,
			int ratingDefense, int ratingMagic, int ratingDifficulty,
			int ratingAttack) {

		super(id, name, iconPath, price);

		this.title = title;
		this.tags = tags;
		this.description = description;

		this.healthBase = healthBase;
		this.healthLevel = healthLevel;
		this.attackLevel = attackLevel;
		this.attackBase = attackBase;
		this.range = range;

		this.moveSpeed = moveSpeed;
		this.armorBase = armorBase;
		this.armorLevel = armorLevel;
		this.manaBase = manaBase;
		this.manaLevel = manaLevel;
		this.criticalChangeBase = criticalChangeBase;
		this.criticalChangeLevel = criticalChangeLevel;
		this.manaRegenBase = manaRegenBase;
		this.manaRegenLevel = manaRegenLevel;
		this.healthRegenBase = healthRegenBase;
		this.healthRegenLevel = healthRegenLevel;
		this.magicResistBase = magicResistBase;
		this.magicResistLevel = magicResistLevel;

		this.ratingDefense = ratingDefense;
		this.ratingMagic = ratingMagic;
		this.ratingDifficulty = ratingDifficulty;
		this.ratingAttack = ratingAttack;
	}

	public String getTitle() {
		return title;
	}

	public String getTags() {
		return tags;
	}

	public String getLore() {
		return description;
	}

	public int getHealthBase() {
		return healthBase;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public int getAttackLevel() {
		return attackLevel;
	}

	public int getAttackBase() {
		return attackBase;
	}

	public int getRange() {
		return range;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public int getArmorBase() {
		return armorBase;
	}

	public int getArmorLevel() {
		return armorLevel;
	}

	public int getManaBase() {
		return manaBase;
	}

	public int getManaLevel() {
		return manaLevel;
	}

	public int getCriticalChangeBase() {
		return criticalChangeBase;
	}

	public int getCriticalChangeLevel() {
		return criticalChangeLevel;
	}

	public int getManaRegenBase() {
		return manaRegenBase;
	}

	public int getManaRegenLevel() {
		return manaRegenLevel;
	}

	public int getHealthRegenBase() {
		return  healthRegenBase;
	}

	public int getHealthRegenLevel() {
		return healthRegenLevel;
	}

	public int getMagicResistBase() {
		return magicResistBase;
	}

	public int getMagicResistLevel() {
		return magicResistLevel;
	}

	public int getRatingDefense() {
		return ratingDefense;
	}

	public int getRatingMagic() {
		return ratingMagic;
	}

	public int getRatingDifficulty() {
		return ratingDifficulty;
	}

	public int getRatingAttack() {
		return ratingAttack;
	}

}
