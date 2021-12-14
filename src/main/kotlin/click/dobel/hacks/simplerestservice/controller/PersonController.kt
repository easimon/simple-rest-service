package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController {

  private companion object {
    private val ALL_PERSONS = listOf(
      "Thaddeus",
      "Ward",
      "Koit",
      "Luisa",
      "Sven",
      "Leofwine",
      "Lúcás",
      "Manoj",
      "Lanzo",
      "Hector"
    ).map { name -> Person(name) }
  }

  @GetMapping("/persons")
  fun getPersons(@RequestParam(name = "name", required = false, defaultValue = "") nameSubstring: String): List<Person> {
    return ALL_PERSONS.filter { it.name.contains(nameSubstring) }.sortedBy { it.name }
  }

  @GetMapping("/persons/{id}"  )
  fun getPersonById(@PathVariable(name = "id") id: String): Person? {
    return ALL_PERSONS.filter { it.id.toString() == id }.firstOrNull()
  }
}
