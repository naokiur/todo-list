package jp.ne.naokiur.todomanagement.strage

import android.provider.BaseColumns

object DBContract {

    class TaskEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "tasks"
            const val TASK_NAME = "task_name"
            const val STATUS = "status"
            const val BEGIN_DATE = "begin_date"
            const val END_DATE = "end_date"
            const val UPDATE_TIME = "update_time"
        }
    }
}