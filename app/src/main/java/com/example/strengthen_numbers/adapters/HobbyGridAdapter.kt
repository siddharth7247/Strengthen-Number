package com.example.strengthen_numbers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.models.IntrestModel

class HobbyGridAdapter(private val hoobylist : List<IntrestModel>,private val context: Context) :  BaseAdapter(){
    private var layoutInflater: LayoutInflater? = null
    private lateinit var hobbyName: TextView
    private lateinit var hobbyImage: ImageView
    override fun getCount(): Int {
        return hoobylist.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
       return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_intrest, null)
        }
        hobbyName = convertView!!.findViewById(R.id.txtHobby)
        hobbyImage = convertView!!.findViewById(R.id.imgHobby)

        hobbyName.setText(hoobylist.get(p0).name)
        hobbyImage.setImageResource(hoobylist.get(p0).img)
        return convertView
    }
}