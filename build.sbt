name := "longshore.info site"

version := "1.0"

scalaVersion := "2.11.8"

organization := "info.longshore"

val akkaHttpVersion = "3.0.0-RC1"

val scalatagsVersion = "0.6.0"

val yuiCompressorVersion = "2.4.8"

libraryDependencies ++= Vector(
  "com.lihaoyi" %% "scalatags" % scalatagsVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.yahoo.platform.yui" % "yuicompressor" % yuiCompressorVersion
)

mainClass := Some("longshore.info.site.Main")

scalacOptions ++= Vector(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Yrangepos",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked"
)
