package com.example.strengthen_numbers.adapters

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.test.isSelected
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.R.color.primaryColor
import com.example.strengthen_numbers.models.IntrestModel

class HobbyGridAdapter(private val hobbyList: List<IntrestModel>, private val context: Context) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var hobby : IntrestModel

    override fun getCount(): Int = hobbyList.size

    override fun getItem(position: Int): Any = hobbyList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            layoutInflater = layoutInflater ?: context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater!!.inflate(R.layout.item_intrest, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        hobby = hobbyList[position]
        viewHolder.hobbyName.text = hobby.name
        viewHolder.hobbyImage.setImageResource(hobby.img)
        updateViewAppearance(view, viewHolder, hobby.isSelected)
        return view
    }

    private fun updateViewAppearance(view: View, viewHolder: ViewHolder, isSelected: Boolean) {
        if (isSelected) {
            view.setBackgroundResource(R.drawable.selected_hobby_backgroung)
            viewHolder.hobbyName.setTextColor(ContextCompat.getColor(context, R.color.white))
            viewHolder.hobbyImage.setImageResource(hobby.selectedImg)

        } else {
            view.setBackgroundResource(R.drawable.unselected_hobby_bacground)
            viewHolder.hobbyName.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private class ViewHolder(view: View) {
        val hobbyName: TextView = view.findViewById(R.id.txtHobby)
        val hobbyImage: ImageView = view.findViewById(R.id.imgHobby)
    }

    fun toggleSelection(position: Int) {
        hobbyList[position].isSelected = !hobbyList[position].isSelected
        notifyDataSetChanged()
    }

    fun getSelectedHobbies(): List<IntrestModel> {
        return hobbyList.filter { it.isSelected }
    }
}
