package org.setu.placemark

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.setu.placemark.models.PlacemarkModel

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
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 320, 32, 32)
        }
        root.addView(TextView(this).apply {
            id = R.id.formTitle
            text = getString(if (editingId == null) R.string.add_mark else R.string.edit_mark)
            textSize = 28f
        })
        titleInput = EditText(this).apply {
            id = R.id.titleInput
            hint = getString(R.string.title_hint)
        }
        descriptionInput = EditText(this).apply {
            id = R.id.descriptionInput
            hint = getString(R.string.description_hint)
        }
        xInput = coordinateInput(R.id.xInput, R.string.x_hint)
        yInput = coordinateInput(R.id.yInput, R.string.y_hint)
        root.addView(titleInput)
        root.addView(descriptionInput)
        root.addView(xInput)
        root.addView(yInput)
        root.addView(Button(this).apply {
            id = R.id.saveButton
            text = getString(R.string.save_mark)
            setOnClickListener { saveMark() }
        })
        root.addView(Button(this).apply {
            id = R.id.cancelButton
            text = getString(R.string.cancel)
            setOnClickListener { finish() }
        })
        setContentView(ScrollView(this).apply { addView(root) })
    }

    private fun coordinateInput(viewId: Int, hintId: Int) = EditText(this).apply {
        id = viewId
        hint = getString(hintId)
        inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or
            InputType.TYPE_NUMBER_FLAG_SIGNED
    }

    private fun loadExistingMark(id: Long) {
        val mark = AppData.placedMarks.findOne(id)
        if (mark == null) {
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
            Toast.makeText(this, R.string.mark_created, Toast.LENGTH_SHORT).show()
        } else {
            if (!AppData.placedMarks.update(mark)) {
                Toast.makeText(this, R.string.mark_not_found, Toast.LENGTH_SHORT).show()
                return
            }
            Toast.makeText(this, R.string.mark_updated, Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    companion object {
        const val EXTRA_MARK_ID = "id"
    }
}
