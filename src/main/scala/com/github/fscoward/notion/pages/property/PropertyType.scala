package com.github.fscoward.notion.pages.property

import io.circe.Decoder

enum PropertyType:
  case created_time extends PropertyType
end PropertyType

implicit val propertyTypeDecoder: Decoder[PropertyType] =
  Decoder.decodeString.map(PropertyType.valueOf)
