package com.github.fscoward.notion.databases.query

import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

trait Condition

case class EqualsCondition(equals: Boolean) extends Condition
case class DoesNotEqualCondition(does_not_equal: Boolean) extends Condition
case class ContainsCondition(contains: String) extends Condition
case class DoesNotContainCondition(does_not_contain: String) extends Condition
case class StartsWithCondition(starts_with: String) extends Condition
case class EndsWithCondition(ends_with: String) extends Condition
case class IsEmptyCondition(is_empty: Boolean = true) extends Condition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true) extends Condition {
  require(is_not_empty)
}

implicit val conditionEncoder: Encoder[Condition] = Encoder.instance {
  case equalsCondition @ EqualsCondition(_) => equalsCondition.asJson
  case doesNotEqualCondition @ DoesNotEqualCondition(_) =>
    doesNotEqualCondition.asJson
  case containsCondition @ ContainsCondition(_) => containsCondition.asJson
  case doesNotContainCondition @ DoesNotContainCondition(_) =>
    doesNotContainCondition.asJson
  case startWithCondition @ StartsWithCondition(_) => startWithCondition.asJson
  case endsWithCondition @ EndsWithCondition(_)    => endsWithCondition.asJson
  case isEmptyCondition @ IsEmptyCondition(_) =>
    Json.obj(("is_empty", Json.fromBoolean(true)))
  case isNotEmptyCondition @ IsNotEmptyCondition(_) =>
    Json.obj(("is_not_empty", Json.fromBoolean(true)))
}
