package app.mumandroidproject.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.mumandroidproject.R


/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment() {

    object HOLDER {
        val INSTANCE by lazy { HotFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_hot, container, false)
    }

}
