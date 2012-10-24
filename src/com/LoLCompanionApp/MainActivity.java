package com.LoLCompanionApp;

<<<<<<< HEAD
=======
import com.LoLCompanionApp.view.MainActivityView;
>>>>>>> 67fb5b9d95ba8c990e4d78911a27e04bff076dcf
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.LoLCompanionApp.view.MainActivityView;

public class MainActivity extends Activity implements OnClickListener {

	private MainActivityView mMainActivityView = new MainActivityView(this);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivityView.initialize();
	}

	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case (R.id.main_activity_button_champ_list):
			intent = new Intent(this, ChampionListActivity.class);
		
			break;
			
		case (R.id.main_activity_button_patch_notes):

			break;

		case (R.id.main_activity_button_about):
			break;
		}
		startActivity(intent);
	}

}