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

    val expected = Page(
      `object` = "page",
      id = "b55c9c91-384d-452b-81db-d1ef79372b75",
      created_time = "2020-03-17T19:10:04.968Z",
      last_edited_time = "2020-03-17T21:49:37.913Z",
      properties = Map(
        "Name" -> TextObject(
          "Persea americana",
          None,
          null,
          "text",
          null,
          null
        ),
        "Description" -> null, // TODO
        "In stock" -> Bool(false),
        "Food group" -> Select("🍎Fruit", Color.red),
        "Price" -> Number(2),
        "Cost of next trip" -> Number(2),
        "Last ordered" -> null // TODO
      )
    )
    val actual = decode[Page](resultJson)
    assert(actual.isRight)
    // TODO
    assertEquals(actual, Right(expected))
  }

  test("decode page json that parent is database") {
    val resultJson = """
    |{
    |    "object": "page",
    |    "id": "rrrrrrrr-rrrr-rrrr-rrrr-rrrrrrrrrrrr",
    |    "created_time": "2021-05-23T02:58:36.185Z",
    |    "last_edited_time": "2021-05-26T15:18:00.000Z",
    |    "parent": {
    |        "type": "database_id",
    |        "database_id": "rrrrrrrrrrrrr-rrrr-rrrr-rrrrrrrrrrrr"
    |    },
    |    "archived": false,
    |    "properties": {
    |        "Property 1": {
    |            "id": ":=<q",
    |            "type": "files",
    |            "files": [
    |                {
    |                    "name": "rrrrrrrrr.png"
    |                }
    |            ]
    |        },
    |        "Property 3": {
    |            "id": ":^]u",
    |            "type": "people",
    |            "people": [
    |                {
    |                    "object": "user",
    |                    "id": "rrrrrrrr-rrrr-rrrr-rrrr-rrrrrrrrrrrr",
    |                    "name": "Fumiyasu Sumiya",
    |                    "avatar_url": "https://rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr.jpg?sz=50",
    |                    "type": "person",
    |                    "person": {
    |                        "email": "rrrrrrr@rrrrr.rrr"
    |                    }
    |                }
    |            ]
    |        },
    |        "xxxxxxxx": {
    |            "id": "DpDU",
    |            "type": "checkbox",
    |            "checkbox": true
    |        },
    |        "Property 5": {
    |            "id": "KpBc",
    |            "type": "phone_number",
    |            "phone_number": "080123456789"
    |        },
    |        "Property": {
    |            "id": "Z_eY",
    |            "type": "files",
    |            "files": [
    |                {
    |                    "name": "https://rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr.jpg"
    |                }
    |            ]
    |        },
    |        "Property 4": {
    |            "id": "aMA{",
    |            "type": "email",
    |            "email": "example@example.com"
    |        },
    |        "Tags": {
    |            "id": "eBzE",
    |            "type": "multi_select",
    |            "multi_select": [
    |                {
    |                    "id": "rrrrrrrr-rrrr-rrrr-rrrr-rrrrrrrrrrrr",
    |                    "name": "rrrrr",
    |                    "color": "red"
    |                }
    |            ]
    |        },
    |        "Property 2": {
    |            "id": "jbVw",
    |            "type": "url",
    |            "url": "https://rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"
    |        },
    |        "Name---": {
    |            "id": "title",
    |            "type": "title",
    |            "title": [
    |                {
    |                    "type": "text",
    |                    "text": {
    |                        "content": "this is test",
    |                        "link": null
    |                    },
    |                    "annotations": {
    |                        "bold": false,
    |                        "italic": false,
    |                        "strikethrough": false,
    |                        "underline": false,
    |                        "code": false,
    |                        "color": "default"
    |                    },
    |                    "plain_text": "this is test",
    |                    "href": null
    |                }
    |            ]
    |        }
    |    }
    |}
    """.stripMargin

    val expected = Page(
      `object` = "page",
      id = "rrrrrrrr-rrrr-rrrr-rrrr-rrrrrrrrrrrr",
      created_time = "2021-05-23T02:58:36.185Z",
      last_edited_time = "2021-05-26T15:18:00.000Z",
      archived = Some(false),
      properties = Map(
        "Property 1" -> FileProperty(
          id = ":=<q",
          `type` = "files",
          files = Seq(File("rrrrrrrrr.png"))
        )
      )
    )
    val actual = decode[Page](resultJson)
    assert(actual.isRight)
    // TODO
    assertEquals(actual, Right(expected))
  }
}
