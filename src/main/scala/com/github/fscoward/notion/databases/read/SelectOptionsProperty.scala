package com.github.fscoward.notion.databases.read

import com.github.fscoward.notion.databases.Property
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{ACursor, Decoder, DecodingFailure, HCursor}

private val select: String = "select"
case class SelectOptionsProperty(
    id: String,
    `type`: String = select,
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
      pred => pred.`type` == select,
      s"type is not ${select}"
    )
