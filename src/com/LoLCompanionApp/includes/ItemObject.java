package com.LoLCompanionApp.includes;

public class ItemObject extends BaseObject {

	private String description;

	private int flatHPPoolMod;
	private int flatMPPoolMod;
	private int percentHPPoolMod;
	private int percentMPPoolMod;
	private int flatHPRegenMod;

	private int percentHPRegenMod;
	private int flatMPRegenMod;
	private int percentMPRegenMod;
	private int flatArmorMod;
	private int percentArmorMod;
	private int flatAttackDamageMod;
	private int percentAttackDamageMod;
	private int flatAbilityPowerMod;
	private int percentAbilityPowerMod;
	private int flatMovementSpeedMod;
	private int percentMovementSpeedMod;
	private int flatAttackSpeedMod;
	private int percentAttackSpeedMod;
	private int flatDodgeMod;

	private int percentDodgeMod;
	private int flatCritChanceMod;
	private int percentCritChanceMod;
	private int flatCritDamageMod;
	private int percentCritDamageMod;
	private int flatMagicResistMod;
	private int percentMagicResistMod;
	private int flatEXPBonus;
	private int percentEXPBonus;

	public ItemObject(int id, String name, String iconPath, int price,
			String description, int flatHPPoolMod, int flatMPPoolMod,
			int percentHPPoolMod, int percentMPPoolMod, int flatHPRegenMod,
			int percentHPRegenMod, int flatMPRegenMod, int percentMPRegenMod,
			int flatArmorMod, int percentArmorMod, int flatAttackDamageMod,
			int percentAttackDamageMod, int flatAbilityPowerMod,
			int percentAbilityPowerMod, int flatMovementSpeedMod,
			int percentMovementSpeedMod, int flatAttackSpeedMod,
			int percentAttackSpeedMod, int flatDodgeMod, int percentDodgeMod,
			int flatCritChanceMod, int percentCritChanceMod,
			int flatCritDamageMod, int percentCritDamageMod,
			int flatMagicResistMod, int percentMagicResistMod,
			int flatEXPBonus, int percentEXPBonus) {

		super(id, name, iconPath, price);

		this.description = description;

		this.flatHPPoolMod = flatHPPoolMod;
		this.flatMPPoolMod = flatMPPoolMod;
		this.percentHPPoolMod = percentHPPoolMod;
		this.percentMPPoolMod = percentMPPoolMod;
		this.flatHPRegenMod = flatHPRegenMod;

		this.percentHPRegenMod = percentHPRegenMod;
		this.flatMPRegenMod = flatMPRegenMod;
		this.percentMPRegenMod = percentMPRegenMod;
		this.flatArmorMod = flatArmorMod;
		this.percentArmorMod = percentArmorMod;
		this.flatAttackDamageMod = flatAttackDamageMod;
		this.percentAttackDamageMod = percentAttackDamageMod;
		this.flatAbilityPowerMod = flatAbilityPowerMod;
		this.percentAbilityPowerMod = percentAbilityPowerMod;
		this.flatMovementSpeedMod = flatMovementSpeedMod;
		this.percentMovementSpeedMod = percentMovementSpeedMod;
		this.flatAttackSpeedMod = flatAttackSpeedMod;
		this.percentAttackSpeedMod = percentAttackSpeedMod;
		this.flatDodgeMod = flatDodgeMod;

		this.percentDodgeMod = percentDodgeMod;
		this.flatCritChanceMod = flatCritChanceMod;
		this.percentCritChanceMod = percentCritChanceMod;
		this.flatCritDamageMod = flatCritDamageMod;
	}

	public String getDescription() {
		return description;
	}

	public int getFlatHPPoolMod() {
		return flatHPPoolMod;
	}

	public int getflatMPPoolMod() {
		return flatMPPoolMod;
	}

	public int getpercentHPPoolMod() {
		return percentHPPoolMod;
	}

	public int getpercentMPPoolMod() {
		return percentMPPoolMod;
	}

	public int getflatHPRegenMod() {
		return flatHPRegenMod;
	}

	public int getpercentHPRegenMod() {
		return percentHPRegenMod;
	}

	public int getflatMPRegenMod() {
		return flatMPRegenMod;
	}

	public int getpercentMPRegenMod() {
		return percentMPRegenMod;
	}

	public int getflatArmorMod() {
		return flatArmorMod;
	}

	public int getpercentArmorMod() {
		return percentArmorMod;
	}

	public int getflatAttackDamageMod() {
		return flatAttackDamageMod;
	}

	public int getpercentAttackDamageMod() {
		return percentAttackDamageMod;
	}

	public int getflatAbilityPowerMod() {
		return flatAbilityPowerMod;
	}

	public int getpercentAbilityPowerMod() {
		return percentAbilityPowerMod;
	}

	public int getflatMovementSpeedMod() {
		return flatMovementSpeedMod;
	}

	public int getpercentMovementSpeedMod() {
		return percentMovementSpeedMod;
	}

	public int getflatAttackSpeedMod() {
		return flatAttackSpeedMod;
	}

	public int getpercentAttackSpeedMod() {
		return percentAttackSpeedMod;
	}

	public int getflatDodgeMod() {
		return flatDodgeMod;
	}

	public int getpercentDodgeMod() {
		return percentDodgeMod;
	}

	public int getflatCritChanceMod() {
		return flatCritChanceMod;
	}

	public int getpercentCritChanceMod() {
		return percentCritChanceMod;
	}

	public int getflatCritDamageMod() {
		return flatCritDamageMod;
	}

	public int getpercentCritDamageMod() {
		return percentCritDamageMod;
	}

	public int getflatMagicResistMod() {
		return flatMagicResistMod;
	}

	public int getpercentMagicResistMod() {
		return percentMagicResistMod;
	}

	public int getflatEXPBonus() {
		return flatEXPBonus;
	}

	public int getpercentEXPBonus() {
		return percentEXPBonus;
	}

}
