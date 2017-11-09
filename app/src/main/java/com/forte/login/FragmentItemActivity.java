package com.forte.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
i
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentItemActivity extends FragmentActivity {

    CarPagerAdapter carPagerAdapter;

    ViewPager myViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_item);

        Bundle extras = getIntent().getExtras();
        int carBrandId = extras.getInt("BrandIndex");

        getSupportActionBar().setIcon(Integer.parseInt(CarBrandsCollection.CAR_BRANDS[carBrandId].icon));
        getSupportActionBar().setTitle(CarBrandsCollection.CAR_BRANDS[carBrandId].name);

        carPagerAdapter = new CarPagerAdapter(getSupportFragmentManager(), extras.getInt("BrandIndex"));
        myViewPager = (ViewPager) findViewById(R.id.pager);
        myViewPager.setOffscreenPageLimit(0);
        myViewPager.setAdapter(carPagerAdapter);
        myViewPager.setCurrentItem(extras.getInt("ModelIndex"), true);
        myViewPager.destroyDrawingCache();


    }

    public static class CarPagerAdapter extends FragmentStatePagerAdapter {
        int brandIndex;

        public CarPagerAdapter(FragmentManager fm, int brandIndex) {
            super(fm);
            this.brandIndex = brandIndex;
        }

        @Override
        public int getCount() {
            return CarBrandsCollection.CAR_BRANDS[brandIndex].models.length;
        }

        @Override
        public Fragment getItem(int position) {
            return CarTabHostFragment.newInstance(brandIndex, position);
        }
    }

    public static class CarDetailsItemFragment extends Fragment {

        int brandIndex;
        int modelIndex;
        int subModelIndex;

        static CarDetailsItemFragment newInstance(int brandIndex, int modelIndex, int subModelIndex) {
            CarDetailsItemFragment detailsItemFragment = new CarDetailsItemFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("brandIndex", brandIndex);
            args.putInt("modelIndex", modelIndex);
            args.putInt("subModelIndex", subModelIndex);
            detailsItemFragment.setArguments(args);

            return detailsItemFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            brandIndex = getArguments() != null ? getArguments().getInt("brandIndex") : 1;
            modelIndex = getArguments() != null ? getArguments().getInt("modelIndex") : 1;
            subModelIndex = getArguments() != null ? getArguments().getInt("subModelIndex") : 1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(R.layout.car_details_item_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            View header = view.findViewById(R.id.carDetailsHeader);
            CarBrand carBrand = CarBrandsCollection.CAR_BRANDS[brandIndex];
            CarModel carModel = carBrand.models[modelIndex];
            SubModel subModel = carModel.subModels[subModelIndex];

            ((TextView) header).setText(subModel.header);
            View details = view.findViewById(R.id.carDetailsDescription);
            ((TextView) details).setText(subModel.description);
            View classification = view.findViewById(R.id.carClassification);
            ((TextView) classification).setText(subModel.classification);
            View image = view.findViewById(R.id.carDetailsImage);
            ((ImageView) image).setImageDrawable(view.getResources().getDrawable(Integer.parseInt(subModel.image)));
            view.destroyDrawingCache();
            view.refreshDrawableState();
            view = null;

        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

    }

    public static class CarTabHostFragment extends Fragment {
        private FragmentTabHost carTabHost;

        int brandIndex;
        int modelIndex;

        static CarTabHostFragment newInstance(int brandIndex, int modelIndex) {
            CarTabHostFragment tabFragment = new CarTabHostFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("brandIndex", brandIndex);
            args.putInt("modelIndex", modelIndex);
            tabFragment.setArguments(args);

            return tabFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            brandIndex = getArguments() != null ? getArguments().getInt("brandIndex") : 1;
            modelIndex = getArguments() != null ? getArguments().getInt("modelIndex") : 1;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            carTabHost = new FragmentTabHost(getActivity());
            carTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_tabhost);

            for (int i = 0; i < CarBrandsCollection.CAR_BRANDS[brandIndex].models[modelIndex].subModels.length; i++) {

                Bundle args2 = new Bundle();
                args2.putInt("brandIndex", brandIndex);
                args2.putInt("modelIndex", modelIndex);
                args2.putInt("subModelIndex", i);

                SubModel subModel = CarBrandsCollection.CAR_BRANDS[brandIndex].models[modelIndex].subModels[i];

                carTabHost.addTab(carTabHost.newTabSpec(subModel.tabHeader).setIndicator(subModel.tabHeader),
                        CarDetailsItemFragment.class, args2);
            }

            return carTabHost;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            carTabHost = null;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            carTabHost = null;
        }
    }

}
