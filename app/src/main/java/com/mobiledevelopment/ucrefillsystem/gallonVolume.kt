package com.mobiledevelopment.ucrefillsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class gallonVolume : AppCompatActivity() {
    val list = ArrayList<model_gallon>()
    val listUsers = arrayOf(
        "Lantai 1",
        "Lantai 2",
        "Lantai 3",
        "Lantai 4",
        "Lantai 5",
        "Lantai 6",
        "Lantai 7")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallon_volume)
        supportActionBar?.hide()
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        for (i in 0 until listUsers.size){

            list.add(model_gallon(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = adapter_gallon(list)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                mRecyclerView.adapter = adapter
            }

        }
    }
}
