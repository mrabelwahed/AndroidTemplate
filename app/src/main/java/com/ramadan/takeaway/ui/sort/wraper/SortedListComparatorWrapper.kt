package com.ramadan.takeaway.ui.sort.wraper

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedListAdapterCallback
abstract class SortedListComparatorWrapper<T>(adapter: RecyclerView.Adapter<*>, private var comparator: Comparator<T>?) : SortedListAdapterCallback<T>(adapter) {

    fun setComparator(comparator: Comparator<T>) {
        if (comparator == this.comparator) {
            return
        }

        this.comparator = comparator
    }

    override fun compare(o1: T, o2: T) = comparator!!.compare(o1, o2)
}
