package com.github.fscoward.notion.pages.property_item

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import cats.syntax.functor.*
import com.github.fscoward.notion.pages.property.{PropertyType, propertyDecoder}

trait PagePropertyItem {
  val `object`: String
  val `type`: PropertyType
}

import com.github.fscoward.notion.pages.property.propertyTypeDecoder
implicit val pagePropertyItemDecoder: Decoder[PagePropertyItem] =
  List[Decoder[PagePropertyItem]](
    Decoder[RichText].widen
  ).reduceLeft(_ or _)
