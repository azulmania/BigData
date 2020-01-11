package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

// Obtener la temperatura minima de cada una de las estaciones
// reduce para quedarnos con la minima de cada estacion.
object Meteo extends App {

  def filterInfo(record:String) = {
    val info = record.split(",")
    (info(0), info(2), info(3).toFloat)
  }

  Logger.getLogger("org").setLevel(Level.ERROR)
  val sc:SparkContext = new SparkContext("local[*]", "Meteo App")
  val dataRDD = sc.textFile("data/1800.csv")

  val filteredInfoRDD = dataRDD map filterInfo
  val tMinInfoRDD = filteredInfoRDD filter (item => item._2 == "TMIN")
  val stationInfoRDD = tMinInfoRDD map (item => (item._1, item._3))
  // stationInfoRDD.take(5).foreach(println)

  // reduceByKey genera por cada clave un array de valores y luego los simplifica.
  // ej. ITE00100554: [-75, -1, -2, -3] -> (ITE00100554, -75)
  // stationInfoRDD.reduceByKey((x, y) => if(x < y) x else y)
  val minTempByStationRDD = stationInfoRDD.reduceByKey((x, y) => scala.math.min(x,y))
  minTempByStationRDD.collect().foreach(println)

}


