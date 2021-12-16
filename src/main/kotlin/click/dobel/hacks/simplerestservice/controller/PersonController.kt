package click.dobel.hacks.simplerestservice.controller

import click.dobel.hacks.simplerestservice.model.Person
import click.dobel.hacks.simplerestservice.repository.PersonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(
  private val repository: PersonRepository
) {
  @GetMapping("/persons")
  fun getPersons(@RequestParam(name = "name", required = false, defaultValue = "") nameSubstring: String) =
    repository.getPersons(nameSubstring)

  @GetMapping("/persons/{id}")
  fun getPersonById(@PathVariable(name = "id") id: String): Person? = repository.getPersonById(id)
}
