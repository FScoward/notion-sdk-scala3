package com.github.fscoward.notion.databases

import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{Decoder, DecodingFailure, HCursor, ACursor}

private val `type`: String = "select"
case class SelectOptionsProperty(
    id: String,
    `type`: String = `type`,
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
  Decoder[SelectOptionsProperty].ensure(
    pred => pred.`type` == `type`,
    s"type is not ${`type`}"
  )
