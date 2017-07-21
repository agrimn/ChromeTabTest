package io.branch.chrometabstest;

import android.app.Application;

import io.branch.referral.Branch;

/**
 * Created by anigam on 7/21/17.
 */
public class ChromeTabsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Branch.getAutoInstance(this);
    }
}
