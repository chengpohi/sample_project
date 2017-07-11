import Settings._

name := "sample_project"

version := "0.1"

scalaVersion := "2.12.1"

mainClass := Some("HelloWorld")

libraryDependencies ++= commonDependencies ++ akkaDependencies
