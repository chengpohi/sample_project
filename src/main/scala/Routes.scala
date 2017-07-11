import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

trait HelloWorldController {
  def hello: Route = pathSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`application/json`, {
        "Hello World"
      }))
    }
  }
}

object Routes extends HelloWorldController {
  def routes: Route = hello
}
