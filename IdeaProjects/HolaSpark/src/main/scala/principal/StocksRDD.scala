package principal

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._

object StocksRDD {

  case class StockValue(symbol: String, data: String, price: Double)


  def main(args: Array[String]) = {

    val spark = SparkSession.builder()
      .appName("Stocks RDD")
      .config("spark.master", "local")
      .getOrCreate()

    import spark.implicits._

    val sc = spark.sparkContext

    // Esto es para cuando quiero subir un fichero al cluster
//    val stockList = Source.fromFile("data/stocks.csv")
//      .getLines()
//      .drop(1)
//      .map(line => line.split(","))
//      .toList
//    val stockRDD = sc.parallelize(stockList)


    // Esto vale en local, pero no en cluster.
    val stockRDD = sc.textFile("data/stocks.csv")
      .map(line => line.split(","))
      .filter(items => items(0).toUpperCase() == items(0))
      .map(items => StockValue(items(0), items(1), items(2).toDouble))

    val itemsMSFTCount = stockRDD.filter(value => value.symbol == "MSFT").count()

    val itemsGroupedBySymbol =  stockRDD.groupBy(_.symbol)

    // Persistir la informacin de memoria a las particiones de disco.
    val repartitionRDD = itemsGroupedBySymbol.coalesce(15)

// no funciona (aun)
//    repartitionRDD.toDF.write.mode(SaveMode.Overwrite).parquet("data/groupedStocks")


    val stocksDF = spark.read
        .option("header", "true")
        .option("inferSchema", "true")   // schema gasta muchos recursos y ralentiza, tomarlo en cuenta con muchos datos.
        .csv("data/stocks.csv")
        .write.mode(SaveMode.Overwrite)
        .parquet("data/groupedStocks")

    val numbersRDD = spark.sparkContext.parallelize(List(1,2,3,4,5))
    val numbersDF = spark.createDataFrame(numbersRDD, List.getClass)
    println("numbersDF: ")
    numbersDF.show()

    val numbersDS = spark.createDataset(numbersRDD)
//    val numDF = spark.createDataset(numbersDS)


    sc.stop()

  }

}
