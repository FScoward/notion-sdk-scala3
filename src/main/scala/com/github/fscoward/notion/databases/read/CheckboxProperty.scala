package com.github.fscoward.notion.databases.read

import io.circe.Decoder
import io.circe.generic.auto.*

private val checkbox = "checkbox"

case class CheckboxProperty(
    id: String,
    `type`: String,
    checkbox: Unit = ()
) extends Property

val checkboxPropertyDecoder: Decoder[CheckboxProperty] =
  Decoder[CheckboxProperty].ensure(
    _.`type` == checkbox,
    s"type is not ${checkbox}"
  )
