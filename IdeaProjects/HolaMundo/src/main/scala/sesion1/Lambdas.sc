val process = (x:Int) => x + 1
// val process:Int => = (x:Int) => x + 1
val process2:(Int, Int) => Int =  (x:Int, y:Int) => x + y

val res = process(10)
print(s"Resultado: ${res}")
var res2 = process2(10,10)
print(s"Resultado: ${res2}")


// HOF: High Order Function
def process3(number: Int, process:Int => Boolean) = {
  process(number)
}

val res3 = process3(10, (x:Int) => x>1 )
println(res3)

// range
for(item <- 1 to 10){
  println(item)
}
// para que no tome en cuenta el limite
for(item <- 1 until 10){
  println(item)
}

val list10000 = List.range(0,10000)
println(list10000.size)

val pairList = list10000.filter(x => x%2 == 0 )
println(pairList.size)


