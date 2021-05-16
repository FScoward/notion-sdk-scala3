package com.github.fscoward.notion

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class PageSpec extends munit.FunSuite {
  test("decode Json") {
    val resultJson =
      """
        |{
        |  "object": "page",
        |  "id": "b55c9c91-384d-452b-81db-d1ef79372b75",
        |  "created_time": "2020-03-17T19:10:04.968Z",
        |  "last_edited_time": "2020-03-17T21:49:37.913Z",
        |  "properties": {
        |    "Name": [
        |      {
        |        "id": "some-property-id",
        |        "text": "Avocado", 
        |        "annotations": {
        |          "formatting": [],
        |          "color": "default",
        |          "link": null
        |        }, 
        |        "inline_object": null
        |      }
        |    ],
        |    "Description": [
        |      {
        |        "text": "Persea americana", 
        |        "annotations": {
        |          "formatting": [],
        |          "color": "default",
        |          "link": null
        |        }, 
        |        "inline_object": null
        |      }
        |    ],
        |    "In stock": false,
        |    "Food group": {
        |      "name": "🍎Fruit",
        |      "color": "red"
        |    },
        |    "Price": 2,
        |    "Cost of next trip": 2,
        |    "Last ordered": "2020-03-10",
        |    "Meals": [
        |      "a91e35b0-5c4e-4018-83e8-584988caee1c",
        |      "f5051efa-a7d9-4075-97f3-8ce9af14b1a7"
        |    ],
        |    "Number of meals": 2,
        |    "Store availability": [
        |      {
        |        "name": "Rainbow Grocery",
        |        "color": "purple"
        |      },
        |      {
        |        "name": "Gus's Community Market",
        |        "color": "green"
        |      }
        |    ],
        |    "+1": [
        |      {
        |        "object": "user",
        |        "id": "01da9b00-e400-4959-91ce-af55307647e5",
        |        "type": "person",
        |        "name": "Avocado Lovelace",
        |        "person": {
        |          "email": "avo@example.org"
        |        },
        |        "avatar_url": "https://secure.notion-static.com/e6a352a8-8381-44d0-a1dc-9ed80e62b53d.jpg"
        |      }
        |    ],
        |    "Photos": [
        |      {
        |        "url": "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e6a352a8-8381-44d0-a1dc-9ed80e62b53d/avocado.jpg",
        |        "name": "avocado",
        |        "mime_type": "image/jpg"
        |      }
        |    ]
        |  }
        |}
        |""".stripMargin

    val expected = null
    val actual = decode[Page](resultJson)
    actual match {
      case Left(e)  => println(e)
      case Right(r) => println(r.properties.spaces2)
    }
    assert(actual.isRight)
    assertEquals(actual, Right(expected))
  }
}
