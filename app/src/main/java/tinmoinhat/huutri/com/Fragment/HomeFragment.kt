package tinmoinhat.huutri.com.Fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import tinmoinhat.huutri.com.Adapter.AdapterViewPagerFragment
import tinmoinhat.huutri.com.MainActivity
import tinmoinhat.huutri.com.R

class HomeFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {
    var PAGE_NUMBER = 13

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
        (activity as MainActivity).setSupportActionBar(toolbar)

        //Image vote
        /*imgVote=findViewById(R.id.img_vote_main);
        imgVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=" + MainActivity.this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName())));
                }
            }
        });*/
        //Navigation
        val toggle = ActionBarDrawerToggle(
            activity!!,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        //View Pager
        val adapter =
            AdapterViewPagerFragment(context, childFragmentManager, PAGE_NUMBER)
        viewPager_main.adapter = adapter
        viewPager_main.offscreenPageLimit = PAGE_NUMBER

        tabLayout.setupWithViewPager(viewPager_main)
        //TAB TITLE FONT
        for (i in 0 until PAGE_NUMBER) {
            val tabTitle =
                LayoutInflater.from(context).inflate(R.layout.custom_tab_title, null) as TextView
            tabTitle.text = adapter.getPageTitle(i)
            tabTitle.setTextColor(resources.getColor(R.color.tabTitleColor))
            tabLayout.getTabAt(i)!!.customView = tabTitle
        }
    }

    override fun onResume() {
        super.onResume()
        val preferencesCount = activity!!.getSharedPreferences("retryMain", Context.MODE_PRIVATE)
        val editor = preferencesCount.edit()
        editor.clear().apply()

//        val sharedPreferences = getSharedPreferences("night_mode", Context.MODE_PRIVATE)
//        val isCheck = sharedPreferences.getBoolean("night_mode", false)
//        if (isNightMode != isCheck) {
//            recreate()
//        }
    }


    fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity!!.menuInflater.inflate(R.menu.main, menu)
        /*if(isNightMode){
            menu.getItem(1).setTitle("Chế đô ban ngày");
        }else{
            menu.getItem(1).setTitle("Chế đô ban đêm");
        }*/
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
//            R.id.action_share -> {
//                Toast.makeText(context, "Vui lòng đợi!", Toast.LENGTH_SHORT).show()
//                val sharingIntent = Intent(Intent.ACTION_SEND)
//                sharingIntent.type = "text/plain"
//                val shareBodyText = "Tin mới nhất"
//                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, url);
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText + "\nhttp://play.google.com/store/apps/details?id=" + this.packageName)
//                startActivity(Intent.createChooser(sharingIntent, "Chia sẻ ứng dụng:"))
//            }
            /*case R.id.action_darkmode:
                boolean check=!isNightMode;
                SharedPreferences sharedPreferences = getSharedPreferences("night_mode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode", check);
                editor.commit();
                editor.apply();
                recreate();
                break;*/
//            R.id.action_info -> startActivity(Intent(this@MainActivity, InfoActivity::class.java))
//            R.id.action_tin_danh_dau ->
//                //Toast.makeText(this, "tin đánh dấu", Toast.LENGTH_SHORT).show();
//                startActivity(Intent(this@MainActivity, DanhDauActivity::class.java))
//            R.id.action_vote -> {
//                val uri = Uri.parse("market://details?id=" + this@MainActivity.packageName)
//                val goToMarket = Intent(Intent.ACTION_VIEW, uri)
//                // To count with Play market backstack, After pressing back button,
//                // to taken back to our application, we need to add following flags to intent.
//                goToMarket.addFlags(
//                    Intent.FLAG_ACTIVITY_NO_HISTORY or
//                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
//                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//                try {
//                    startActivity(goToMarket)
//                } catch (e: ActivityNotFoundException) {
//                    startActivity(
//                        Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse("http://play.google.com/store/apps/details?id=" + this@MainActivity.packageName))
//                    )
//                }
//
//            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        val tab: TabLayout.Tab?
        when (id) {
            R.id.nav_tinNoiBat -> tab = tabLayout.getTabAt(0)
            R.id.nav_phapLuat -> tab = tabLayout.getTabAt(1)
            R.id.nav_theGioi -> tab = tabLayout.getTabAt(2)
            R.id.nav_theThao -> tab = tabLayout.getTabAt(3)
            R.id.nav_xaHoi -> tab = tabLayout.getTabAt(4)
            R.id.nav_vanHoa -> tab = tabLayout.getTabAt(5)
            R.id.nav_kinhTe -> tab = tabLayout.getTabAt(6)
            R.id.nav_congNghe -> tab = tabLayout.getTabAt(7)
            R.id.nav_giaiTri -> tab = tabLayout.getTabAt(8)
            R.id.nav_giaoDuc -> tab = tabLayout.getTabAt(9)
            R.id.nav_sucKhoe -> tab = tabLayout.getTabAt(10)
            R.id.nav_nhaDat -> tab = tabLayout.getTabAt(11)
            R.id.nav_xeCo -> tab = tabLayout.getTabAt(12)
            else -> tab = tabLayout.getTabAt(0)
        }

        tab!!.select()
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
