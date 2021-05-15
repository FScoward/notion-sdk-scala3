package com.github.fscoward.notion

import sttp.client3._
import sttp.client3.circe._
import sttp.model._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class HttpClient extends Client {
  val headers = Map(
    "Authorization" -> s"Bearer ${System.getenv("NOTION_INTEGRATION_TOKEN")}",
    "Notion-Version" -> "2021-05-13"
  )

  def listAllUsers: ListAllUsers = {
    val request = basicRequest
      .headers(headers)
      .get(uri"${NotionApi.listAllUsers}")
      .response(asJson[ListAllUsers].getRight)
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)
    response.body
  }
}
