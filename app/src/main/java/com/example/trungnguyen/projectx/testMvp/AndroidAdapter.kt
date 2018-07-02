package com.example.trungnguyen.projectx.testMvp

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trungnguyen.projectx.R
import com.example.trungnguyen.projectx.entity.AndroidVersion
import kotlinx.android.synthetic.main.item_android_version.view.*

/**
 * Created by Trung Nguyen on 26-Apr-18.
 */
class AndroidAdapter(val androidVerList: ArrayList<AndroidVersion>) : RecyclerView.Adapter<AndroidAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(androidVerList.get(position))
        Log.e("Data: ",androidVerList.get(position).toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_android_version,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = androidVerList.count();

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bin(android: AndroidVersion) {
            itemView.tv_name.text=android.name
            itemView.tv_ver.text=android.ver
            itemView.tv_api.text=android.api
        }
    }
}