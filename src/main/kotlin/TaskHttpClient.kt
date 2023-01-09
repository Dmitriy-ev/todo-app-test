class TaskHttpClient : BaseHttpClient() {

    private val url = "http://localhost:8080/api/v1/task"

    fun createTask(task: Task): Task?{
        val response = doPostRequest(url, task).body?.string()
        return gson.fromJson<Task>(response)
    }
}