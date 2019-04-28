package com.tripl3dev.prettyListView.baseAdapter

import androidx.recyclerview.widget.DiffUtil
import java.util.*

class MyDiffUtil<T>(val oldList: ArrayList<T?>, val newList: ArrayList<T?> ) : DiffUtil.Callback() {

    constructor(oldList: ArrayList<T?>, newList: ArrayList<T?> , ss : ContentsAreTheSame<T>):this (oldList,newList){
        setItemsTheSame(ss)
    }
    private lateinit var itemsTheSame: ContentsAreTheSame<T>
    fun setItemsTheSame(itemsTheSame: ContentsAreTheSame<T>) {
        this.itemsTheSame = itemsTheSame
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (this::itemsTheSame.isInitialized) {
            itemsTheSame.areItemsTheSame(oldList[oldItemPosition]!!,newList[newItemPosition]!!)
        }else{
            oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (this::itemsTheSame.isInitialized) {
            itemsTheSame.areItemsHaveSameContent(oldList[oldItemPosition]!!, newList[newItemPosition]!!)
        }else{
            oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
         return if (this::itemsTheSame.isInitialized && getChangePayload(oldItemPosition , newItemPosition) !=null) {
            itemsTheSame.getChangePayload(oldItemPosition , newItemPosition)
        }else{
           super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }

    interface ContentsAreTheSame<T> {
        fun areItemsHaveSameContent(oldItem: T, newItem: T): Boolean
        fun areItemsTheSame(oldItem: T, newItem: T):Boolean
        fun getChangePayload(oldItemPosition :Int , newItemPosition: Int) :Any?{
            return null
        }

    }

}
