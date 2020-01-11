package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

// conteo de palabras de texto.
object WordCountApp extends App {


  def normalize(text:String) = {
    text.split("\\W+").foreach(s => s.toLowerCase())
  }

  Logger.getLogger("org").setLevel(Level.ERROR)
  val sc:SparkContext = new SparkContext("local[*]", "Temperature App")
  val dataRDD = sc.textFile("../../PycharmProjects/curso/data/quijote.txt")
  println(s"Numero de lineas: ${dataRDD.count()}")

  // Map retorna Array de Strings, flatMap retorna Strings
  val wordsRDD = dataRDD.flatMap(line => line.split("\\W+"))
  val lowerCaseWordsRDD = wordsRDD.map(w => w.toLowerCase())
  val wordCounterRDD = lowerCaseWordsRDD
    .map(w => (w, 1))
    .reduceByKey((x,y) => x + y)

  for(data <- wordCounterRDD.take(200)){
    println(s"La palabra ${data._1} aparece ${data._2} veces")
  }

}
