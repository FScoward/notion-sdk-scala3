package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto._

private val text = "text"
case class TextProperty(
    id: String,
    `type`: String = text,
    text: Unit = ()
) extends Property

val textPropertyDecoder: Decoder[TextProperty] =
  Decoder[TextProperty].ensure(
    pred => pred.`type` == text,
    s"type is not ${text}"
  )
