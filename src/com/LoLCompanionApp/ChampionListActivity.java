/**
 * 
 */
package com.LoLCompanionApp;

import com.LoLCompanionApp.view.ChampionListView;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author V
 *
 */
public class ChampionListActivity extends Activity {
	
	ChampionListView mChampionListView = new ChampionListView(this);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mChampionListView.initialize();
	}
	
}
