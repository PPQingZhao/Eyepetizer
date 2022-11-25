package com.pp.module_user.ui

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.paging.PagingData
import com.pp.library_common.app.App
import com.pp.library_network.eyepetizer.bean.Metro
import com.pp.library_network.eyepetizer.bean.UserInfoBean
import com.pp.library_ui.R
import com.pp.module_user.manager.UserManager
import com.pp.module_user.repositoy.NvaTabRepository
import com.pp.library_base.base.ThemeViewModel
import kotlinx.coroutines.flow.Flow

class UserViewModel(app: Application) : ThemeViewModel(app) {
    val icon = ObservableField<String>()
    val nickName = ObservableField<String>()
    val gender = ObservableField<CharSequence>()
    val city = ObservableField<String>()
    val fan = ObservableField<String>("0")
    val follow = ObservableField<String>("0")
    val badge = ObservableField<String>("0")
    val location = ObservableField<String>()

    val userInfo = MutableLiveData<UserInfoBean?>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        userInfo.observe(owner) {

            icon.set(it?.avatar?.url)
            nickName.set(it?.nick)
            if (null == it) {
                gender.set("")
            } else {
                gender.set(getApplication<App>().resources.getText(if ("male" == it?.gender) R.string.male else R.string.female))
            }
            city.set(it?.city)
            fan.set(it?.fansCount.toString())
            follow.set(it?.followCount.toString())
            badge.set(it?.medalCount.toString())
            location.set(it?.location)
        }


        UserManager.userModel().observe(owner) {

            if (it == null) {
                userInfo.value = null
                return@observe
            }

            it.userInfo().observe(owner) {
                userInfo.value = it
            }
        }
    }

    fun getNvaTabData(
        uid: Int,
        pageType: String,
        pageLabel: String
    ): Flow<PagingData<Metro>> {
        return NvaTabRepository.getPagingData(uid, pageType, pageLabel)
    }

}
