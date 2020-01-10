// Definicion de clase
class Car(b:String, m:String){
  val brand:String = b
  val model:String = m
  def start() = {
    println(s"Coche ${this.brand} ${this.model} -> Ruuun, Ruuuuuunnnn...")
  }
}

// Crear instancia de coche
val seatLeon = new Car("Seat", "Leon")

println(seatLeon.brand)
// Si estan declarados como var, se pueden modificar, como val no
// seatLeon.brand = "Audi"
// println(seatLeon.brand)
seatLeon.start()

case class Driver(name:String, dni:String)

// A la case class no hace falta poner new
val juan = Driver("Juan", "666H")
val maria = Driver("Maria", "333J")

print(s"${juan.name} -> ${juan.dni}")

case class MutableDriver(var name:String, var dni:String)

val drivers:List[Driver] = List(juan, maria)
val drivers2:List[(String,Driver)] = List(("String", juan), ("Cadena", maria))







