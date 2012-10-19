package com.LoLCompanionApp.view;

import android.widget.Button;

import com.LoLCompanionApp.MainActivity;
import com.LoLCompanionApp.R;

public class MainActivityView {

	/** The activity */
	private MainActivity mActivity;

	public MainActivityView(MainActivity activity) {
		mActivity = activity;
	}

	public void initialize() {
		mActivity.setContentView(R.layout.activity_main_layout);

		((Button) mActivity.findViewById(R.id.main_activity_button_champ_list))
				.setOnClickListener(mActivity);
		((Button) mActivity.findViewById(R.id.main_activity_button_patch_notes))
				.setOnClickListener(mActivity);
		((Button) mActivity.findViewById(R.id.main_activity_button_about))
				.setOnClickListener(mActivity);

	}

}
