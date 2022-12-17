package com.pp.library_common.model

import com.pp.library_common.extension.update
import com.pp.library_network.eyepetizer.bean.MetroDataBean
import com.pp.library_ui.model.DetailsInfoViewModel

class MetroDataDetailsInfoViewModel : DetailsInfoViewModel() {


    var metro: MetroDataBean? = null
        set(value) {
            field = value

            update(value)
        }

}