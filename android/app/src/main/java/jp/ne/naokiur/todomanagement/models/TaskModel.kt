package jp.ne.naokiur.todomanagement.models

class TaskModel(val name: String, val status: String, val beginData: Long, val endDate: Long, val updateTime: Long) {
    enum class Status(val value: String) {
        DOING("0"),
        DONE("1")
    }
}