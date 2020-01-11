package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

// Encontrar estacion que ha medido la temperatura minima
// map para quitar columnas, filter para quitar registros
object TemperatureApp {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc:SparkContext = new SparkContext("local[*]", "Temperature App")
    val dataRDD = sc.textFile("data/1800.csv")
    println(s"Numero de items: ${dataRDD.count()}")

    val result = dataRDD
      .map(toData)
      .filter(tuple => tuple._2 == "TMIN")
      .map(tuple => (tuple._3, tuple._1))
      .sortByKey()
      .take(1)

    result.foreach(println)

  }

  def toData(record:String) = {
    val info = record.split(",")
    // (stationId, flag, temperature)
    (info(0), info(2), info(3).toFloat)
  }

}
