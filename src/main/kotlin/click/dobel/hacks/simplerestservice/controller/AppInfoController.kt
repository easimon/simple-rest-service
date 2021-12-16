package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.config.AppProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppInfoController(
  properties: AppProperties
) {
  private val deploymentInfo = DeploymentInfo(properties.deploymentType)

  @GetMapping("/")
  fun getDeploymentType() = deploymentInfo

  data class DeploymentInfo(
    val type: String
  )
}
