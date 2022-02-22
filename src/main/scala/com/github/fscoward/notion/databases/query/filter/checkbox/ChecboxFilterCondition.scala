package com.github.fscoward.notion.databases.query.filter.checkbox

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait CheckboxFilterCondition

case class EqualsCondition(equals: Boolean) extends CheckboxFilterCondition
case class DoesNotEqualCondition(does_not_equal: Boolean)
    extends CheckboxFilterCondition

implicit val conditionEncoder: Encoder[CheckboxFilterCondition] =
  Encoder.instance {
    case equalsCondition @ EqualsCondition(_) => equalsCondition.asJson
    case doesNotEqualCondition @ DoesNotEqualCondition(_) =>
      doesNotEqualCondition.asJson
  }
