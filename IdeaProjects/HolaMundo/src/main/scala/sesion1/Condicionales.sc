val childrenNumber = 8

if(childrenNumber > 3){
  println("Es familia numerosa")
} else {
  println("No es familia numerosa")
}

// val isLargeFamily = if(childrenNumber>3) true else false // really?
val isLargeFamily = childrenNumber>3

val familyType:String = childrenNumber match {
  case 2 => "Normal"
  case 3 => "General"
  case 4 => "Especial"
  case x if x > 6 && x < 10 => "Mega"
  case _ => "Sin categorizar"
}

print(familyType)
