package org.setu.placemark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.setu.placemark.models.PlacemarkModel
import timber.log.Timber

class PlacedMarkAdapter(
    private var marks: List<PlacemarkModel>,
    private val listener: OnMarkListener
) : RecyclerView.Adapter<PlacedMarkAdapter.MarkViewHolder>() {
    class MarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        val coordinatesText: TextView = itemView.findViewById(R.id.coordinatesText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_placed_mark, parent, false)
        return MarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarkViewHolder, position: Int) {
        val mark = marks[position]
        holder.titleText.text = "${mark.id}: ${mark.title}"
        holder.descriptionText.text = mark.description
        holder.coordinatesText.text = holder.itemView.context.getString(
            R.string.coordinates, mark.x, mark.y
        )
        holder.itemView.setOnClickListener {
            Timber.d("Mark item clicked: id=%d", mark.id)
            listener.onMarkClick(mark)
        }
        holder.itemView.setOnLongClickListener {
            Timber.d("Mark item long-pressed: id=%d", mark.id)
            listener.onMarkLongClick(mark)
        }
    }

    override fun getItemCount(): Int = marks.size

    fun updateMarks(newMarks: List<PlacemarkModel>) {
        marks = newMarks
        notifyDataSetChanged()
    }
}
