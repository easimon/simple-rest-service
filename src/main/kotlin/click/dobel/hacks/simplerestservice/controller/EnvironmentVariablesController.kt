package click.dobel.hacks.simplerestservice.controller

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@ConditionalOnProperty(prefix = "app", name = ["enableenv"], havingValue = "true")
class EnvironmentVariablesController {

  @GetMapping("/environment", produces = ["text/plain"])
  fun getEnvironment(): String {
    return System.getenv()
      .map { e -> "${e.key}=${e.value}" }
      .sorted()
      .joinToString("\n")
  }
}
