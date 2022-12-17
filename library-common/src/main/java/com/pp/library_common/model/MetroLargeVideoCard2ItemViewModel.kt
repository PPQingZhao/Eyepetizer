package com.pp.library_common.model

import android.view.View
import com.pp.library_common.extension.intentToVideoDetails
import com.pp.library_network.eyepetizer.bean.Metro

class MetroLargeVideoCard2ItemViewModel(item: Metro?) :
    MetroLargeVideoCardItemViewModel(item) {

    override fun onVideo(view: View) {
        intentToVideoDetails(resourceId?:0, resourceType)
    }

}