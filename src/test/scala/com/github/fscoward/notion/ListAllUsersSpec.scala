package com.github.fscoward.notion

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class ListAllUsersSpec extends munit.FunSuite {
  test("docode json") {
    val resultJson = """
    |  {
    |    "object": "list",
    |    "results": [
    |        {
    |            "object": "user",
    |            "id": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaa",
    |            "name": "XXXXXXXXXX",
    |            "avatar_url": "https://XXXXX.jpg",
    |            "type": "person",
    |            "person": {
    |                "email": "test@example.com"
    |            }
    |        },
    |        {
    |            "object": "user",
    |            "id": "xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxxxx",
    |            "name": "testtest",
    |            "avatar_url": null,
    |            "type": "bot",
    |            "bot": {}
    |        }
    |    ],
    |    "next_cursor": null,
    |    "has_more": false
    |}
    """.stripMargin

    val expected = ListAllUsers(
      `object` = "list",
      results = Seq(
        Content(
          `object` = "user",
          id = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaa",
          name = "XXXXXXXXXX",
          avatar_url = Some("https://XXXXX.jpg"),
          `type` = "person",
          person = Some(Person("test@example.com"))
        ),
        Content(
          `object` = "user",
          id = "xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxxxx",
          name = "testtest",
          avatar_url = None,
          `type` = "bot",
          bot = Some(Bot())
        )
      ),
      next_cursor = None,
      has_more = false
    )

    val actual = decode[ListAllUsers](resultJson)
    assert(actual.isRight)
    assertEquals(actual, Right(expected))
  }
}
