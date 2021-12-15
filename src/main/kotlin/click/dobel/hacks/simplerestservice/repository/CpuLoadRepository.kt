package click.dobel.hacks.simplerestservice.repository

import org.springframework.stereotype.Repository
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.annotation.PreDestroy

@Repository
class CpuLoadRepository {

  companion object {
    const val parallelThreadCount = 1
    const val taskDurationSeconds = 10
  }

  private val executorQueue = LinkedBlockingQueue<Runnable?>()
  private val executorPool = ThreadPoolExecutor(
    parallelThreadCount,
    parallelThreadCount,
    0L,
    TimeUnit.MILLISECONDS,
    executorQueue
  )

  fun queueLoadTask(seconds: Long) {
    repeat((seconds / taskDurationSeconds).toInt()) {
      executorPool.submit(LoadGenerator())
    }
  }

  val remainingSeconds: Int get() = executorQueue.size * taskDurationSeconds

  @PreDestroy
  fun shutdown() {
    executorPool.shutdownNow()
    executorPool.awaitTermination(1, TimeUnit.MINUTES)
  }

  private class LoadGenerator(val seconds: Int = taskDurationSeconds) : Runnable {
    override fun run(): Unit {
      val endTime = System.currentTimeMillis() + 1000 * seconds
      while (System.currentTimeMillis() < endTime) {
        // burn cpu.
      }
    }
  }
}
