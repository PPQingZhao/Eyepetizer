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
import com.pp.mvvm.LifecycleViewModel

class UserViewModel(app: Application) : LifecycleViewModel(app) {
    val icon = ObservableField<String>()
    val nickName = ObservableField<String>()
    val gender = ObservableField<CharSequence>()
    val city = ObservableField<String>()
    val fan = ObservableField<String>("0")
    val follow = ObservableField<String>("0")
    val badge = ObservableField<String>("0")
    val location = ObservableField<String>()

    val userInfo = MutableLiveData<UserInfoBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        UserManager.userModel().observe(owner) {
            it.userInfo().observe(owner) {
                userInfo.value = it

                icon.set(it.avatar.url)
                nickName.set(it.nick)
                gender.set(getApplication<App>().resources.getText(if ("male" == it.gender) R.string.male else R.string.female))
                city.set(it.city)
                fan.set(it.fansCount.toString())
                follow.set(it.followCount.toString())
                badge.set(it.medalCount.toString())
                location.set(it.location)

            }
        }
    }

    fun getNvaTabData(
        uid: Int,
        pageType: String,
        pageLabel: String
    ): LiveData<PagingData<Metro>> {
        return NvaTabRepository.getPagingData(uid, pageType, pageLabel).asLiveData()
    }

}
