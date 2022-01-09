package com.github.fscoward.notion.databases.create

import io.circe.{Decoder, Encoder, HCursor, Json}

case class Parent(
    page_id: String
) {
  val `type`: String = "page_id"
}

implicit val parentEncoder: Encoder[Parent] = new Encoder[Parent] {
  override def apply(a: Parent): Json = Json.obj(
    ("type", Json.fromString(a.`type`)),
    ("page_id", Json.fromString(a.page_id))
  )
}
