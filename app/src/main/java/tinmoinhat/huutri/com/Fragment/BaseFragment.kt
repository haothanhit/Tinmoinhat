package tinmoinhat.huutri.com.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}