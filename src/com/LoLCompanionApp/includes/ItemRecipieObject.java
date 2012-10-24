package com.LoLCompanionApp.includes;

public class ItemRecipieObject {

	private BaseObject createdItem;
	private BaseObject[] requiredItems;

	public ItemRecipieObject(BaseObject created, BaseObject[] required) {
		this.createdItem = created;
		this.requiredItems = required;
	}

	public BaseObject getCreatedItem() {
		return createdItem;
	}

	public BaseObject[] getRequiredItems() {
		return requiredItems;
	}

}
