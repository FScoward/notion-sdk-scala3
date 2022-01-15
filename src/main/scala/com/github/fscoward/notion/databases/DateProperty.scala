package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto.*

private val typeValue = "date"
case class DateProperty(
    id: String,
    `type`: String = typeValue,
    date: Unit = ()
) extends Property

val datePropertyDecoder: Decoder[DateProperty] =
  Decoder[DateProperty].ensure(
    _.`type` == typeValue,
    s"type is not ${typeValue}"
  )
