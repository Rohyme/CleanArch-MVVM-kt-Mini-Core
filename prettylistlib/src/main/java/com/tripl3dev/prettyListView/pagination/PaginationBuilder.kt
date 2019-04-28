package com.tripl3dev.prettyListView.pagination

import androidx.annotation.LayoutRes
import com.tripl3dev.prettyListView.R
import com.tripl3dev.prettyListView.baseAdapter.AdapterBuilder

class PaginationBuilder<T>internal constructor(val adapterBuilder: AdapterBuilder<T>) {
    internal var visibleThreshold = 5

    @LayoutRes
    internal var loadingLayoutRes : Int = R.layout.default_pagination_loading

    @LayoutRes
    internal var errorLayoutRes : Int = R.layout.default_pagination_error

    /**
     * Setting Visible threshold
     */
    fun setVisibleThreshold(visibleThreshold: Int) :PaginationBuilder<T>{
        this.visibleThreshold = visibleThreshold
        return this
    }

    /**
     * Setting error layout for pagination
     *
     */

    fun setErrorLayout(@LayoutRes errorLayoutRes : Int):PaginationBuilder<T>{
        this.errorLayoutRes  = errorLayoutRes
        return this
    }

    /**
     * Setting Loading layout for pagination
     *
     */
    fun setLoadingLayout(@LayoutRes loadingLayoutRes: Int):PaginationBuilder<T>{
        this.loadingLayoutRes = loadingLayoutRes
        return this
    }


     fun paginationDone() :AdapterBuilder<T>{
        adapterBuilder.paginationBuilder = this
        return adapterBuilder
    }

}