import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

case class BuildInfo(buildNumber: Option[String],
                     branch: Option[String],
                     buildSourceJDK: Option[String],
                     buildTargetJDK: Option[String],
                     buildDate: Option[String],
                     change: Option[String])

trait BuildInfoController extends JSONSerializer {

  def buildInfo: Route = get {
    pathSingleSlash {
      complete(getBuildInfoFromManifest)
    } ~ path("/info") {
      complete(getBuildInfoFromManifest)
    }
  }

  def getBuildInfoFromManifest: BuildInfo = {
    val manifest = new java.util.jar.Manifest(
      this.getClass.getResource("/META-INF/MANIFEST.MF").openStream())
    val buildNumber = manifest.getMainAttributes.getValue("Build_Number")
    val branch = manifest.getMainAttributes.getValue("Branch")
    val buildDate = manifest.getMainAttributes.getValue("Build-Date")
    val buildSourceJDK =
      manifest.getMainAttributes.getValue("X-Compile-Source-JDK")
    val buildTargetJDK =
      manifest.getMainAttributes.getValue("X-Compile-Target-JDK")
    val change = manifest.getMainAttributes.getValue("Change")
    BuildInfo(Option(buildNumber),
              Option(branch),
              Option(buildSourceJDK),
              Option(buildTargetJDK),
              Option(buildDate),
              Option(change))
  }
}

object Routes extends BuildInfoController {
  def routes: Route = buildInfo
}
