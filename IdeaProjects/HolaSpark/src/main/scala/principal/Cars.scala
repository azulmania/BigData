package principal

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Cars extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    .appName("Cars App")
    .config("spark.master", "local")
    .getOrCreate()

  // Creamos un dataframe
  val carsDF = spark.read
    .option("inferSchema", "true")
    .json("data/cars.json")

  carsDF.show()

  // agregar columnas
  carsDF
    .withColumn("Weight_in_kgs", col("Weight_in_lbs") * 2.2)
    .show()
  // renombrar columnas
  carsDF
    .withColumnRenamed("Horsepower", "Power")
    .show()
  // Borrar columnas
  carsDF
    .drop("Year", "Displacement")
    .show()

  spark.sparkContext.stop()
}
