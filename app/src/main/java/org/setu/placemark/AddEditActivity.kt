package org.setu.placemark

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.setu.placemark.models.PlacemarkModel
import timber.log.Timber

class AddEditActivity : AppCompatActivity() {
    private lateinit var titleInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var xInput: EditText
    private lateinit var yInput: EditText
    private var editingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editingId = intent.getLongExtra(EXTRA_MARK_ID, -1L).takeIf { it != -1L }
        createUserInterface()
        editingId?.let { loadExistingMark(it) }
    }

    private fun createUserInterface() {
        setContentView(R.layout.activity_add_edit)
        titleInput = findViewById(R.id.titleInput)
        descriptionInput = findViewById(R.id.descriptionInput)
        xInput = findViewById(R.id.xInput)
        yInput = findViewById(R.id.yInput)
        findViewById<TextView>(R.id.formTitle).setText(
            if (editingId == null) R.string.add_mark else R.string.edit_mark
        )
        findViewById<Button>(R.id.saveButton).setOnClickListener { saveMark() }
        findViewById<Button>(R.id.cancelButton).setOnClickListener {
            Timber.d("Cancelled mark form")
            finish()
        }
    }

    private fun loadExistingMark(id: Long) {
        val mark = AppData.placedMarks.findOne(id)
        if (mark == null) {
            Timber.w("Mark id=%d not found while editing", id)
            Toast.makeText(this, R.string.mark_not_found, Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        titleInput.setText(mark.title)
        descriptionInput.setText(mark.description)
        xInput.setText(mark.x.toString())
        yInput.setText(mark.y.toString())
    }

    private fun saveMark() {
        val title = titleInput.text.toString().trim()
        if (title.isEmpty()) {
            titleInput.error = getString(R.string.title_required)
            return
        }
        val x = xInput.text.toString().toDoubleOrNull()
        if (x == null || !x.isFinite()) {
            xInput.error = getString(R.string.valid_number_required)
            return
        }
        val y = yInput.text.toString().toDoubleOrNull()
        if (y == null || !y.isFinite()) {
            yInput.error = getString(R.string.valid_number_required)
            return
        }
        val mark = PlacemarkModel(
            id = editingId ?: 0L,
            title = title,
            description = descriptionInput.text.toString().trim(),
            x = x,
            y = y
        )
        if (editingId == null) {
            AppData.placedMarks.create(mark)
            Timber.d("Created mark id=%d", mark.id)
            Toast.makeText(this, R.string.mark_created, Toast.LENGTH_SHORT).show()
        } else {
            if (!AppData.placedMarks.update(mark)) {
                Timber.w("Mark id=%d not found during update", mark.id)
                Toast.makeText(this, R.string.mark_not_found, Toast.LENGTH_SHORT).show()
                return
            }
            Timber.d("Updated mark id=%d", mark.id)
            Toast.makeText(this, R.string.mark_updated, Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    companion object {
        const val EXTRA_MARK_ID = "id"
    }
}
