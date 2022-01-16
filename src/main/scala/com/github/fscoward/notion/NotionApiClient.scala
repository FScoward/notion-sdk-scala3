package com.github.fscoward.notion

import com.github.fscoward.notion.databases.read.TitleProperty
import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.TextObj
import com.github.fscoward.notion.pages.text.TextPropertyValue
import sttp.client3.*
import sttp.client3.circe.*
import sttp.model.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import com.github.fscoward.notion.users.*

class NotionApiClient {
  val headers = Map(
    "Authorization" -> s"Bearer ${System.getenv("NOTION_INTEGRATION_TOKEN")}",
    "Notion-Version" -> "2021-08-16"
  )

  def get[R](uri: String)(implicit m: io.circe.Decoder[R]): R = {
    val request = basicRequest
      .headers(headers)
      .get(uri"$uri")
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

  def listDatabases: ListDatabase = {
    get[ListDatabase](NotionApiUri.Database.listAlliDatabases)
  }

  def post(
      uri: String,
      body: String
  ): Identity[Response[Either[String, String]]] = {
    val request = basicRequest
      .headers(headers ++ Map("Content-Type" -> "application/json"))
      .post(uri"$uri")
      .body(body)
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)
    response
  }

  def createDatabase(
      database: com.github.fscoward.notion.databases.create.Database
  ): Identity[Response[Either[String, String]]] = {
    import com.github.fscoward.notion.databases.create.propertiesEncoder
    val response = post(NotionApiUri.Database.create, database.asJson.toString)
    response
  }

  def createPage(
      parentDatabaseId: String,
      properties: Map[String, String]
  ): Unit = {
//    import com.github.fscoward.notion.pages.create
//    val request = create.PageProperty(
//      create.PageParent("7b1e0686274f4ae6b6373c2236b57080"),
//      create.Properties(
//        Map(
//          "Name" -> create.TitleProperty(
//            create.TextPropertyValue("this is content")
//          ),
//          "Description" -> create.RitchTextProperty(
//            create.TextPropertyValue("A dark green leafy vegetable")
//          ),
//          "Food group" -> create.SelectProperty(
//            create.SelectPropertyValue("Vegetable")
//          ),
//          "Price" -> create.NumberProperty(2.5)
//        )
//      )
//    )
//    import com.github.fscoward.notion.pages.create.encoder
//
//    val response = post(NotionApiUri.Page.pages, request.asJson.toString)
//    println(response)
  }
}
