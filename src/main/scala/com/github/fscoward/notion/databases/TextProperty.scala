package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto._

private val `type` = "text"
case class TextProperty(
    id: String,
    `type`: String = `type`,
    text: Unit = ()
) extends Property

val textPropertyDecoder: Decoder[TextProperty] =
  Decoder[TextProperty].ensure(_.`type` == `type`, s"type is not ${`type`}")
