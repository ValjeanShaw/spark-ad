package com.lucky.base

import org.apache.spark.sql.SparkSession

/**
  * @author xiaoran
  * @date 2019/08/20
  *
  *       spark累加器
  *
  *       共享变量,在工作节点上的任务不能读取累加器的值，只能写入
  *
  * 1.创建
  * SparkContext.accumulator[T]   过期
  * SparkContext.longAccumulator  等，直接指定类型
  *
  * 2.使用
  *       增加 +=
  *       访问 value
  *
  *
  */
object SparkAccumulator {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("about accumulator").master("local").getOrCreate()
    val input = "/Users/xiaoran/bigdata/file/txt/input/input1.txt"

    val zeroCountAccumulator = spark.sparkContext.longAccumulator

    val allCount = spark.sparkContext.textFile(input).map {
      line => {
        val typeNum = line.split('|')(0)
        if (typeNum.equals("0"))
          zeroCountAccumulator.add(1)
        line
      }
    }.count()
    val zeroCount = zeroCountAccumulator.value
    println(s"总数：$allCount type为0：$zeroCount  占比：$zeroCount/$allCount")

  }
}
