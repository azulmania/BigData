import findspark

findspark.init()
from pyspark import SparkContext, SparkConf

if __name__ == "__main__":
    config = SparkConf().setAppName("Quijote App")
    sc = SparkContext(conf=config)

    quijote_RDD = sc.textFile("data/quijote.txt")
    palabras_RDD = quijote_RDD.flatMap(lambda linea: linea.split())
    palabras_RDD.countByValue()
    contador_palabras = palabras_RDD.countByValue()

    for value, count in contador_palabras.items():
        palabra_limpia = value.encode('ascii', 'ignore')
        if palabra_limpia:
            print(palabra_limpia.decode(), str(count))
