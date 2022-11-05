package com.pp.module_search.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.pp.library_common.datastore.dataStore
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.module_search.R
import com.pp.module_search.adapter.SearchAdapter
import com.pp.module_search.databinding.*
import com.pp.module_search.model.SearchItemModel
import com.pp.mvvm.LifecycleActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.Search.activity_search)
class SearchActivity : LifecycleActivity<ActivitySearchBinding, SearchViewModel>() {
    override val mBinding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    companion object {
        private const val TAG = "SearchActivity"

        private const val TYPE_HISTORY = "type_history"
        private const val TYPE_HISTORY_TITLE = "type_history_title"
        private const val TYPE_HOT_QUERIES = "type_hot_queries"
        private const val TYPE_HOT_QUERIES_TITLE = "type_hot_queries_title"

        private const val KEY_SEARCH_HISTORY = "search_history"
    }

    private var mAdapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSearchView()
        initRecyclerView()
        initData()
    }


    private fun initRecyclerView() {
        val layoutManager = FlexboxLayoutManager(baseContext)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START

        mBinding.recyclerTag.layoutManager = layoutManager

        val typeHistory = 0
        val typeHistoryTitle = 1
        val typeHot = 2
        val typeHotTitle = 3
        mAdapter = SearchAdapter().apply {
            addBindingItem(DefaultViewBindingItem<SearchItemModel>(
                typeHistory,
                { it?.type == TYPE_HISTORY },
                { parent ->
                    ItemHistoryBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))

            addBindingItem(DefaultViewBindingItem<SearchItemModel>(
                typeHistoryTitle,
                { it?.type == TYPE_HISTORY_TITLE },
                { parent ->
                    ItemHistoryTitleBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))

            addBindingItem(DefaultViewBindingItem<SearchItemModel>(
                typeHot,
                { it?.type == TYPE_HOT_QUERIES },
                { parent ->
                    ItemHotQueriesBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))

            addBindingItem(DefaultViewBindingItem<SearchItemModel>(
                typeHotTitle,
                { it?.type == TYPE_HOT_QUERIES_TITLE },
                { parent ->
                    ItemHotQueriesTitleBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))
        }

        mBinding.recyclerTag.adapter = mAdapter
    }

    private val mList = mutableListOf<SearchItemModel>()
    private val hotList: MutableList<SearchItemModel> by lazy {
        mutableListOf<SearchItemModel>()
    }
    private val historyList: MutableList<SearchItemModel> by lazy {
        mutableListOf<SearchItemModel>()
    }
    private val historyTitle by lazy {
        SearchItemModel(
            TYPE_HISTORY_TITLE,
            getString(R.string.search_history)
        )
    }
    private val hotTitle by lazy {
        SearchItemModel(
            TYPE_HOT_QUERIES_TITLE,
            getString(R.string.search_recommend)
        )
    }

    private fun initData() {
        lifecycleScope.launch {

            try {
                baseContext.dataStore.data.collect {
                    it.asMap().forEach { entry ->
                        Log.e(TAG, "collect: ${entry.key.name}   ${entry.value.toString()}")
                        if (entry.key.name == KEY_SEARCH_HISTORY) {
                            val value = entry.value.toString()
                            val items = value.split(";").filter { s ->
                                s.isNotBlank()
                            }
                            mList.clear()
                            historyList.clear()
                            historyList.add(historyTitle)
                            val size = if (items.size > 5) 5 else items.size
                            for (i in 0 until size) {
                                historyList.add(SearchItemModel(TYPE_HISTORY, items[i]))
                            }
                            mList.addAll(historyList)
                            mList.addAll(hotList)
                            mAdapter?.setDataList(mList)
                        }

                        Log.e(TAG, "collect: ${entry.key.name}   end")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "collect dataStore: ${e.message}")
            }

        }

        lifecycleScope.launch {
            hotList.add(hotTitle)
            try {
                val response = mViewModel.getHotQueries()
                if (response.code == 0) {
                    Log.e(TAG, "collect: response success 00 ")
                    response.result.itemList.forEach {
                        val item = SearchItemModel(TYPE_HOT_QUERIES, it)
                        hotList.add(item)
                    }
                    val last = mList.size
                    mList.addAll(last, hotList)
                    mAdapter?.setDataList(mList)
                    Log.e(TAG, "collect: response success 11 ")
                }
            } catch (e: Exception) {
                Log.e(TAG, "getHotQueries err: ${e.message}")
            }

        }


    }

    private fun initSearchView() {
        mBinding.searchView.apply {
            isIconified = false

            setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.e(TAG, "onQueryTextSubmit: $query")
                    query?.let {
                        save(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.e(TAG, "onQueryTextChange: $newText")
                    return false
                }

            })

            setOnCloseListener(object : OnCloseListener {
                override fun onClose(): Boolean {

                    return true
                }
            })

        }
    }


    private val searchKey = stringPreferencesKey(KEY_SEARCH_HISTORY)

    private fun save(searchText: String) {
        lifecycleScope.launch {
            try {
                baseContext.dataStore.edit {
                    val old = it[searchKey] ?: ""

                    if (!old.contains(searchText, false)) {
                        var oldItem = ""
                        val items = old.split(";").filter { s ->
                            s.isNotBlank()
                        }.filterIndexed { index, s ->
                            index <=3
                        }.forEach {
                            oldItem += "${it};"
                        }

                        it[searchKey] = "${searchText};${old}"
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "save err: ${e.message}")
            }
        }

    }
}