package com.github.fscoward.notion.pages.property_item

import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import cats.syntax.functor.*

import com.github.fscoward.notion.pages.property.propertyDecoder
import com.github.fscoward.notion.pages.property_item.pagePropertyItemDecoder

case class RetrieveAPagePropertyItemResponse(
    `object`: String = "list",
    results: Seq[PagePropertyItem],
    next_cursor: Option[String],
    has_more: Boolean
)

implicit val pagePropertyItemResponseDecoder
    : Decoder[RetrieveAPagePropertyItemResponse] =
  (c: HCursor) => {
    for {
      obj <- c.downField("object").as[String]
      results <- c.downField("results").as[Seq[PagePropertyItem]]
      next_cursor <- c.downField("next_cursor").as[Option[String]]
      has_more <- c.downField("has_more").as[Boolean]
    } yield RetrieveAPagePropertyItemResponse(
      obj,
      results,
      next_cursor,
      has_more
    )
  }
