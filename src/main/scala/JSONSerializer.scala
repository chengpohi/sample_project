import org.json4s.{DefaultFormats, Formats, native}

trait JSONSerializer extends JSON4SSerializer {
  implicit val serialization = native.Serialization
  implicit val formats: Formats = DefaultFormats.preservingEmptyValues
}
