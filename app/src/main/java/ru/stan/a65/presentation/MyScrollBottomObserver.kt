package ru.stan.a65.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyScrollBottomObserver(
    private val manager: LinearLayoutManager,
    private val recyclerView: RecyclerView,
    private val adapter: ForumAdapter
) : RecyclerView.AdapterDataObserver() {

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)

        val count = adapter.itemCount
        val lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition()
        val loading = lastVisiblePosition == -1

        val atBottom = positionStart >= count - 1 && lastVisiblePosition == positionStart - 1

        if (loading || atBottom)
            recyclerView.scrollToPosition(positionStart)
    }
}