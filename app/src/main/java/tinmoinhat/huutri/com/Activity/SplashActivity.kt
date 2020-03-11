package tinmoinhat.huutri.com.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import tinmoinhat.huutri.com.Ads.AdFull
import tinmoinhat.huutri.com.Ads.Common
import tinmoinhat.huutri.com.MainActivity
import tinmoinhat.huutri.com.Model.URLDATA
import tinmoinhat.huutri.com.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MobileAds.initialize(this, resources.getString(R.string.Admod_app_id))
        Common.interstitialAd = AdFull(applicationContext).ad

        //Xoa bien dem bai viet
        xoaCountAd()
        initURL()

        val welcomeThread = object : Thread() {
            override fun run() {
                try {
                    super.run()
                    //startService(new Intent(SplashActivity.this, CheckService.class));
                    sleep(2000)
                } catch (e: Exception) {

                } finally {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        welcomeThread.start()
    }

    private fun xoaCountAd() {
        val preferences = getSharedPreferences("number", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear().apply()
    }

    private fun initURL() {
        val preferences = getSharedPreferences("URL", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("cate", URLDATA.URL_CATE_CHINH)
        editor.putString("detail", URLDATA.URL_DATA_CHINH)
        editor.apply()
    }
}
