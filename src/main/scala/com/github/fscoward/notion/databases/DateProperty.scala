package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto.*

private val `type` = "date"
case class DateProperty(
    id: String,
    `type`: String = `type`,
    date: Unit = ()
) extends Property

val datePropertyDecoder: Decoder[DateProperty] =
  Decoder[DateProperty].ensure(_.`type` == `type`, s"type is not ${`type`}")
