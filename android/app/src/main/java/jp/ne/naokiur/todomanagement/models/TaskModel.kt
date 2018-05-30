package jp.ne.naokiur.todomanagement.models

class TaskModel(val name: String, val status: String, val beginData: Long, val endDate: Long, val updateTime: Long) {
    var id: Long = 0
    constructor(id: Long = 0, name: String, status: String, beginData: Long, endDate: Long, updateTime: Long) : this(name, status, beginData, endDate, updateTime) {
        this.id = id
    }

    enum class Status(val value: String, val isDone: Boolean) {
        DOING("0", false),
        DONE("1", true);

        companion object {
            fun fromValue(value: String): Status {
                return values().first { it.value == value }
            }

            fun fromIsDone(isDone: Boolean): Status {
                return values().first { it.isDone == isDone }
            }
        }
    }
}