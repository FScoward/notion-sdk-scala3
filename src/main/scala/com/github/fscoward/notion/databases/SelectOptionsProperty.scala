package com.github.fscoward.notion.databases

import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{Decoder, DecodingFailure, HCursor, ACursor}

private val typeValue: String = "select"
case class SelectOptionsProperty(
    id: String,
    `type`: String = typeValue,
    select: SelectOptions
) extends Property

case class SelectOptions(
    options: Seq[SelectPropertyValue]
)

case class SelectPropertyValue(
    id: String,
    name: String,
    color: String
)

val selectOptionsPropertyDecoder: Decoder[SelectOptionsProperty] =
  Decoder[SelectOptionsProperty]
    .ensure(
      pred => pred.`type` == typeValue,
      s"type is not ${typeValue}"
    )
