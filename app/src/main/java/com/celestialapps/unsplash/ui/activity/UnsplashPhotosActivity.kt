package com.celestialapps.unsplash.ui.activity

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem

import com.arellomobile.mvp.presenter.InjectPresenter
import com.celestialapps.unsplash.R
import com.celestialapps.unsplash.network.model.UnsplashPhotos
import com.celestialapps.unsplash.network.model.UnsplashPhotosSearchResult
import com.celestialapps.unsplash.presentation.presenter.UnsplashPhotosPresenter
import com.celestialapps.unsplash.presentation.view.UnsplashPhotosView
import com.celestialapps.unsplash.ui.adapter.UnsplashPhotosAdapter
import com.paginate.Paginate
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*


class UnsplashPhotosActivity : BaseActivity(), UnsplashPhotosView, Paginate.Callbacks {

    @Parcelize
    class StateData(var isLoading: Boolean, var query: String, var isSearch: Boolean,
                    var currentPage: Int, var maxPage: Int, var pageSize: Int) : Parcelable

    companion object {
        const val STATE_DATA: String = "state_data";
    }

    @InjectPresenter
    lateinit var mUnsplashPhotosPresenter: UnsplashPhotosPresenter

    private lateinit var mPaginate: Paginate
    private lateinit var mUnsplashPhotosAdapter: UnsplashPhotosAdapter;

    private var mStateData: StateData = StateData(false, "", false, 0, 1000, 30)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mStateData = savedInstanceState.getParcelable(STATE_DATA)
        }

        m_rv_photos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        m_rv_photos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        initializeAdapterWithPagination()

    }

    private fun initializeAdapterWithPagination() {
        mUnsplashPhotosAdapter = UnsplashPhotosAdapter(this, ArrayList())
        m_rv_photos.adapter = mUnsplashPhotosAdapter
        setupPagination()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(STATE_DATA, mStateData)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        menuItem.expandActionView()

        val searchView: SearchView = menuItem.actionView as SearchView

        searchView.setQuery(mStateData.query, false)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (mStateData.query != query) {
                    mStateData.isLoading = true
                    mStateData.query = query
                    mStateData.isSearch = true
                    mStateData.currentPage = 1
                    mStateData.maxPage = 1000
                    mStateData.pageSize = 30

                    mUnsplashPhotosAdapter = UnsplashPhotosAdapter(this@UnsplashPhotosActivity, ArrayList())
                    m_rv_photos.adapter = mUnsplashPhotosAdapter

                    mUnsplashPhotosPresenter.searchNextPagePhotos(query, mStateData.currentPage, mStateData.pageSize)

                }
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) {
                    mStateData.isSearch = false
                    mStateData.currentPage = 1
                    mStateData.maxPage = 1000
                    mStateData.pageSize = 30

                    initializeAdapterWithPagination()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    private fun setupPagination() {
        mPaginate = Paginate
                .with(m_rv_photos, this)
                .setLoadingTriggerThreshold(5)
                .build()
    }

    override fun gettingUnsplashPhotosNextPageSuccess(unsplashPhotosList: List<UnsplashPhotos>, currentPage: Int) {
        if (!mStateData.isSearch) {
            mStateData.currentPage = currentPage
            mStateData.isLoading = false
            mUnsplashPhotosAdapter.addItems(unsplashPhotosList)
        }
    }

    override fun gettingUnsplashPhotosNextPageFailed(message : String) {
        getSnackBar(message).setAction(getString(R.string.retry)) {
            loadOrSearchPhotos()
        }.show()
    }

    override fun onLoadMore() {
        mStateData.isLoading = true
        loadOrSearchPhotos()
    }

    private fun loadOrSearchPhotos() {
        if (mStateData.isSearch) {
            mUnsplashPhotosPresenter.searchNextPagePhotos(mStateData.query, mStateData.currentPage + 1, mStateData.pageSize)
        } else {
            mUnsplashPhotosPresenter.getNextPagePhotos(mStateData.currentPage + 1, mStateData.pageSize)
        }
    }

    override fun isLoading(): Boolean {
        return mStateData.isLoading
    }

    override fun hasLoadedAllItems(): Boolean {
        return mStateData.currentPage == mStateData.maxPage + 1
    }

    override fun searchPhotosNextPageSuccess(unsplashPhotosSearchResult: UnsplashPhotosSearchResult, query: String, currentPage: Int) {
        if (mStateData.isSearch && mStateData.query == query) {
            mStateData.isLoading = false
            mStateData.currentPage = currentPage
            mStateData.maxPage = unsplashPhotosSearchResult.totalPages.toInt()
            mUnsplashPhotosAdapter.addItemsFromSearch(unsplashPhotosSearchResult.results!!)
        }
    }

    override fun searchPhotosNextPageFailed(message : String) {
        getSnackBar(message).setAction(getString(R.string.retry)) {
            loadOrSearchPhotos()
        }.show()
    }


}
