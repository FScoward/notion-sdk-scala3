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
case class PastWeek(past_week: Unit) extends DateFilterCondition
case class PastMonth(past_month: Unit) extends DateFilterCondition
case class PastYear(past_year: Unit) extends DateFilterCondition
case class NextWeek(next_week: Unit) extends DateFilterCondition
case class NextMonth(next_month: Unit) extends DateFilterCondition
case class NextYear(next_year: Unit) extends DateFilterCondition

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
    case onOrAfterCondition @ OnOrAfterCondition(on_or_after) =>
      Json.fromString(
        on_or_after.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
      )
    case pastWeek @ PastWeek(_)   => Json.obj(("past_week", Json.obj()))
    case pastMonth @ PastMonth(_) => Json.obj(("past_month", Json.obj()))
    case pastYear @ PastYear(_)   => Json.obj(("past_year", Json.obj()))
    case nextWeek @ NextWeek(_)   => Json.obj(("next_week", Json.obj()))
    case nextMonth @ NextMonth(_) => Json.obj(("next_month", Json.obj()))
    case nextYear @ NextYear(_)   => Json.obj(("next_year", Json.obj()))
  }
