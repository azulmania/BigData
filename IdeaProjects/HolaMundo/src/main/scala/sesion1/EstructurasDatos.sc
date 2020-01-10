

val numbers: List[Int] = List(1, 2, 3, 4, 5, 6)
print(s"El primer elemento es ${numbers(0)}")
print(s"El primer elemento es ${numbers.head}")
print(s"El primer elemento no es ${numbers.tail}")

for (number <- numbers) {
  println(number)
}

def showData(list: List[Int]) = {
  for (number <- list) {
    println(number)
  }
}

showData(numbers)

def addNumber(list: List[Int], number: Int) = {
  list.+:(number) // prepend (no hace nada)
  list.:+(number) // append (solo la ultima linea retorna valor)
}

val newList = addNumber(numbers, 666)
showData(newList)

// Diccionarios
val europeanCities: Map[String, Long] = Map(
  "Madrid" -> 666,
  "Dublin" -> 1000,
  "Munchen" -> 2000,
  "London" -> 3000
)

val usaCities: Map[String, Long] = Map(
  "NY" -> 1000,
  "Boston" -> 2000,
  "Los Angeles" -> 3000
)

val BCNPopulation = europeanCities.get("Madrid")

def showCityInformation(item:(String, Long)) = {
  println(item._2)
}

for(item <- usaCities){
  showCityInformation(item)
}

def showCityInformation(population:Long) = {
  println(population)
}

for((_, population) <- usaCities){
  showCityInformation(population)
}

usaCities.keys.foreach(city => println(city.toUpperCase))

val correctedPopulations = usaCities.values.map(qty => qty - 500)
// the same: correctedPopulations = usaCities.values map (qty => qty - 500)
correctedPopulations.foreach(x => println(x))

// Crear un diccionario mundial que incluya europa y estados unidos,
// Mostrar el listado de poblacion.
(europeanCities ++ usaCities)  // concatenacion de diccionarios y conjuntos
  .foreach(x => println(s"Ciudad: ${x._1} - Poblacion: ${x._2}"))

val tuple = (1,2,3)
// En scala empieza por el 1, no por el 0
val value1 = tuple._1
val value2 = tuple._2
val value3 = tuple._3

// destructuring: requiere parentesis
val (v1, v2, v3) = tuple

// La tupla no tiene foreach, hay que pasar por "productIteration"
tuple.productIterator.foreach(e => println(e))

// Map mutable
import scala.collection.mutable.Map

val students:Map[String, String] = Map()
// val no es reasignable, esto no se puede hacer
// students = Map()

def register(list:Map[String, String], name:String, id:String) = {
  list += (id -> name)
}

def showStudents(students:Map[String, String]) = {
  students.foreach(s => println(s._2))
}

// Es recomendable trabajar con inmutables. Las colecciones mutables usar con cuidado.
showStudents(students)
register(students, "Misha", "666H")
showStudents(students)


// Conjuntos (sets)
val set = Set(1,1,1,1,1,1,1,1,1,2)
println(set.size)

val setList = set.toList
setList.size
setList.exists(item => item == 1)



