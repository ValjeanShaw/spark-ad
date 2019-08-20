name := "spark-ad"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0" withSources() exclude("org.slf4j", "slf4j-api"),
  "org.apache.spark" %% "spark-sql" % "2.3.0" withSources()
)