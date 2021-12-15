package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.repository.CpuLoadRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CpuLoadController(
  private val repository: CpuLoadRepository
) {

  @GetMapping("/load/add")
  fun generateLoad(
    @RequestParam(name = "secs", required = false, defaultValue = "10") seconds: Long
  ): String {
    repository.queueLoadTask(seconds)
    return "OK"
  }

  @GetMapping("/load")
  fun getRemainingSeconds(): Int {
    return repository.remainingSeconds
  }
}
