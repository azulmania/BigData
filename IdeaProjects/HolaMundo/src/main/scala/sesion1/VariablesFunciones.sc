var nombre:String = "Dato"  // val es inmutable, var es mutable.
nombre = "Valor"
println(nombre)

// creacion de una funcion
// Deberia llevar :Unit, pero si no se pone no pasa nada.
def mostrar()={
  print("Hola")
}

// Se puede invocar funcion sin parentesis si no hay parametos, pero pone warning.
mostrar

// Sobrecarga de metodo
def mostrar(mensaje: String) = {
  print(mensaje)
}

// Ya no me deja ejecutar solo mostrar.
mostrar(_)
mostrar("Que pedo, wey")

def escribir(mensaje: String) = print(s"Mensaje ${mensaje}")

escribir("Estamos en la penultima sesion de escala")

// Expresiones e instrucciones
// Una expresion es cuando hay una evaluacion y se obtiene un resultado. Ej.
val resultado = 1 + 4
// Esto no es una expresion, es una instruccion
print("Test")

// Esto es un bloque porque abre y cierra llave.
val valor = {
  // variables locales al bloque
  val x = 0
  val res = 4 + x
  res  // Retorna la ultima linea del bloque, es como poner return res
}
print(valor)

def saludar ():String = {
  return "Jie jieeeEEEEE"
}
saludar

// Esto tambien es una funcion, aunque parezca una asignacion de valor
def saludar = "Jie jieeeEEEEE"
saludar
print(saludar)



