package click.dobel.hacks.simplerestservice.repository

import click.dobel.hacks.simplerestservice.config.EnvironmentEndpointProperties
import org.springframework.stereotype.Repository

@Repository
class EnvironmentVarRepository (
configuration: EnvironmentEndpointProperties
) {
  private val filteredVarPrefixes = configuration.filteredPrefixes
    .split(",")
    .map { it.trim() }
    .toSet()

  val allEnviromentVariables = System.getenv().toSortedMap()

  val filteredEnviromentVariables = allEnviromentVariables
    .filter { envVar -> filteredVarPrefixes.all { !envVar.key.startsWith(it) } }
}
