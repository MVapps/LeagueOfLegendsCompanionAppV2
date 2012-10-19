package com.LoLCompanionApp.view;

import com.LoLCompanionApp.ChampionListActivity;
import com.LoLCompanionApp.R;

public class ChampionListView {

	/** The activity */
	private ChampionListActivity mActivity;

	public ChampionListView(ChampionListActivity activity) {
		mActivity = activity;
	}

	public void initialize() {
		mActivity.setContentView(R.layout.activity_champion_list_layout);
		
	}

}
