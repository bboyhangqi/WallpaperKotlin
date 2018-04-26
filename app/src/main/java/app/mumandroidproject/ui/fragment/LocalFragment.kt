package app.mumandroidproject.ui.fragment


import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import app.mumandroidproject.R
import app.mumandroidproject.bean.LocalImageItem
import app.mumandroidproject.constant.Constant
import app.mumandroidproject.helper.LocalHelper
import app.mumandroidproject.helper.SharePerferenceHelper
import app.mumandroidproject.presenter.LocalPresenter
import app.mumandroidproject.ui.adpter.CollectAdapter
import app.mumandroidproject.ui.adpter.DownloadAdapter
import app.mumandroidproject.view.LocalView
import kotlinx.android.synthetic.main.fragment_local.*


/**
 * A simple [Fragment] subclass.
 */
class LocalFragment : Fragment(), LocalView {

    private val TAG = "LocalFragment"
    private val localPresenter by lazy { LocalPresenter(this) }

    private var downPage: LinearLayout? = null
    private var collectPage: LinearLayout? = null
    private var recyclerViewDownload: RecyclerView? = null
    private var recyclerViewCollected: RecyclerView? = null


    private val intentFilter = IntentFilter()
    private var broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG,"zhq.debug onReceive action: ${intent?.action}")
            if (intent?.action == Constant.BROADCAST_ACTION.ACTION_IMAGE_DOWNLOADED) {
                updateDownLoadedImages()
            } else if (intent?.action == Constant.BROADCAST_ACTION.ACTION_IMAGE_COLLECTED) {
                updateCollectImages()
            } else if (intent?.action == Constant.BROADCAST_ACTION.ACTION_IMAGE_UNCOLLECTED) {
                updateCollectImages()
            }
        }
    }


    object HOLDER {
        val INSTANCE by lazy { LocalFragment() }
    }

    companion object {
        val instance = HOLDER.INSTANCE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_local, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_downloaded.setOnClickListener {
            bottom_line1.visibility = View.VISIBLE
            bottom_line2.visibility = View.INVISIBLE
            showDownloadedPage()
        }
        tv_collected.setOnClickListener {
            bottom_line1.visibility = View.INVISIBLE
            bottom_line2.visibility = View.VISIBLE
            showCollectedPage()
        }
        showDownloadedPage()
        intentFilter.addAction(Constant.BROADCAST_ACTION.ACTION_IMAGE_DOWNLOADED)
        intentFilter.addAction(Constant.BROADCAST_ACTION.ACTION_IMAGE_COLLECTED)
        activity?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(broadcastReceiver)
    }


    private fun showDownloadedPage() {
        if (content_layout.findViewById<LinearLayout>(R.id.download_layout) == null) {
            downPage = LayoutInflater.from(this.context).inflate(R.layout.download_page, content_layout, false) as LinearLayout
            recyclerViewDownload = downPage?.findViewById(R.id.rc_download)
            recyclerViewDownload?.layoutManager = LinearLayoutManager(this.context)
            content_layout.addView(downPage)
        }
        downPage?.visibility = View.VISIBLE
        collectPage?.visibility = View.GONE
        updateDownLoadedImages()
    }

    private fun showCollectedPage() {
        if (content_layout.findViewById<LinearLayout>(R.id.collected_layout) == null) {
            collectPage = LayoutInflater.from(this.context).inflate(R.layout.collected_page, content_layout, false) as LinearLayout
            recyclerViewCollected = collectPage?.findViewById(R.id.rc_collect)
            recyclerViewCollected?.layoutManager = LinearLayoutManager(this.context)
            content_layout.addView(collectPage)
        }
        downPage?.visibility = View.GONE
        collectPage?.visibility = View.VISIBLE
        updateCollectImages()
    }

    private fun updateDownLoadedImages() {
        val imagePaths = SharePerferenceHelper.getDownloadWallpapers(this.context!!)
        imagePaths.forEach { Log.d(TAG, "updateDownLoadedImages path: $it") }
        recyclerViewDownload?.adapter = DownloadAdapter(imagePaths, activity!!.windowManager, localPresenter)
    }

    private fun updateCollectImages() {
        Log.d(TAG,"zhq.debug    updateCollectImages")
        val imagePaths = SharePerferenceHelper.getCollectWallpapers(this.context!!)
        imagePaths.forEach { Log.d(TAG, "path: $it") }
        recyclerViewCollected?.adapter = CollectAdapter(imagePaths, activity!!.windowManager, localPresenter)
    }


    override fun onDownloadImageDeleted() {
        updateDownLoadedImages()
    }

    override fun onCollectedItemDeleted() {
        updateCollectImages()
    }


}
