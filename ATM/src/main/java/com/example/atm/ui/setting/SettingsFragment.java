package com.example.atm.ui.setting;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.atm.MainActivity;
import com.example.atm.R;


public class SettingsFragment extends Fragment implements
		OnItemSelectedListener {

	private ArrayAdapter<String> refreshAdapter;
	private ArrayAdapter<String> autoRefreshAdapter;
	private Spinner spinnerLocation;
	private Spinner spinnerSiteStatus;
	private Switch airconSwitch;
	private String switch_on;
	private SharedPreferences preferences;
	private Editor editor;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_settings, container, false);

		initView(view);

		preferences = getActivity().getSharedPreferences("switch",
				Context.MODE_PRIVATE);
		editor = preferences.edit();

		switch_on = preferences.getString("on", "");
		// Default on
		if ("on".equalsIgnoreCase(switch_on)) {
			airconSwitch.setChecked(true);
		} else {
			airconSwitch.setChecked(false);
		}

		refreshAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, getDateSource());

		spinnerLocation.setTag(R.id.spinner_location);
		spinnerLocation.setAdapter(refreshAdapter);
		spinnerLocation.setOnItemSelectedListener(this);

		spinnerSiteStatus.setTag(R.id.spinner_siteStatus);
		autoRefreshAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item,
				autoRefreshDateSource());

		spinnerSiteStatus.setAdapter(autoRefreshAdapter);
		spinnerSiteStatus.setOnItemSelectedListener(this);

		airconSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					editor.putString("on", "on");
					editor.commit();

				} else {
					editor.putString("on", "off");
					editor.commit();

				}
			}
		});


		return view;
	}

	public void initView(View view) {
		spinnerLocation = (Spinner) view.findViewById(R.id.spinner_location);
		spinnerSiteStatus = (Spinner) view
				.findViewById(R.id.spinner_siteStatus);
		airconSwitch = (Switch) view.findViewById(R.id.airconSwitch);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onResume() {
		super.onResume();
		MainActivity.setActionBarTitle(getString(R.string.title_settings),null);

		SharedPreferences preferences = getActivity().getSharedPreferences(
				"spinner", Context.MODE_PRIVATE);
		int ite = preferences.getInt("position", 0);
		spinnerLocation.setSelection(ite, true);

		SharedPreferences autoRefreshPreferences = getActivity()
				.getSharedPreferences("autoFlashControl", Context.MODE_PRIVATE);
		int check = autoRefreshPreferences.getInt("position", 0);
		spinnerSiteStatus.setSelection(check, true);

	}

	public List<String> getDateSource() {
		List<String> list = new ArrayList<String>();
		list.add("1  minute");
		list.add("5  minutes");
		list.add("10 minutes");
		list.add("15 minutes");
		list.add("30 minutes");
		list.add("1  hour");
		return list;
	}

	public List<String> autoRefreshDateSource() {
		List<String> list = new ArrayList<String>();
		list.add("off");
		list.add("30 seconds");
		list.add("1  minute");
		list.add("3  minutes");
		list.add("5  minutes");
		list.add("10 minutes");
		return list;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		switch (parent.getId()) {
		case R.id.spinner_location:
			changeLocationRefreshRate(position );
			break;
		case R.id.spinner_siteStatus:
			changeSiteStatusRefreshRate(position);
			break;
		default:
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	private void changeLocationRefreshRate(int position) {
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"spinner", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("position", position);
		
		switch (position) {
		case 0:
			editor.putInt("time", 1 * 60);		//1 minute
			break;

		case 1:
			editor.putInt("time", 5 * 60); 	//5 minutes
			break;
		case 2:
			editor.putInt("time", 10 * 60);	//10 minutes	
			break;
		case 3:
			editor.putInt("time", 15 * 60);	//15 minutes	
			break;
		case 4:
			editor.putInt("time", 30 * 60);	//30 minutes	
			break;
		case 5:
			editor.putInt("time", 60 * 60);	//60 minutes	
			break;
		default:
			break;
		}
		editor.commit();

	}

	private void changeSiteStatusRefreshRate(int position) {
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"autoFlashControl", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("position", position);
		switch (position) {
		case 0:
			editor.putInt("time", 0 * 60);		//OFF
			break;

		case 1:
			editor.putInt("time", 30); 			//30 seconds
			break;
		case 2:
			editor.putInt("time", 1 * 60);	  	//1 minutes	
			break;
		case 3:
			editor.putInt("time", 3 * 60);		//3 minutes	
			break;
		case 4:
			editor.putInt("time", 5 * 60);	//5 minutes	
			break;
		case 5:
			editor.putInt("time", 10 * 60);	//5 minutes	
			break;
			
		default:
			break;
		}
		editor.commit();

	}

}
