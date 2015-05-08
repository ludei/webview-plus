/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.webkit;

import java.util.ArrayList;
import java.util.List;

import org.chromium.content_public.browser.NavigationHistory;

import com.jsaiyan.Unsafe;

import android.os.Build;

/**
 * WebView Chromium implementation of WebBackForwardList. Simple immutable
 * wrapper around NavigationHistory.
 */
public class LudeiWebBackForwardList extends WebBackForwardList {

	private static final long serialVersionUID = 1L;
	
	private List<LudeiWebHistoryItem> mHistroryItemList;
    private int mCurrentIndex;
    
    LudeiWebBackForwardList(NavigationHistory nav_history) {
        mCurrentIndex = nav_history.getCurrentEntryIndex();
        mHistroryItemList = new ArrayList<LudeiWebHistoryItem>(nav_history.getEntryCount());
        for (int i = 0; i < nav_history.getEntryCount(); ++i) {
            mHistroryItemList.add(new LudeiWebHistoryItem(nav_history.getEntryAtIndex(i)));
        }
    }
    
    public static LudeiWebBackForwardList newInstance(NavigationHistory nav_history) {
    	LudeiWebBackForwardList instance = null;
    	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
	    	Unsafe unsafe = Unsafe.instance();
	    	instance = (LudeiWebBackForwardList) unsafe.allocateObject(LudeiWebBackForwardList.class);
	    	instance.init(nav_history);
    	
    	} else {
    		instance = new LudeiWebBackForwardList(nav_history);
    	}
    	
		return instance;
    }
    
    private void init(NavigationHistory nav_history) {
    	mCurrentIndex = nav_history.getCurrentEntryIndex();
        mHistroryItemList = new ArrayList<LudeiWebHistoryItem>(nav_history.getEntryCount());
        for (int i = 0; i < nav_history.getEntryCount(); ++i) {
        	LudeiWebHistoryItem item = LudeiWebHistoryItem.newInstance(nav_history.getEntryAtIndex(i));
            mHistroryItemList.add(item);
        }
    }
    
    /**
     * See {@link android.webkit.WebBackForwardList#getCurrentItem}.
     */
    @Override
    public synchronized WebHistoryItem getCurrentItem() {
        if (getSize() == 0) {
            return null;
        } else {
            return getItemAtIndex(getCurrentIndex());
        }
    }
    /**
     * See {@link android.webkit.WebBackForwardList#getCurrentIndex}.
     */
    @Override
    public synchronized int getCurrentIndex() {
        return mCurrentIndex;
    }
    /**
     * See {@link android.webkit.WebBackForwardList#getItemAtIndex}.
     */
    @Override
    public synchronized WebHistoryItem getItemAtIndex(int index) {
        if (index < 0 || index >= getSize()) {
            return null;
        } else {
            return mHistroryItemList.get(index);
        }
    }
    /**
     * See {@link android.webkit.WebBackForwardList#getSize}.
     */
    @Override
    public synchronized int getSize() {
        return mHistroryItemList.size();
    }
    // Clone constructor.
    private LudeiWebBackForwardList(List<LudeiWebHistoryItem> list,
                                       int currentIndex) {
        mHistroryItemList = list;
        mCurrentIndex = currentIndex;
    }
    /**
     * See {@link android.webkit.WebBackForwardList#clone}.
     */
    @Override
    protected synchronized LudeiWebBackForwardList clone() {
        List<LudeiWebHistoryItem> list =
                new ArrayList<LudeiWebHistoryItem>(getSize());
        for (int i = 0; i < getSize(); ++i) {
            list.add(mHistroryItemList.get(i).clone());
        }
        return new LudeiWebBackForwardList(list, mCurrentIndex);
    }
}