package org.setu.placemark

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createUserInterface()
    }

    override fun onResume() {
        super.onResume()
        if (::listLayout.isInitialized) displayMarks()
    }

    private fun createUserInterface() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 320, 32, 32)
        }
        val title = TextView(this).apply {
            text = getString(R.string.placed_marks)
            textSize = 28f
            gravity = Gravity.CENTER
        }
        val addButton = Button(this).apply {
            id = R.id.addMarkButton
            text = getString(R.string.add_mark)
            setOnClickListener {
                startActivity(Intent(this@MainActivity, AddEditActivity::class.java))
            }
        }
        listLayout = LinearLayout(this).apply {
            id = R.id.marksContainer
            orientation = LinearLayout.VERTICAL
        }
        root.addView(title)
        root.addView(addButton)
        root.addView(listLayout)
        setContentView(ScrollView(this).apply { addView(root) })
        displayMarks()
    }

    private fun displayMarks() {
        listLayout.removeAllViews()
        val marks = AppData.placedMarks.findAll()
        if (marks.isEmpty()) {
            listLayout.addView(TextView(this).apply {
                text = getString(R.string.no_marks)
                textSize = 18f
                setPadding(0, 40, 0, 40)
            })
            return
        }
        for (mark in marks) {
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(0, 20, 0, 20)
            }
            row.addView(TextView(this).apply {
                text = "${mark.id}: ${mark.title}"
                textSize = 20f
            })
            row.addView(TextView(this).apply { text = mark.description })
            row.addView(TextView(this).apply { text = getString(R.string.coordinates, mark.x, mark.y) })
            row.addView(Button(this).apply {
                id = R.id.editButton
                text = getString(R.string.edit_mark)
                setOnClickListener {
                    startActivity(Intent(this@MainActivity, AddEditActivity::class.java).apply {
                        putExtra(AddEditActivity.EXTRA_MARK_ID, mark.id)
                    })
                }
            })
            row.addView(Button(this).apply {
                id = R.id.deleteButton
                text = getString(R.string.delete_mark)
                setOnClickListener {
                    AppData.placedMarks.delete(mark.id)
                    displayMarks()
                }
            })
            listLayout.addView(row)
        }
    }
}
