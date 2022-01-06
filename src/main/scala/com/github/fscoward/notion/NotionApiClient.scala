package com.github.fscoward.notion

import com.github.fscoward.notion.databases.TitleProperty
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

  def listDatabases: ListDatabase = {
    get[ListDatabase](NotionApiUri.Database.listAlliDatabases)
  }

  def post(uri: String, body: String) = {
    val request = basicRequest
      .headers(headers ++ Map("Content-Type" -> "application/json"))
      .post(uri"${uri}")
      .body(body)
    val backend = HttpURLConnectionBackend()
    val response = request.send(backend)
    response
  }
  def createPage(parentDatabaseId: String, properties: Map[String, String]) = {

    /*{
	"parent": { "database_id": "48f8fee9cd794180bc2fec0398253067" },
	"properties": {
		"Name": {
			"title": [
				{
					"text": {
						"content": "Tuscan Kale"
					}
				}
			]
		},
		"Description": {
			"rich_text": [
				{
					"text": {
						"content": "A dark green leafy vegetable"
					}
				}
			]
		},
		"Food group": {
			"select": {
				"name": "Vegetable"
			}
		},
		"Price": { "number": 2.5 }
	},
	"children": [
		{
			"object": "block",
			"type": "heading_2",
			"heading_2": {
				"text": [{ "type": "text", "text": { "content": "Lacinato kale" } }]
			}
		},
		{
			"object": "block",
			"type": "paragraph",
			"paragraph": {
				"text": [
					{
						"type": "text",
						"text": {
							"content": "Lacinato kale is a variety of kale with a long tradition in Italian cuisine, especially that of Tuscany. It is also known as Tuscan kale, Italian kale, dinosaur kale, kale, flat back kale, palm tree kale, or black Tuscan palm.",
							"link": { "url": "https://en.wikipedia.org/wiki/Lacinato_kale" }
						}
					}
				]
			}
		}
	]
}*/
    case class CreatePageParent(database_id: String)
    case class KeyName(underlying: String)
    case class PropertyId(underlying: String)
    case class Text(content: String) {
      val key = "text"
    }

    case class TextProperty(propertyId: PropertyId, property: Text)
    implicit val tpencoder: Encoder[TextProperty] = new Encoder[TextProperty] {
      override def apply(a: TextProperty): Json =
        Json.obj(
          (
            a.propertyId.underlying,
            Json.arr(Json.obj((a.property.key, a.property.asJson)))
          )
        )
    }

    case class Y(
        parent: CreatePageParent,
        properties: Map[KeyName, TextProperty]
    )

    implicit val keyNameEncoder: KeyEncoder[KeyName] = new KeyEncoder[KeyName] {
      override def apply(foo: KeyName): String = foo.underlying
    }
    implicit val propertyTypeEncoder: KeyEncoder[PropertyId] =
      new KeyEncoder[PropertyId] {
        override def apply(key: PropertyId): String = key.underlying
      }

    val b = Y(
      CreatePageParent(s"$parentDatabaseId"),
      Map(
        KeyName("Name") -> TextProperty(
          PropertyId("title"),
          Text("this is content")
        )
      )
    )
    println(b.asJson)

    val response = post(NotionApiUri.Page.pages, b.asJson.toString)
    println(response)
  }
}
