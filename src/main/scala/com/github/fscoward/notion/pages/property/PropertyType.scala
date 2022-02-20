package com.github.fscoward.notion.pages.property

import io.circe.Decoder

import scala.util.Try

enum PropertyType:
  case rich_text extends PropertyType
  case number extends PropertyType
  case select extends PropertyType
  case multi_select extends PropertyType
  case date extends PropertyType
  case formula extends PropertyType
  case relation extends PropertyType
  case rollup extends PropertyType
  case title extends PropertyType
  case people extends PropertyType
  case files extends PropertyType
  case checkbox extends PropertyType
  case url extends PropertyType
  case email extends PropertyType
  case phone_number extends PropertyType
  case created_time extends PropertyType
  case created_by extends PropertyType
  case last_edited_time extends PropertyType
  case last_edited_by extends PropertyType
end PropertyType

implicit val propertyTypeDecoder: Decoder[PropertyType] =
  Decoder.decodeString.emapTry(propertyType =>
    Try(PropertyType.valueOf(propertyType))
  )
