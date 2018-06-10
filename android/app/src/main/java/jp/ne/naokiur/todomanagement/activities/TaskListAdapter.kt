package jp.ne.naokiur.todomanagement.activities

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import jp.ne.naokiur.todomanagement.R
import jp.ne.naokiur.todomanagement.models.TaskModel
import jp.ne.naokiur.todomanagement.strage.TasksDatabaseHelper
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(context: Context, taskList: ArrayList<TaskModel>) : BaseAdapter() {
    val context: Context = context
    val layoutInflater: LayoutInflater
    val taskList = taskList

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return taskList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return taskList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        var customViewHolder: CustomViewHolder? = null

        v?.let {
            customViewHolder = it.tag as CustomViewHolder?

        } ?: kotlin.run {
            v = layoutInflater.inflate(R.layout.component_task_row, null)
            customViewHolder = CustomViewHolder(
                    v?.findViewById(R.id.task_name) as TextView,
                    v?.findViewById(R.id.limit_date) as TextView,
                    v?.findViewById(R.id.task_state) as CheckBox
            )

            v?.tag = customViewHolder
        }

        customViewHolder?.let {
            it.taskName.text = taskList[position].name

            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE)
            it.limitDate.text = sdf.format(taskList[position].endDate)

            it.checkBox.isChecked = TaskModel.Status.fromValue(taskList[position].status).isDone

            it.checkBox.setOnClickListener { _ ->
                val taskDatabaseHelper = TasksDatabaseHelper(context)
                taskDatabaseHelper.updateCheck(
                        taskList[position].id,
                        TaskModel.Status.fromIsDone(it.checkBox.isChecked).value
                )
            }

            if (it.checkBox.isChecked) {
                it.taskName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                it.limitDate.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        return v as View
    }

    class CustomViewHolder(var taskName: TextView, var limitDate: TextView, var checkBox: CheckBox)
}