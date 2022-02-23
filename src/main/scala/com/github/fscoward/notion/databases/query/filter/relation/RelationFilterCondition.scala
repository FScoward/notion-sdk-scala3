package com.github.fscoward.notion.databases.query.filter.relation

import io.circe.Encoder

import java.util.UUID
import io.circe.generic.auto.*
import io.circe.syntax.*

trait RelationFilterCondition

case class ContainsCondition(contains: String) extends RelationFilterCondition {
  require(UUID.fromString(contains).version() == 4)
}
case class DoesNotContainsCondition(does_not_contain: String)
    extends RelationFilterCondition {
  require(UUID.fromString(does_not_contain).version() == 4)
}
case class IsEmptyCondition(is_empty: Boolean = true)
    extends RelationFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends RelationFilterCondition {
  require(is_not_empty)
}

implicit val conditionEncoder: Encoder[RelationFilterCondition] =
  Encoder.instance {
    case containsCondition: ContainsCondition =>
      containsCondition.asJson
    case doesNotContainsCondition: DoesNotContainsCondition =>
      doesNotContainsCondition.asJson
    case isEmptyCondition: IsEmptyCondition       => isEmptyCondition.asJson
    case isNotEmptyCondition: IsNotEmptyCondition => isNotEmptyCondition.asJson
  }
