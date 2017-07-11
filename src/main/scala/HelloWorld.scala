import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{complete, redirect}
import akka.http.scaladsl.server._
import akka.stream.ActorMaterializer
import org.apache.log4j.LogManager

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object HelloWorld {
  private val logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    bootstrap()
  }

  implicit def rejectionHandler: RejectionHandler =
    RejectionHandler
      .newBuilder()
      .handle {
        case MissingCookieRejection(cookieName) =>
          complete(
            HttpResponse(BadRequest, entity = "No cookies, no service!!!"))
        case AuthorizationFailedRejection ⇒
          complete((Forbidden, "You're out of your depth!"))
        case AuthenticationFailedRejection(cause, challenge) =>
          complete((Forbidden, "Need Login"))
        case ValidationRejection(msg, _) ⇒
          complete((InternalServerError, "That wasn't valid! " + msg))
        case MethodRejection(method) =>
          complete((MethodNotAllowed, s"$method is invalid method"))
      }
      .handleNotFound {
        complete((NotFound, s""))
      }
      .result()

  def bootstrap(): Unit = {
    implicit val system = ActorSystem("sample_project")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val route: Route = Routes.routes

    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(
      CorsSupport.corsHandler(route),
      Config.BIND_IP,
      Config.LISTEN_PORT)

    logger.info(
      s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  }
}
