import com.typesafe.sbt.packager.SettingsHelper._



enablePlugins(JavaServerAppPackaging, SystemdPlugin)

makeDeploymentSettings(Universal, packageBin in Universal, "zip")

scriptClasspath in bashScriptDefines ~= (cp => "../conf" +: cp)


val deploy = taskKey[Unit]("deploy")

deploy := {
}

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
)().withUser((daemonUser in Linux).value)
  .withGroup((daemonGroup in Linux).value)
  .withPerms("755")
