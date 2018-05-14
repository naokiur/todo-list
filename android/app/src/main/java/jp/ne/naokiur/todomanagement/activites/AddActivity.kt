package jp.ne.naokiur.todomanagement.activites

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import jp.ne.naokiur.todomanagement.R
import jp.ne.naokiur.todomanagement.models.TaskModel
import jp.ne.naokiur.todomanagement.strage.TasksDatabaseHelper
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val cal = Calendar.getInstance()
        val format = "yyyy/MM/dd"
        val dateFormat = SimpleDateFormat(format, Locale.JAPANESE)

        val beginDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            begin_date!!.text = dateFormat.format(cal.time)
        }

        val limitDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            limit_date!!.text = dateFormat.format(cal.time)
        }

        begin_date.setOnClickListener { _ ->
            DatePickerDialog(
                    this@AddActivity,
                    beginDateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        limit_date.setOnClickListener { _ ->
            DatePickerDialog(
                    this@AddActivity,
                    limitDateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        save_button.setOnClickListener { _ ->
            val taskDatabaseHelper = TasksDatabaseHelper(this)

            val beginDate = dateFormat.parse(begin_date.text.toString()).time
            val limitDate = dateFormat.parse(limit_date.text.toString()).time

            val newTask = TaskModel(
                    task_name.text.toString(),
                    TaskModel.Status.DOING.value,
                    beginDate,
                    limitDate,
                    Calendar.getInstance().timeInMillis
            )

            taskDatabaseHelper.insertTask(newTask)

            task_name.text.clear()
            begin_date.text = ""
            limit_date.text = ""

            Toast.makeText(this, "Added new Task!", Toast.LENGTH_LONG).show()
        }
    }
}
