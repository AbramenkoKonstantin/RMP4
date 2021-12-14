package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, list,this)
    }
    private val list = arrayListOf(ColorData(Color.CYAN, "Cyan"),
            ColorData(Color.BLACK, "Black"),
            ColorData(Color.BLUE, "Blue"),
            ColorData(Color.RED, "Red"),
            ColorData(Color.GRAY, "Gray")
    )
    override fun onCellClickListener(data: ColorData) {
        Toast.makeText(this, "It is " + data.colorName, Toast.LENGTH_SHORT).show()
    }
}
data class ColorData(
    val colorHex: Int,
    val colorName: String
)

interface CellClickListener {
    fun onCellClickListener(data: ColorData)
}

