import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.json4s.native.JsonMethods._
import org.json4s.{DefaultFormats, _}
import org.scalatest.{Matchers, WordSpec}

class BuildInfoControllerTest
    extends WordSpec
    with Matchers
    with ScalatestRouteTest {
  val buildInfoController = new BuildInfoController {}

  implicit val formats = DefaultFormats

  "BuildInfoController" should {
    "get project build infos" in {
      Get() ~> buildInfoController.buildInfo ~> check {
        parse(responseAs[String]).extract[BuildInfo] shouldEqual BuildInfo(
          None,
          None,
          None,
          None,
          None,
          None)
      }
    }
  }

}
