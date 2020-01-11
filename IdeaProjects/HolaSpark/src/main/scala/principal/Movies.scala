package principal

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.log4j.{Level, Logger}

object Movies extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession.builder()
    .appName("Movies App")
    .config("spark.master", "local")
    .getOrCreate()

  // Creamos un dataframe
  val moviesDF = spark.read
    .option("inferSchema", "true")
    .json("data/movies.json")

  moviesDF.show()
  moviesDF.printSchema()


  //  requiere importar implicits
//    moviesDF
//      .select(
//        $"IMDB Rating",
//      'Major Genre
//      )
//      .show(false)

  val selectedMoviesDF = moviesDF.select(
    col("Director"),
    col("IMDB_Rating"),
    col("Title"),
    col("US_Gross"),
    col("Major_Genre")
  ).cache()

  val aggregationsDF = selectedMoviesDF.groupBy(col("Major_Genre"))
    .agg(avg("IMDB_Rating"), sum("US_Gross").as("Total_Gross"))
    .orderBy("Total_Gross")
    .show()

  // moviesDF.selectExpr("Title").show()

  // Mostrar el sumatorio de beneficios totales de todas las pelis (DVD, US, World)
  moviesDF
    .select(
      (col("US_DVD_Sales") + col("US_Gross") + col("Worldwide_Gross"))
        .as("Total_Gross")
    )
    .select(sum("Total_Gross"))
    .show()

  // Obtener los directores de las distintas peliculas
  moviesDF
      .select(col("Director"))
      .distinct()
      .show()

  moviesDF
    .select(countDistinct(col("Director")))
    .show()


  spark.sparkContext.stop()
}
