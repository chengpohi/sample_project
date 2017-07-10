import Settings._

scalaVersion := "2.12.1"

lazy val app = project
  .in(file("app"))
  .settings(commonSetting: _*)
  .settings(libraryDependencies ++= commonDependencies ++ akkaDependencies)
  .settings(
    name := "scala99",
    version := "0.1"
  )
