package click.dobel.hacks.simplerestservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "app.environment-endpoint")
@ConstructorBinding
data class EnvironmentEndpointProperties(
  val enabled: Boolean,
  val filteredPrefixes: String,
)
