import findspark
import re

findspark.init()
from pyspark import SparkContext, SparkConf


def normalizar_palabras(texto):
    return re.compile(r'\W+', re.UNICODE).split(texto.lower())

if __name__ == "__main__":
    config = SparkConf().setAppName("Quijote App")
    sc = SparkContext(conf=config)

    quijote_RDD = sc.textFile("data/quijote.txt")
    palabras_RDD = quijote_RDD.flatMap(normalizar_palabras)

    contador_palabras = palabras_RDD\
        .map(lambda palabra: (palabra, 1))\
        .reduceByKey(lambda x, y: x+y)\
        .map(lambda tupla: (tupla[1], tupla[0]))\
        .sortByKey(ascending=False)

    for palabra, count in contador_palabras.collect():
        print(palabra, str(count))

