package com.github.fscoward.notion.databases.query.filter.file

import io.circe.Encoder
import io.circe.generic.auto.*
import io.circe.syntax.*

trait FileFilterCondition

case class IsEmptyCondition(is_empty: Boolean = true)
    extends FileFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends FileFilterCondition {
  require(is_not_empty)
}
implicit val conditionEncoder: Encoder[FileFilterCondition] =
  Encoder.instance {
    case isEmptyCondition: IsEmptyCondition       => isEmptyCondition.asJson
    case isNotEmptyCondition: IsNotEmptyCondition => isNotEmptyCondition.asJson
  }
