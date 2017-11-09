package com.forte.login;

import java.util.List;

import com.forte.carbrowser.database.CarBrowserDataSource;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class CarListAdapter extends BaseExpandableListAdapter {

		private final TreeActivity treeActitvity;
		private List<CarBrand> carBrands;
		private Context context;
		private CarBrowserDataSource dataSource;
		
		
		public CarListAdapter(TreeActivity treeActivity, CarBrowserDataSource dataSource) {
			treeActitvity = treeActivity;
			carBrands = dataSource.getAllCarBrands();
			this.context = treeActivity;
			this.dataSource = dataSource;
		}

		public Object getChild(int groupPosition, int childPosition) {
			long carBrandId = carBrands.get(groupPosition).id;
			return dataSource.getCarModels(carBrandId).get(childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			long carBrandId = carBrands.get(groupPosition).id;
			return dataSource.getCarModels(carBrandId).size();
		}

		public TextView getGenericView() {
			// Layout parameters for the ExpandableListView
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 64);

			TextView textView = new TextView(treeActitvity);
			textView.setLayoutParams(lp);
			// Center the text vertically
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView.setPadding(36, 0, 0, 0);
			return textView;
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			TextView textView = getGenericView();
			textView.setText(getChild(groupPosition, childPosition).toString());
			return textView;
		}

		public Object getGroup(int groupPosition) {
			return carBrands.get(groupPosition);
		}

		public int getGroupCount() {
			return carBrands.size();
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		
		public View getGroupView(int groupPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			
			if (convertView == null) {
				LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inf.inflate(R.layout.expandlist_group_item, null);
			}
			
			ViewHolder holder = (ViewHolder) convertView.getTag();

			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}

			CarBrand carBrand = (CarBrand) getGroup(groupPosition);
			
			
			holder.text.setText(carBrand.name);
			//holder.image.setImageDrawable(treeActitvity.getResources().getDrawable(Integer.parseInt(carBrand.icon)));
			holder.image.setImageDrawable(treeActitvity.getResources().getDrawable(Integer.parseInt(carBrand.icon), null));
			return convertView;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		public boolean hasStableIds() {
			return true;
		}

	}