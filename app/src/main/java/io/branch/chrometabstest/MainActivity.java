package io.branch.chrometabstest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();

        final TextView linkData = (TextView) findViewById(R.id.linkText);
        final RelativeLayout screen = (RelativeLayout) findViewById(R.id.mainscreen);

        screen.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                //Launch Chrome tabs
                String url = "https://agrimn.github.io/WebViewTest/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
            }
        });

        Branch branch = Branch.getInstance();
        branch.initSession(new Branch.BranchReferralInitListener(){
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    linkData.setText(referringParams.toString());
                } else {
                    linkData.setText(error.toString());
                    Log.i("MyApp", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
