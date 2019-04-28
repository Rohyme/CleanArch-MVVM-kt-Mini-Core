package com.tripl3dev.prettyListView.baseAdapter

import androidx.annotation.LayoutRes
import java.util.*

interface MainHolderInterface<T> {
    @LayoutRes
    fun getView(type:Int): Int

    fun getList(): ArrayList<T?>

    fun getViewData(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, t: T, position: Int = 0)

     fun getItemViewType(position: Int): Int{
        return 0
    }

}