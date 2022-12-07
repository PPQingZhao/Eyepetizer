package com.pp.library_common.extension

import com.pp.library_network.eyepetizer.EyepetizerService2
import com.pp.library_network.eyepetizer.bean.MetroDataBean


fun MetroDataBean.isVideo(): Boolean {
    return resourceType == EyepetizerService2.MetroType.ResourceType.pgc_video
            || resourceType == EyepetizerService2.MetroType.ResourceType.ugc_video
}