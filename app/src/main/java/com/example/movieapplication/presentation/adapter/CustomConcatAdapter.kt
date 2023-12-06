package com.example.movieapplication.presentation.adapter

import android.content.Context
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
/*
class CustomConcatAdapter(
    private val pagerAdapter: PagerAdapter,
    private val listAdapter: ListAdapter<*, *>
) : ConcatAdapter() {

    init {
        addAdapter(pagerAdapter)
        addAdapter(listAdapter)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        // If the pagerAdapter is a RecyclerView.Adapter, set its stateRestorationPolicy
        if (pagerAdapter is RecyclerView.Adapter<*>) {
            pagerAdapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        // If the listAdapter is a RecyclerView.Adapter, set its stateRestorationPolicy
        if (listAdapter is RecyclerView.Adapter<*>) {
            listAdapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
}*/

