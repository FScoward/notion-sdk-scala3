package com.github.fscoward.notion.databases.read

import io.circe.Decoder
import io.circe.generic.auto.*

private val date = "date"
case class DateProperty(
    id: String,
    `type`: String = date,
    date: Unit = ()
) extends Property

val datePropertyDecoder: Decoder[DateProperty] =
  Decoder[DateProperty].ensure(
    _.`type` == date,
    s"type is not ${date}"
  )
