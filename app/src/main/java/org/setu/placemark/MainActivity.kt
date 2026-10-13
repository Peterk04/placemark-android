package org.setu.placemark

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Timber.treeCount == 0) Timber.plant(Timber.DebugTree())
        Timber.d("Main menu opened")
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.viewMarksButton).setOnClickListener {
            Timber.d("Opening mark list")
            startActivity(Intent(this, MarkListActivity::class.java))
        }
        findViewById<Button>(R.id.addMarkButton).setOnClickListener {
            Timber.d("Opening new mark form")
            startActivity(Intent(this, AddEditActivity::class.java))
        }
    }
}
