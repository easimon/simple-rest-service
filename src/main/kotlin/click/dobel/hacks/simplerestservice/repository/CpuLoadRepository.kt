package click.dobel.hacks.simplerestservice.repository

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.annotation.PreDestroy

@Repository
class CpuLoadRepository {

  companion object {
    private const val parallelThreadCount = 1
    private const val taskDurationSeconds = 10

    private val LOGGER = LoggerFactory.getLogger(CpuLoadRepository::class.java)
  }

  private val executorQueue = LinkedBlockingQueue<Runnable?>()
  private val executorPool = ThreadPoolExecutor(
    parallelThreadCount,
    parallelThreadCount,
    0L,
    TimeUnit.MILLISECONDS,
    executorQueue
  )

  val remainingSeconds: Int get() = executorQueue.size * taskDurationSeconds

  fun queueLoadTask(seconds: Long) {
    LOGGER.info("Adding {} seconds of work.", seconds)
    repeat((seconds / taskDurationSeconds).toInt()) {
      executorPool.submit(LoadGenerator())
    }
  }

  @PreDestroy
  fun shutdown() {
    LOGGER.info("Terminating executor pool.")
    executorPool.shutdownNow()
    executorPool.awaitTermination(1, TimeUnit.MINUTES)
    LOGGER.info("Executor pool terminated.")
  }

  private inner class LoadGenerator(val seconds: Int = taskDurationSeconds) : Runnable {
    override fun run() {
      LOGGER.info("Consuming {} seconds cpu time.", seconds)
      val endTime = System.currentTimeMillis() + 1000 * seconds
      while (System.currentTimeMillis() < endTime) {
        // burn cpu.
      }
      LOGGER.info("Consumed {} seconds cpu time, {} remaining in queue.", seconds, remainingSeconds)
    }
  }
}
