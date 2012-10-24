package com.LoLCompanionApp.includes;

public class CounterObject {

	public enum CounterType {
		user, original
	}

	private CounterType type;
	private int championId;
	private int counterId;
	private String tips;
	private String role;
	private String description;

	public CounterObject(int championId, String description, String role,
			String tips, int counterId, CounterType type) {

		this.championId = championId;
		this.counterId = counterId;
		this.description = description;
		this.role = role;
		this.tips = tips;
	}

	public int getChampionId() {
		return championId;
	}

	public String getTips() {
		return tips;
	}

	public int getCounterId() {
		return counterId;
	}

	public String getRole() {
		return role;
	}

	public String getDescription() {
		return description;
	}

	public CounterType getCounterType() {
		return type;
	}

}
