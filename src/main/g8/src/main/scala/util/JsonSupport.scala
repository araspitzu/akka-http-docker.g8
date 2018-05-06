package util

import java.time.LocalDateTime
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.JsonAST.{ JNull, JString }
import org.json4s.{ CustomSerializer, DefaultFormats, Formats, native }

trait JsonSupport extends Json4sSupport {

  implicit val serialization = native.Serialization

  //Append custom formats here
  implicit def json4sFormats: Formats = DefaultFormats ++ List(LocalDateTimeSerializer)

  case object LocalDateTimeSerializer extends CustomSerializer[LocalDateTime](format => (
    {
      case JString(p) => LocalDateTime.parse(p)
      case JNull      => null
    },
    {
      case date: LocalDateTime => JString(date.toString)
    }
  ))

}
