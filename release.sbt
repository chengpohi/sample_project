import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.typesafe.sbt.packager.SettingsHelper._
import sbt.Package.ManifestAttributes

import scala.util.Try

val buildNumber =
  Try(System.getenv().get("BUILD_NUMBER").trim).getOrElse("")

val javaVersion = System.getProperties.getProperty("java.version").trim

val branchName = Try(System.getenv().get("GIT_BRANCH").trim).getOrElse("")

val commitNumber = Try(System.getenv().get("GIT_COMMIT").trim).getOrElse("")

val buildDate = LocalDateTime
  .now()
  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

packageOptions := Seq(
  ManifestAttributes(
    ("Change", commitNumber),
    ("X-Compile-Target-JDK", javaVersion),
    ("X-Compile-Source-JDK", javaVersion),
    ("Build_Number", buildNumber),
    ("Branch", branchName),
    ("Build-Date", buildDate)
  ))

enablePlugins(JavaServerAppPackaging, SystemdPlugin)

makeDeploymentSettings(Universal, packageBin in Universal, "zip")

scriptClasspath in bashScriptDefines ~= (cp => "../conf" +: cp)

val deploy = taskKey[Unit]("deploy")

deploy := {}

defaultLinuxInstallLocation := "/apps/"
rpmPrefix := Some("/apps")
linuxPackageSymlinks := Seq.empty
defaultLinuxLogsLocation := defaultLinuxInstallLocation + "/" + name

// package settings
maintainer in Linux := "chengpohi@gmail.com"
packageSummary in Linux := "sample_project"
packageDescription := "sample_project"

// rpm specific
rpmRelease := "1"
rpmVendor := "sample.project"
rpmUrl := Some("sample_project")
rpmLicense := Some("Sample Project")

linuxPackageMappings += packageTemplateMapping(
  s"/apps/${(packageName in Linux).value}"
)()
  .withUser((daemonUser in Linux).value)
  .withGroup((daemonGroup in Linux).value)
  .withPerms("755")
