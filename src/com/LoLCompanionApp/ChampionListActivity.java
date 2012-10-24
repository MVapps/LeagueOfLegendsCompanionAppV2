/**
 * 
 */
package com.LoLCompanionApp;

import com.LoLCompanionApp.view.ChampionListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * @author V
 *
 */
public class ChampionListActivity extends Activity {
	
	private ChampionListView mChampionListView = new ChampionListView(this);
	
	private LayoutInflater layoutInflater;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		mChampionListView.initialize(layoutInflater);
		
	}
	
}
