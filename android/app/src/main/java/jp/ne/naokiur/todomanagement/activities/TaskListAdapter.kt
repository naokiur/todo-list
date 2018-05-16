package jp.ne.naokiur.todomanagement.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import jp.ne.naokiur.todomanagement.R
import jp.ne.naokiur.todomanagement.models.TaskModel

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
        var holder: CustomViewHolder? = null
        System.out.println("getView Now")

        v?.let {
            holder = it.tag as CustomViewHolder?
            System.out.println("holder Now")
        } ?: kotlin.run {
            v = layoutInflater.inflate(R.layout.component_task_row, null)
            holder = CustomViewHolder(v?.findViewById(R.id.task_name) as TextView)
            v?.tag = holder
            System.out.println("run Now")
        }

        holder?.let {
            it.textView.text = taskList[position].name
            System.out.println(taskList[position].name)

        }

        return v as View
    }

    class CustomViewHolder(var textView: TextView)
}