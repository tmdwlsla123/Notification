package com.example.customnotification

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.example.customnotification.DataBase.AppDB

class SwipeHelperCallback(val adapter: NotificationAdapter,val context: Context) : ItemTouchHelper.Callback() {
    val db = AppDB.getInstance(context)
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // Drag와 Swipe 방향을 결정 Drag는 사용하지 않아 0, Swipe의 경우는 LEFT, RIGHT 모두 사용가능하도록 설정
        return makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue * 5
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        db!!.DAO().update_screen_status(adapter.data.get(viewHolder.position).appdetail!!.packagename!!)
        adapter.notifyDataSetChanged()
    Log.e("포지션",viewHolder.position.toString())
    }
}