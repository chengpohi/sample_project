import sbt._
import sbt.Keys._

object Settings {
  scalaVersion := "2.12.1"

  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )

  lazy val akkaDependencies = Seq(
    "com.typesafe" % "config" % "1.2.1",
    "com.typesafe.akka" %% "akka-slf4j" % "2.4.16",
    "com.typesafe.akka" %% "akka-http" % "10.0.1",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.0.9",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.1"
  )

  val commonDependencies = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.itv" %% "scalapact-scalatest" % "2.1.3" % "test",
    "org.scalaz" %% "scalaz-core" % "7.3.0-M9",
    "org.scalaz" %% "scalaz-effect" % "7.3.0-M9",
    "org.scalaz" %% "scalaz-concurrent" % "7.3.0-M9",
    "org.scalaz" %% "scalaz-iteratee" % "7.3.0-M9",
    "org.apache.logging.log4j" % "log4j-1.2-api" % "2.7",
    "org.apache.logging.log4j" % "log4j-api" % "2.7",
    "org.apache.logging.log4j" % "log4j-core" % "2.7" % "runtime",
    "org.json4s" %% "json4s-native" % "3.5.2"
  )

  val commonSetting = Seq(
    version := "1.0",
    scalaVersion := "2.12.1",
    scalacOptions ++= Seq("-language:implicitConversions",
                          "-language:higherKinds",
                          "-feature",
                          "-language:postfixOps"),
    initialCommands in console := "import scalaz._, Scalaz._",
    libraryDependencies ++= commonDependencies
  )
}
