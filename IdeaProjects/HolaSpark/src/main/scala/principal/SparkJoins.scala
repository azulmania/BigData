package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SparkJoins {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder()
      .appName("Joins App")
      .config("spark.master", "local")
      .getOrCreate()

    // Ingesta en DF
    // Guitarristas
    val guitarPlayersDF = spark.read
        .option("inferSchema", "true")
        .json("data/guitarPlayers.json")

    // Bandas
    val bandsDF = spark.read
      .option("inferSchema", "true")
      .json("data/bands.json")

    // Guitarras
    val guitarsDF = spark.read
      .option("inferSchema", "true")
      .json("data/guitars.json")

    val joinCondition = guitarPlayersDF.col("band") === bandsDF.col("id")

    val guitarPlayersAndBandsDF = guitarPlayersDF.join(bandsDF, joinCondition,"left_outer")  //inner

    guitarPlayersAndBandsDF.show()


  }

}
