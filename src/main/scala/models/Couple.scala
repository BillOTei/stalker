package models

import play.api.libs.json.{Json, OFormat}

case class Couple(id: String, pass: String)

object Couple {
  implicit val coupleFormat: OFormat[Couple] = Json.format[Couple]
}