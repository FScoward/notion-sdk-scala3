package com.github.fscoward.notion.databases.query.filter.select

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait SelectFilterCondition

case class EqualsCondition(equals: Boolean) extends SelectFilterCondition
case class DoesNotEqualCondition(does_not_equal: Boolean)
    extends SelectFilterCondition
case class IsEmptyCondition(is_empty: Boolean = true)
    extends SelectFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends SelectFilterCondition {
  require(is_not_empty)
}

implicit val conditionEncoder: Encoder[SelectFilterCondition] =
  Encoder.instance {
    case equalsCondition @ EqualsCondition(_) => equalsCondition.asJson
    case doesNotEqualCondition @ DoesNotEqualCondition(_) =>
      doesNotEqualCondition.asJson
    case isEmptyCondition @ IsEmptyCondition(_) =>
      Json.obj(("is_empty", Json.fromBoolean(true)))
    case isNotEmptyCondition @ IsNotEmptyCondition(_) =>
      Json.obj(("is_not_empty", Json.fromBoolean(true)))
  }
