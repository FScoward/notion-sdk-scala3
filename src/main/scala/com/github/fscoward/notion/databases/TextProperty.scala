package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto._

private val typeValue = "text"
case class TextProperty(
    id: String,
    `type`: String = typeValue,
    text: Unit = ()
) extends Property

val textPropertyDecoder: Decoder[TextProperty] =
  Decoder[TextProperty].ensure(
    _.`type` == typeValue,
    s"type is not ${typeValue}"
  )
