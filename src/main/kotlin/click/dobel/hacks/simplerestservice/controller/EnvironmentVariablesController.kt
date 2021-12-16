package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.repository.EnvironmentVarRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@ConditionalOnProperty(prefix = "app", name = ["environment-endpoint.enabled"], havingValue = "true")
class EnvironmentVariablesController(
  private val repository: EnvironmentVarRepository
) {

  @GetMapping("/environment")
  fun getEnvironment() = repository.filteredEnviromentVariables

  @GetMapping("/environment/{variableName}")
  fun getEnvironment(
    @PathVariable(name = "variableName") variableName: String,
    @RequestParam(name = "all", required = false, defaultValue = "false") all: Boolean,
  ): Map<String, String> {
    val props = if (all) {
      repository.allEnviromentVariables
    } else {
      repository.filteredEnviromentVariables
    }
    return props.filter { it.key == variableName }
  }
}
