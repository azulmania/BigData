package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object EcommerceApp {

  def extractUserInfo(record:String) = {
    val info = record.split(",")
    (info(0).toInt, info(2).toFloat)
  }

  def main(args: Array[String]): Unit = {
    // Solo dejamos los logs de ERROR.
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc:SparkContext = new SparkContext("local[*]", "Ecommerce App")
    val dataRDD = sc.textFile("data/customer-orders.csv")
    val numItems = dataRDD.count()
    println(s"Numero de items: ${numItems}")

    // No nos interesa el ID, lo quitamos
    val dataUserInfoRDD = dataRDD.map(extractUserInfo)
    val totalAmountUserRDD = dataUserInfoRDD.reduceByKey((x,y) => x+y)

    val results = totalAmountUserRDD.collect()
    // results.foreach(println)
    println(s"Total de resultados: ${results.size}")

    // Obtener los 10 clientes que mas han gastado en ecomerce
    val totalAmountUserSortedRDD = totalAmountUserRDD
      .map(tuple => (tuple._2, tuple._1))
      .sortByKey(false)

    val top10 = totalAmountUserSortedRDD.take(10)
    top10.foreach(println)

    sc.stop()
  }


}
