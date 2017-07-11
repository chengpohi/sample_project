import akka.http.scaladsl.common.{
  EntityStreamingSupport,
  JsonEntityStreamingSupport
}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, NullOptions, RootJsonFormat}

trait JSONSerializer
    extends SprayJsonSupport
    with DefaultJsonProtocol
    with NullOptions {
  implicit val buildInfoFormat: RootJsonFormat[BuildInfo] = jsonFormat6(
    BuildInfo)
  implicit val jsonStreamingSupport: JsonEntityStreamingSupport =
    EntityStreamingSupport.json()
}
