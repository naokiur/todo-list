package jp.ne.naokiur.todomanagement.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import jp.ne.naokiur.todomanagement.R
import kotlinx.android.synthetic.main.component_menu.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val taskDatabaseHelper = TasksDatabaseHelper(this)
        initFont()

        icon_add.setOnClickListener { view ->
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
//        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }
    private fun initFont() {
        val font = Typeface.createFromAsset(assets, "fa-solid-900.ttf")
        val iconAdd = findViewById<TextView>(R.id.icon_add)
        val iconList = findViewById<TextView>(R.id.icon_list)
        val iconTop = findViewById<TextView>(R.id.icon_top)

        iconAdd.typeface = font
        iconList.typeface = font
        iconTop.typeface = font
    }
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
