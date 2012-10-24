package com.LoLCompanionApp.view;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.LoLCompanionApp.ChampionListActivity;
import com.LoLCompanionApp.R;

public class ChampionListView extends ListActivity {

	public EditText searchBar;

	/** The activity */
	private ChampionListActivity mActivity;

	public ChampionListView(ChampionListActivity activity) {
		mActivity = activity;
	}

	public void initialize(LayoutInflater layoutinFlater) {
		mActivity.setContentView(R.layout.activity_champion_list_layout);
		searchBar = (EditText) mActivity.findViewById(R.id.champion_list_search_field);

	}

	public OnFocusChangeListener onFocusChangeListener() {
		return new OnFocusChangeListener() {

			public void onFocusChange(View v, boolean hasFocus) {

			}
		};
	}

}
