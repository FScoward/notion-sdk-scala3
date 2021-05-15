package com.github.fscoward.notion

import sttp.client3._
import sttp.client3.circe._
import sttp.model._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class NotionApiClient {
  val headers = Map(
    "Authorization" -> s"Bearer ${System.getenv("NOTION_INTEGRATION_TOKEN")}",
    "Notion-Version" -> "2021-05-13"
  )

  def get[R](uri: String)(implicit m: io.circe.Decoder[R]): R = {
    val request = basicRequest
      .headers(headers)
      .get(uri"${uri}")
      .response(asJson[R].getRight)
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)
    response.body
  }

  def user(userId: String): User = {
    get[User](NotionApiUri.User.user(userId))
  }

  def listAllUsers: ListAllUsers = {
    get[ListAllUsers](NotionApiUri.User.listAllUsers)
  }
}
