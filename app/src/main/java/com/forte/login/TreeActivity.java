package com.forte.login;

import java.util.List;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.forte.carbrowser.database.CarBrowserDataSource;

public class TreeActivity extends ExpandableListActivity {
	private ExpandableListAdapter adapter;
	private CarBrowserDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
		datasource = new CarBrowserDataSource(this);
		datasource.open();
		adapter = new CarListAdapter(this, datasource);
		setListAdapter(adapter);
		registerForContextMenu(getExpandableListView());
		ExpandableListView expListView = (ExpandableListView)findViewById(android.R.id.list);
		expListView.setOnChildClickListener(new OnChildClickListener(){
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent intent = new Intent(TreeActivity.this, FragmentItemActivity.class);
				intent.putExtra("BrandIndex", groupPosition);
				intent.putExtra("ModelIndex", childPosition);
				//intent.putExtras((Parcelable)datasource);
				startActivity(intent);
				//Toast.makeText(TreeActivity.this, groupPosition + " " + childPosition, Toast.LENGTH_SHORT).show();
				return true;
			}
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_tree, menu);
		return true;
	}
}
