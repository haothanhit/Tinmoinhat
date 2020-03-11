package tinmoinhat.huutri.com

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import tinmoinhat.huutri.com.Activity.BaseActivity
import tinmoinhat.huutri.com.Fragment.CashOutFragment
import tinmoinhat.huutri.com.Fragment.HomeFragment
import tinmoinhat.huutri.com.Fragment.InviteFragment
import tinmoinhat.huutri.com.Fragment.MeFragment


class MainActivity : BaseActivity() {

    private val TAG_FRAGMENT_ONE = "HomeFragment"
    private val TAG_FRAGMENT_TWO = "InviteFragment"
    private val TAG_FRAGMENT_THREE = "CashOutFragment"
    private val TAG_FRAGMENT_FOUR = "MeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.startusColor)
        }

        setContentView(R.layout.activity_main)
        replaceFragment(R.id.container, HomeFragment(), false)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    private val mOnNavigationItemSelectedListener =
            object : BottomNavigationView.OnNavigationItemSelectedListener {

                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.getItemId()) {
                        R.id.navigation_home -> {
                            var isCreate = false
                            var fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_ONE)
                            if (fragment == null) {
                                fragment = HomeFragment()
                                isCreate = true
                            }
                            replaceFragmentwithTag(fragment, isCreate, TAG_FRAGMENT_ONE)
                            return true
                        }
                        R.id.navigation_invite -> {
                            var isCreate = false
                            var fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_TWO)
                            if (fragment == null) {
                                fragment = InviteFragment()
                                isCreate = true
                            }
                            replaceFragmentwithTag(fragment, isCreate, TAG_FRAGMENT_TWO)

                            return true
                        }
                        R.id.navigation_cash_out -> {
                            var isCreate = false

                            var fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_THREE)
                            if (fragment == null) {
                                fragment = CashOutFragment()
                                isCreate = true

                            }
                            replaceFragmentwithTag(fragment, isCreate, TAG_FRAGMENT_THREE)


                            return true
                        }
                        R.id.navigation_me -> {
                            var isCreate = false

                            var fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_FOUR)
                            if (fragment == null) {
                                fragment = MeFragment()
                                isCreate = true

                            }
                            replaceFragmentwithTag(fragment, isCreate, TAG_FRAGMENT_FOUR)

                            return true
                        }
                    }
                    return false
                }
            }

    /**
     * add fragment has tag
     */
    private fun replaceFragmentwithTag(fragment: Fragment, isCreate: Boolean, tag: String) {
        if (fragment != currentFragment) {
            if (isCreate) {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.container, fragment, tag)
                        .commit()

            } else {
                supportFragmentManager
                        .beginTransaction().show(fragment).commitNow()
            }
            when (tag) {
                TAG_FRAGMENT_ONE -> {
                    hideFragmentwithTag(TAG_FRAGMENT_TWO)
                    hideFragmentwithTag(TAG_FRAGMENT_THREE)
                    hideFragmentwithTag(TAG_FRAGMENT_FOUR)
                }
                TAG_FRAGMENT_TWO -> {
                    hideFragmentwithTag(TAG_FRAGMENT_ONE)
                    hideFragmentwithTag(TAG_FRAGMENT_THREE)
                    hideFragmentwithTag(TAG_FRAGMENT_FOUR)
                }
                TAG_FRAGMENT_THREE -> {
                    hideFragmentwithTag(TAG_FRAGMENT_TWO)
                    hideFragmentwithTag(TAG_FRAGMENT_ONE)
                    hideFragmentwithTag(TAG_FRAGMENT_FOUR)
                }
                TAG_FRAGMENT_FOUR -> {
                    hideFragmentwithTag(TAG_FRAGMENT_TWO)
                    hideFragmentwithTag(TAG_FRAGMENT_THREE)
                    hideFragmentwithTag(TAG_FRAGMENT_ONE)
                }
            }

            currentFragment = fragment
        }
    }

    /**
     * hide fragment with tag
     */
    private fun hideFragmentwithTag(tag: String) {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction().hide(fragment).commitNow()
        }
    }


}
