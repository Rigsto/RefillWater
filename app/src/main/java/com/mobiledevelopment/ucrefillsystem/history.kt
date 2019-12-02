package com.mobiledevelopment.ucrefillsystem
import kotlinx.android.synthetic.main.activity_history.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class history : AppCompatActivity() {
    val list = ArrayList<model_history>()
    val listUsers = arrayOf(
        "Google",
        "Apple",
        "Microsoft",
        "Asus",
        "Zenpone",
        "Acer")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.hide()
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        for (i in 0 until listUsers.size){

            list.add(model_history(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = adapter_history(list)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                mRecyclerView.adapter = adapter
            }

        }
    }
}
