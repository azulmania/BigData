package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object EcommerceApp {

  def main(args: Array[String]): Unit = {
    // Solo dejamos los logs de ERROR.
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc:SparkContext = new SparkContext("local[*]", "Ecommerce App")
    val dataRDD = sc.textFile("data/customer-orders.csv")
    val numItems = dataRDD.count()
    println(s"Numero de items: ${numItems}")
    sc.stop()
  }

}
