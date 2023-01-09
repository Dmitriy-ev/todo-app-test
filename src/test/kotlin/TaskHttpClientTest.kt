import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TaskHttpClientTest : FunSpec({

    val taskApi = TaskHttpClient()
    test("add task") {
        val task =
            taskApi.createTask(Task(id = null, name = "test2", priority = Priority.HIGH, completed = null))
        task?.id shouldNotBe null
        task?.completed shouldBe false
    }
})
