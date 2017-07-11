import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class BuildInfoControllerTest
    extends WordSpec
    with Matchers
    with ScalatestRouteTest {
  val buildInfoController = new BuildInfoController {}
  "BuildInfoController" should {

    "get project build infos" in {
      Get() ~> buildInfoController.buildInfo ~> check {
        responseAs[String] shouldEqual """{"branch":null,"buildSourceJDK":null,"change":null,"buildTargetJDK":null,"buildDate":null,"buildNumber":null}"""
      }
    }
  }

}
