package com.lucky.base

import org.apache.spark.sql.SparkSession

/**
  * @author xiaoran
  * @date 2019/08/21
  *
  *       广播变量
  *
  *       共享变量，工作节点只能读取广播变量的值，不能变更，若强制变更，则只改变当前副本
  *
  *       1.创建
  *       SparkContext.broadcast[T]
  *
  *       2.使用
  *
  *
  */
object SparkBroadcast {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("about broadcast").master("local").getOrCreate()

    val input = "/Users/xiaoran/bigdata/file/txt/input/input1.txt"

    val allType = spark.sparkContext.textFile(input).map {
      line => {
        val typeNum = line.split('|')(0)
        line
      }
    }

    //创建
    val broadcastValue = spark.sparkContext.broadcast(allType)

    val resultRDD = spark.sparkContext.textFile(input).flatMap {
      row =>
        //使用
        val broadcast = broadcastValue.value
        broadcast.collect()
    }



  }
}
