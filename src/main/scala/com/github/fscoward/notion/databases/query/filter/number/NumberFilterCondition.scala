package com.github.fscoward.notion.databases.query.filter.number

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait NumberFilterCondition

case class EqualsCondition(equals: Int) extends NumberFilterCondition
case class DoesNotEqualCondition(does_not_equal: Int)
    extends NumberFilterCondition
case class GreaterThanCondition(greater_than: Int) extends NumberFilterCondition
case class LessThanCondition(less_than: Int) extends NumberFilterCondition
case class GreaterThanEqualToCondition(greater_than_or_equal_to: Int)
    extends NumberFilterCondition
case class LessThanEqualToCondition(less_than_or_equal_to: Int)
    extends NumberFilterCondition
case class IsEmptyCondition(is_empty: Boolean = true)
    extends NumberFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends NumberFilterCondition {
  require(is_not_empty)
}

implicit val conditionEncoder: Encoder[NumberFilterCondition] =
  Encoder.instance {
    case equalsCondition @ EqualsCondition(_) => equalsCondition.asJson
    case doesNotEqualCondition @ DoesNotEqualCondition(_) =>
      doesNotEqualCondition.asJson
    case isEmptyCondition @ IsEmptyCondition(_) =>
      Json.obj(("is_empty", Json.fromBoolean(true)))
    case isNotEmptyCondition @ IsNotEmptyCondition(_) =>
      Json.obj(("is_not_empty", Json.fromBoolean(true)))
  }
