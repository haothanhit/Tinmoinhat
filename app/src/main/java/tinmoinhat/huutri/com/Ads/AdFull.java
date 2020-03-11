package tinmoinhat.huutri.com.Ads;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import tinmoinhat.huutri.com.R;

public class AdFull {
    Context context;
    InterstitialAd interstitialAd;

    public AdFull(final Context context) {
        this.context = context;

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(context.getString(R.string.ad_full));
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Common.interstitialAd = (new AdFull(context)).getAd();
            }
        });
    }

    public InterstitialAd getAd(){
        return interstitialAd;
    }


}
