package com.github.fscoward.notion.databases.query.filter.text

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait TextFilterCondition

case class EqualsCondition(equals: Boolean) extends TextFilterCondition
case class DoesNotEqualCondition(does_not_equal: Boolean)
    extends TextFilterCondition
case class ContainsCondition(contains: String) extends TextFilterCondition
case class DoesNotContainCondition(does_not_contain: String)
    extends TextFilterCondition
case class StartsWithCondition(starts_with: String) extends TextFilterCondition
case class EndsWithCondition(ends_with: String) extends TextFilterCondition
case class IsEmptyCondition(is_empty: Boolean = true)
    extends TextFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends TextFilterCondition {
  require(is_not_empty)
}

implicit val conditionEncoder: Encoder[TextFilterCondition] = Encoder.instance {
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
