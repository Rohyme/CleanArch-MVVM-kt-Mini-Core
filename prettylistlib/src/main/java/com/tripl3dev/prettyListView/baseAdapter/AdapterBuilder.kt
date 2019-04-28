package com.tripl3dev.prettyListView.baseAdapter

import com.tripl3dev.prettyListView.pagination.PaginationBuilder


class AdapterBuilder<T> internal constructor(val listView: androidx.recyclerview.widget.RecyclerView) {
    lateinit var holder: MainHolderInterface<T>
    internal var paginationBuilder: PaginationBuilder<T>? = null
    internal fun isPaginated() = paginationBuilder != null


    fun setAdapter(holderInterface: MainHolderInterface<T>): AdapterBuilder<T> {
        holder = holderInterface
        return this
    }

    fun adapterDone(): BaseListAdapter<T> {
        return BaseListAdapter(this)
    }

    fun hasPaginated(): PaginationBuilder<T> {
        paginationBuilder = PaginationBuilder(this)
        return paginationBuilder!!
    }


}