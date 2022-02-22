package com.github.fscoward.notion.databases.query.filter.date

import io.circe.{Encoder, Json}

import java.time.format.DateTimeFormatter
import java.time.OffsetDateTime

trait DateFilterCondition

case class EqualsCondition(equals: OffsetDateTime) extends DateFilterCondition
case class BeforeCondition(before: OffsetDateTime) extends DateFilterCondition
case class AfterCondition(after: OffsetDateTime) extends DateFilterCondition
case class OnOrBeforeCondition(on_or_before: OffsetDateTime)
    extends DateFilterCondition
case class IsEmptyCondition(is_empty: Boolean = true)
    extends DateFilterCondition {
  require(is_empty)
}
case class IsNotEmptyCondition(is_not_empty: Boolean = true)
    extends DateFilterCondition {
  require(is_not_empty)
}
case class OnOrAfterCondition(on_or_after: OffsetDateTime)
    extends DateFilterCondition

implicit val conditionEncoder: Encoder[DateFilterCondition] =
  Encoder.instance {
    case equalsCondition @ EqualsCondition(equals) =>
      Json.fromString(equals.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    case beforeCondition @ BeforeCondition(before) =>
      Json.fromString(before.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    case afterCondition @ AfterCondition(after) =>
      Json.fromString(after.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    case onOrBeforeCondition @ OnOrBeforeCondition(on_or_before) =>
      Json.fromString(
        on_or_before.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      )
    case isEmptyCondition @ IsEmptyCondition(_) =>
      Json.obj(("is_empty", Json.fromBoolean(true)))
    case isNotEmptyCondition @ IsNotEmptyCondition(_) =>
      Json.obj(("is_not_empty", Json.fromBoolean(true)))
    case onOrAfterCondition @ OnOrBeforeCondition(on_or_after) =>
      Json.fromString(
        on_or_after.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      )
  }
