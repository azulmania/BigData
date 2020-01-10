

val numbers:List[Int] = List(1,2,3,4,5,6)
print(s"El primer elemento es ${numbers(0)}")
print(s"El primer elemento es ${numbers.head}")
print(s"El primer elemento no es ${numbers.tail}")

for(number <- numbers){
  println(number)
}

def showData(list:List[Int]) = {
  for(number <- list){
    println(number)
  }
}

showData(numbers)

def addNumber(list:List[Int], number: Int) = {
  list.+:(number) // prepend (no hace nada)
  list.:+(number) // append (solo la ultima linea retorna valor)
}

val newList = addNumber(numbers, 666)
showData(newList)




