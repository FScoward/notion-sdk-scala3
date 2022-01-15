package com.github.fscoward.notion.databases.read

import com.github.fscoward.notion.databases.Property
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{Decoder, HCursor}

private val multiSelect = "multi_select"
case class MultiSelectOptionsProperty(
    id: String,
    `type`: String = multiSelect,
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
    _.`type` == multiSelect,
    s"type is not ${multiSelect}"
  )
