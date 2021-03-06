package com.github.fscoward.notion

import com.github.fscoward.notion.blocks.{BlockObject, TodoBlockObject}
import com.github.fscoward.notion.databases.read.TitleProperty
import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.TextObj
import com.github.fscoward.notion.pages.text.TextPropertyValue
import sttp.client3.*
import sttp.client3.circe.*
import sttp.client3.okhttp.OkHttpSyncBackend
import sttp.model.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import com.github.fscoward.notion.users.*

import java.net.HttpURLConnection

class NotionApiClient {
  val headers = Map(
    "Authorization" -> s"Bearer ${System.getenv("NOTION_API_KEY")}",
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

  def retrieveABlock(blockId: String): BlockObject = {
    import com.github.fscoward.notion.blocks.blockDecoder
    get[TodoBlockObject](NotionApiUri.Block.retrieveABlock(blockId))
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

  def update(
      uri: String,
      body: String
  ): Identity[Response[Either[String, String]]] = {
    val request = basicRequest
      .headers(headers ++ Map("Content-Type" -> "application/json"))
      .patch(uri"$uri")
      .body(body)

    val backend = OkHttpSyncBackend()
    val response = request.send(backend)
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

  def updatePage(
      pageId: String,
      updatePageProperty: com.github.fscoward.notion.pages.create.Properties
  ): Identity[Response[Either[String, String]]] = {
    import com.github.fscoward.notion.pages.create.pagePropertiesEncoder
    update(
      uri = s"${NotionApiUri.Page.page(pageId)}",
      body = Json.obj(("properties", updatePageProperty.asJson)).toString
    )
  }
}
