package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.config.EnvironmentEndpointProperties
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@ConditionalOnProperty(prefix = "app", name = ["environment-endpoint.enabled"], havingValue = "true")
class EnvironmentVariablesController(
  configuration: EnvironmentEndpointProperties
) {

  companion object {
    private val LOGGER = LoggerFactory.getLogger(EnvironmentVariablesController.javaClass)
  }

  val filteredVarPrefixes = configuration.filteredPrefixes
    .split(",")
    .map { it.trim() }
    .toSet()

  @GetMapping("/environment", produces = ["text/plain"])
  fun getEnvironment(): String {
    LOGGER.info("filtering: {}", filteredVarPrefixes.joinToString(","))
    return System.getenv()
      .filter { envVar -> filteredVarPrefixes.all { !envVar.key.startsWith(it) } }
      .map { e -> "${e.key}=${e.value}" }
      .sorted()
      .joinToString("\n")
  }
}
