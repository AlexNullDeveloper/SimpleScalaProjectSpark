package ru.talismanov

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.NGram

object Main {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("temp-project").setMaster("local[2]")

    val sc = new SparkContext(conf)
    val sQLContext = new SQLContext(sc);

    val wordDataFrame = sQLContext.createDataFrame(Seq((0, Array("Hi", "I", "want", "to", "learn", "data", "science")),
      (1, Array("I", "want", "to", "use", "Scala")),
      (2, Array("This", "is", "easy", "and", "fun")))).toDF("label", "words")

    val ngram = new NGram().setInputCol("words").setOutputCol("ngrams")
    val ngramDataFrame = ngram.transform(wordDataFrame)

    ngramDataFrame.take(3).map( line => line.getAs[Stream[String]]("ngrams").toList).foreach(println)

    println("done")
  }
}
