package com.github.fscoward.notion.pages.property

import io.circe.Decoder

import scala.util.Try

enum PropertyType:
  case rich_text extends PropertyType
  case select extends PropertyType
  case created_time extends PropertyType
end PropertyType

implicit val propertyTypeDecoder: Decoder[PropertyType] =
  Decoder.decodeString.emapTry(propertyType =>
    Try(PropertyType.valueOf(propertyType))
  )
