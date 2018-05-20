package jp.ne.naokiur.todomanagement.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import jp.ne.naokiur.todomanagement.R
import jp.ne.naokiur.todomanagement.models.TaskModel
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(context: Context, taskList: ArrayList<TaskModel>) : BaseAdapter() {
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
        var taskNameHolder: CustomViewHolder? = null
        var limitDateHolder: CustomViewHolder? = null
        var taskStateHolder: CustomViewHolder? = null

        v?.let {
            taskNameHolder = it.tag as CustomViewHolder?

        } ?: kotlin.run {
            v = layoutInflater.inflate(R.layout.component_task_row, null)
            taskNameHolder = CustomViewHolder(v?.findViewById(R.id.task_name) as TextView)
            limitDateHolder = CustomViewHolder(v?.findViewById(R.id.limit_date) as TextView)
            v?.tag = taskNameHolder
        }

        taskNameHolder?.let {
            it.textView.text = taskList[position].name
        }

        limitDateHolder?.let {
            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.JAPANESE)
            it.textView.text = sdf.format(taskList[position].endDate)
        }

        return v as View
    }

    class CustomViewHolder(var textView: TextView)
}