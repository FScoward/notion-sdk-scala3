package com.github.fscoward.notion.pages.create

import com.github.fscoward.notion.pages.create.block.{Children, childrenEncoder}
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class PageProperty(
    parent: PageParent,
    properties: Properties,
    children: Children
)

implicit val encoder: Encoder[PageProperty] = new Encoder[PageProperty] {
  override def apply(pageProperty: PageProperty): Json = {
    Json.obj(
      ("parent", pageProperty.parent.asJson),
      (
        "properties",
        pageProperty.properties.asJson
      ),
      ("children", pageProperty.children.asJson)
    )
  }
}
