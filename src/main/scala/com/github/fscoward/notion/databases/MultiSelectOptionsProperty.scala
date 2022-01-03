package com.github.fscoward.notion.databases

import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{Decoder, HCursor}

private val `type` = "multi_select"
case class MultiSelectOptionsProperty(
    id: String,
    `type`: String = `type`,
    multi_select: MultiSelectOptions
) extends Property

case class MultiSelectOptions(
    options: Seq[MultiSelectPropertyValue]
)

case class MultiSelectPropertyValue(
    id: String,
    name: String,
    color: String
)

val multiSelectOptionsPropertyDecoder: Decoder[MultiSelectOptionsProperty] =
  Decoder[MultiSelectOptionsProperty].ensure(
    _.`type` == `type`,
    s"type is not ${`type`}"
  )
