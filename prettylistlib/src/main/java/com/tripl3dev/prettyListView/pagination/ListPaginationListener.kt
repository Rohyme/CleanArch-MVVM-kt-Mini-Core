package com.tripl3dev.prettyListView.pagination

import android.view.View

interface ListPaginationListener {
    fun onLoadMore(page: Int, totalItemCount: Int, listView: androidx.recyclerview.widget.RecyclerView)
    fun onError(errorView: View){}
    fun onLoading(loadingView:View){}
}