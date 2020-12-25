

package com.maskyn.fileeditor;

import android.os.Bundle;

import shared.turboeditor.home.MainActivity;

public class HomeActivity extends MainActivity {

    private AdsHelper adsHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: move to firebase-crahslytics
//        if(shared.turboeditor.preferences.PreferenceHelper.getSendErrorReports(this))
//            Fabric.with(this, new Crashlytics());
        // setup the ads
        if(!shared.turboeditor.util.ProCheckUtils.isPro(this))
            adsHelper = new AdsHelper(this);
    }

    @Override
    public boolean showInterstitial() {
        if(adsHelper != null && !shared.turboeditor.util.ProCheckUtils.isPro(this)) {
            adsHelper.displayInterstitial();
            return true;
        }
        else {
            return false;
        }
    }


}
