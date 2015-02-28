/*
 * Copyright (C) 2014-2015 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer.ui.scene;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hippo.ehviewer.R;
import com.hippo.ehviewer.data.ListUrlBuilder;
import com.hippo.ehviewer.ui.fragment.GalleryListFragment;
import com.hippo.ehviewer.ui.fragment.SearchFragment;
import com.hippo.scene.Scene;
import com.hippo.scene.StageActivity;
import com.hippo.widget.Appbar;

import org.jetbrains.annotations.NotNull;

public class GalleryListScene extends Scene implements SearchFragment.OnSearchListener {

    private StageActivity mActivity;
    private Resources mResources;

    private Appbar mAppbar;
    private ViewPager mViewPager;

    private GalleryListPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = getStageActivity();
        mResources = mActivity.getResources();
    }

    @Override
    public View onCreateSceneView(Bundle savedInstanceState) {
        View sceneView = mActivity.getLayoutInflater().inflate(R.layout.scene_gallery_list, null);
        mAppbar = (Appbar) sceneView.findViewById(R.id.appbar);
        mViewPager = (ViewPager) sceneView.findViewById(R.id.viewPager);

        mAdapter = new GalleryListPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mAppbar.setTitle(mResources.getString(R.string.app_name));

        SearchFragment.setScene(this);
        SearchFragment.setOnSearchListener(this);

        return sceneView;
    }

    @Override
    public void onResume() {
        // Empty
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        SearchFragment.setScene(null);
        SearchFragment.setOnSearchListener(null);
    }

    @Override
    public void onRequestSearch(ListUrlBuilder lub) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NotNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class GalleryListPagerAdapter extends FragmentPagerAdapter {

        public GalleryListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int i) {
            if (i == 0) {
                SearchFragment fragment = new SearchFragment();
                return fragment;
            } else {
                GalleryListFragment fragment = new GalleryListFragment();
                return fragment;
            }
        }
    }
}
