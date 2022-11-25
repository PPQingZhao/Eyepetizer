package com.pp.module_search.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.pp.library_base.base.ThemeActivity
import com.pp.library_common.datastore.dataStore
import com.pp.library_common.result.Result
import com.pp.library_router_service.services.RouterPath
import com.pp.library_ui.adapter.DefaultViewBindingItem
import com.pp.module_search.R
import com.pp.module_search.adapter.SearchAdapter
import com.pp.module_search.adapter.SearchRankAdapter
import com.pp.module_search.databinding.*
import com.pp.module_search.listener.OnItemClickListener
import com.pp.module_search.model.SearchItemModel
import com.pp.module_search.model.SearchRankItemModel
import com.pp.module_search.model.SearchRankItemTitleModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = RouterPath.Search.activity_search)
class SearchActivity : ThemeActivity<ActivitySearchBinding, SearchViewModel>() {
    override val mBinding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun getModelClazz(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    companion object {
        private const val TAG = "SearchActivity"

        const val TYPE_HISTORY = "type_history"
        const val TYPE_HISTORY_TITLE = "type_history_title"
        const val TYPE_HOT_QUERIES = "type_hot_queries"
        const val TYPE_HOT_QUERIES_TITLE = "type_hot_queries_title"

        private const val KEY_SEARCH_HISTORY = "search_history"
        private const val MAX_HISTORY_COUNT = 5
    }

    private var mAdapter: SearchAdapter? = null
    private var mRankAdapter: SearchRankAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSearchView()
        initHotRecycler()
        initRankRecycler()
        initData()
    }

    private fun initRankRecycler() {
        mBinding.recyclerRank.layoutManager = LinearLayoutManager(baseContext)

        mRankAdapter = SearchRankAdapter().apply {
            addBindingItem(DefaultViewBindingItem<SearchRankItemTitleModel>(
                SearchViewModel.TYPE_RANK_TITLE,
                { it?.itemType == SearchViewModel.TYPE_RANK_TITLE },
                { parent ->
                    ItemSearchRankTitleBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))
            addBindingItem(DefaultViewBindingItem<SearchRankItemModel>(
                SearchViewModel.TYPE_RANK,
                { it?.itemType == SearchViewModel.TYPE_RANK },
                { parent ->
                    ItemSearchRankBinding.inflate(layoutInflater, parent, false)
                },
                { binding, item, cacheItemViewModel ->
                    item
                }
            ))
        }

        mBinding.recyclerRank.adapter = mRankAdapter!!
    }

    private fun initHotRecycler() {
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
    private val hotList: MutableList<SearchItemModel> by lazy { mutableListOf() }
    private val historyList: MutableList<SearchItemModel> by lazy { mutableListOf() }
    private val historyTitle by lazy {
        SearchItemModel(
            TYPE_HISTORY_TITLE,
            getString(R.string.search_history),
            mItemClickListener
        )
    }
    private val hotTitle by lazy {
        SearchItemModel(
            TYPE_HOT_QUERIES_TITLE,
            getString(R.string.search_recommend)
        )
    }

    private val mItemClickListener = object : OnItemClickListener {
        override fun onDelete() {
            removeHistory()
        }

        override fun onSelect(itemModel: SearchItemModel) {
            itemModel.data?.let {
                save(it)
                // do search
                mBinding.searchView.setQuery(it, true)

            }
        }
    }

    private fun initData() {
        lifecycleScope.launch {

            try {
                baseContext.dataStore.data.collect {
                    it.asMap().forEach { entry ->
                        if (entry.key.name == KEY_SEARCH_HISTORY) {
                            val value = entry.value.toString()
                            val items = value.split(";").filter { s ->
                                s.isNotBlank()
                            }
                            mList.clear()
                            historyList.clear()

                            val size =
                                if (items.size > MAX_HISTORY_COUNT) MAX_HISTORY_COUNT else items.size
                            if (size > 0) {
                                historyList.add(historyTitle)
                            }
                            for (i in 0 until size) {
                                historyList.add(
                                    SearchItemModel(
                                        TYPE_HISTORY,
                                        items[i],
                                        mItemClickListener
                                    )
                                )
                            }
                            mList.addAll(historyList)
                            mList.addAll(hotList)
                            mAdapter?.setDataList(mList)
                        }

                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "collect dataStore: ${e.message}")
            }

        }

        hotList.add(hotTitle)

        mViewModel.hotQueriesData.observe(this) { t ->
            hotList.addAll(t)
            mList.addAll(hotList)
            mAdapter?.setDataList(mList)
        }

        mViewModel.getHot(mItemClickListener)

        lifecycleScope.launch {
            mViewModel.recommendState.collect {
                when (it) {
                    is Result.Loading -> {

                    }
                    is Result.Success -> {
                        mRankAdapter?.setDataList(it.data)
                    }
                    else -> {

                    }
                }
            }
        }

        mViewModel.getRecommend()
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
                    return false
                }

            })

            setOnCloseListener {
                this@SearchActivity.finish()
                true
            }

        }
    }

    private val searchKey = stringPreferencesKey(KEY_SEARCH_HISTORY)
    private val maxIndex = 3

    private fun save(searchText: String) {
        lifecycleScope.launch {
            try {
                baseContext.dataStore.edit {
                    val old = it[searchKey] ?: ""

                    var oldItem = ""
                    old.split(";").filter { s ->
                        s.isNotBlank() && s != searchText
                    }.filterIndexed { index, _ ->
                        index <= maxIndex
                    }.forEach { s ->
                        oldItem += "${s};"
                    }

                    it[searchKey] = "${searchText};${oldItem}"
                }
            } catch (e: Exception) {
                Log.e(TAG, "save err: ${e.message}")
            }
        }

    }

    private fun removeHistory() {
        lifecycleScope.launch {
            try {
                baseContext.dataStore.edit {
                    val old = it[searchKey] ?: ""
                    it[searchKey] = ""
                }
            } catch (e: Exception) {
                Log.e(TAG, "save err: ${e.message}")
            }
        }
    }
}