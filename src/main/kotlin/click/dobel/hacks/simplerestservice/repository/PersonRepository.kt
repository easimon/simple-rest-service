package click.dobel.hacks.simplerestservice.repository

import click.dobel.hacks.simplerestservice.model.Person
import org.springframework.stereotype.Repository

@Repository
class PersonRepository {

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

  fun getPersons(nameSubstring: String): List<Person> {
    return ALL_PERSONS.filter { it.name.contains(nameSubstring) }.sortedBy { it.name }
  }

  fun getPersonById(id: String): Person? {
    return ALL_PERSONS.filter { it.id.toString() == id }.firstOrNull()
  }
}
