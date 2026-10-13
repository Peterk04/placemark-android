package org.setu.placemark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.setu.placemark.models.PlacemarkModel
import timber.log.Timber

class MarkListActivity : AppCompatActivity() {
    private lateinit var adapter: PlacedMarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_list)
        adapter = PlacedMarkAdapter(
            marks = AppData.placedMarks.findAll(),
            listener = object : OnMarkListener {
                override fun onMarkClick(mark: PlacemarkModel) = editMark(mark)

                override fun onMarkLongClick(mark: PlacemarkModel): Boolean {
                    confirmDelete(mark)
                    return true
                }
            }
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
        Timber.d("Editing mark id=%d", mark.id)
        startActivity(Intent(this, AddEditActivity::class.java).apply {
            putExtra(AddEditActivity.EXTRA_MARK_ID, mark.id)
        })
    }

    private fun confirmDelete(mark: PlacemarkModel) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle(R.string.delete_mark_confirm)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete_mark) { _, _ ->
                AppData.placedMarks.delete(mark.id)
                Timber.d("Deleted mark id=%d", mark.id)
                refreshMarks()
            }
            .show()
    }

    private fun refreshMarks() {
        val marks = AppData.placedMarks.findAll()
        Timber.d("Loaded %d marks", marks.size)
        adapter.updateMarks(marks)
    }
}
