package jp.ne.naokiur.todomanagement.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.ne.naokiur.todomanagement.R
import jp.ne.naokiur.todomanagement.strage.TasksDatabaseHelper
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

//        val array = arrayOf("a", "b", "c")

        val dbHelper = TasksDatabaseHelper(this)
        val adapter = TaskListAdapter(this, dbHelper.selectAll())

        task_list.adapter = adapter
    }
}
