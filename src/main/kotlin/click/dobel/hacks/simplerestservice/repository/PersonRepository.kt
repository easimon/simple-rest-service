package click.dobel.hacks.simplerestservice.repository

import click.dobel.hacks.simplerestservice.model.Person
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PersonRepository {

  private companion object {
    private val ALL_PERSONS = mapOf(
      "582e10df-7231-4993-ad14-986e6abbbe57" to "Thaddeus",
      "a4fd8cf5-f829-4844-8bb6-1d576d9f3ca9" to "Ward",
      "16b80b34-f26b-485a-8a93-e9981503d9f0" to "Koit",
      "4fdb7d8d-7e48-4942-bcda-aac1a294479c" to "Luisa",
      "b00b7798-4bc9-49de-aa23-a2d3aca531dc" to "Sven",
      "d767af9b-1300-4850-8326-648b140116b0" to "Leofwine",
      "20023b8c-3c5a-49dd-a876-193bad783ae1" to "Lúcás",
      "5190f1e4-0b1b-43ad-8326-c0c42fd2bfbf" to "Manoj",
      "f68e71c7-5541-45a3-8d78-b0d1424a7080" to "Lanzo",
      "17870d3b-45a4-4650-875c-103ab6d21d7d" to "Hector",
    ).map { Person(UUID.fromString(it.key), it.value) }
  }

  fun getPersons(nameSubstring: String): List<Person> {
    return ALL_PERSONS.filter { it.name.contains(nameSubstring) }.sortedBy { it.name }
  }

  fun getPersonById(id: String): Person? {
    return ALL_PERSONS.filter { it.id.toString() == id }.firstOrNull()
  }
}
