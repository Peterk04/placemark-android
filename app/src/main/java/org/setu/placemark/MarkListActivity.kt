package org.setu.placemark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.setu.placemark.models.PlacemarkModel

class MarkListActivity : AppCompatActivity() {
    private lateinit var adapter: PlacedMarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_list)
        adapter = PlacedMarkAdapter(
            marks = AppData.placedMarks.findAll(),
            onEdit = { editMark(it) },
            onDelete = { deleteMark(it) }
        )
        findViewById<RecyclerView>(R.id.marksRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@MarkListActivity)
            adapter = this@MarkListActivity.adapter
        }
        findViewById<Button>(R.id.returnButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (::adapter.isInitialized) refreshMarks()
    }

    private fun editMark(mark: PlacemarkModel) {
        startActivity(Intent(this, AddEditActivity::class.java).apply {
            putExtra(AddEditActivity.EXTRA_MARK_ID, mark.id)
        })
    }

    private fun deleteMark(mark: PlacemarkModel) {
        AppData.placedMarks.delete(mark.id)
        refreshMarks()
    }

    private fun refreshMarks() {
        val marks = AppData.placedMarks.findAll()
        adapter.updateMarks(marks)
    }
}
