package com.LoLCompanionApp.includes;

public class SkillObject {

	private int rank;
	private int championId;
	private String name;
	private String cost;
	private String cooldown;
	private String iconPath;
	private int range;
	private String effect;
	private String description;
	private char hotKey;

	public SkillObject(int championId, int rank, String name, String cost,
			String cooldown, String iconPath, int range, String effect,
			String description, char hotKey) {

		this.rank = rank;
		this.championId = championId;
		this.name = name;
		this.cost = cost;
		this.cooldown = cooldown;
		this.iconPath = iconPath;
		this.range = range;
		this.effect = effect;
		this.description = description;
		this.hotKey = hotKey;

	}

	public int getRank() {
		return rank;
	}

	public int getChampionId() {
		return championId;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public String getCooldown() {
		return cooldown;
	}

	public String getIconPath() {
		return iconPath;
	}

	public int getRange() {
		return range;
	}

	public String getEffect() {
		return effect;
	}

	public String getDescription() {
		return description;
	}

	public char getHotKey() {
		return hotKey;
	}

}
