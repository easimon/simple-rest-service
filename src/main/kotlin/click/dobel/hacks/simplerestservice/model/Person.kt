package click.dobel.hacks.simplerestservice.model

import java.util.UUID

data class Person(val id: UUID, val name: String) {
  constructor(name: String): this(UUID.randomUUID(), name)
}
