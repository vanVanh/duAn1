package vn.edu.poly.project_android_1.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.poly.project_android_1.fragment.CartFragment;
import vn.edu.poly.project_android_1.fragment.FavouriteFragment;
import vn.edu.poly.project_android_1.fragment.HomeFragment;
import vn.edu.poly.project_android_1.fragment.SearchFragment;
import vn.edu.poly.project_android_1.fragment.UserFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = CartFragment.newInstance();
                break;
            case 2:
                fragment = FavouriteFragment.newInstance();
                break;
            case 3:
                fragment = SearchFragment.newInstance();
                break;
            case 4:
                fragment = UserFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
