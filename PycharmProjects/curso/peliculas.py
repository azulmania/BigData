import findspark

findspark.init()

from pyspark.sql import SparkSession
from pyspark.sql import Row

if __name__ == "__main__":
    spark = SparkSession.builder \
        .appName("Peliculas app") \
        .getOrCreate()

    lineas_peliculas_RDD = spark.sparkContext.textFile("data/ml-100k/u.data")
    peliculas_RDD = lineas_peliculas_RDD \
        .map(lambda linea: Row(pelicula_id=int(linea.split()[1])))
    peliculas_DF = spark.createDataFrame(peliculas_RDD)

    peliculas_top = peliculas_DF.groupBy("pelicula_id").count().orderBy("count", ascending=False)
    #peliculas_top.show()
    peliculas_top_5 = peliculas_top.take(5)

    for pelicula in peliculas_top_5:
        print(pelicula)


