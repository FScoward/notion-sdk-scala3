package com.github.fscoward.notion

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.checkbox.CheckboxProperty
import com.github.fscoward.notion.pages.date.{DateProperty, DatePropertyValue}
import com.github.fscoward.notion.pages.multi_select.MultiSelectProperty
import com.github.fscoward.notion.pages.property.{
  PropertyType,
  Text,
  TextObj,
  TitleProperty,
  TitlePropertyValue
}
import com.github.fscoward.notion.pages.select
import com.github.fscoward.notion.pages.select.{
  SelectProperty,
  SelectPropertyValue
}
import com.github.fscoward.notion.pages.text.{TextProperty, TextPropertyValue}
import com.github.fscoward.notion.pages.url.URLProperty
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class PageSpec extends munit.FunSuite {
  test("decode Json") {
    val resultJson =
      """
        |{
        |    "object": "page",
        |    "id": "a1712d54-53e4-4893-a69d-4d581cd2c845",
        |    "created_time": "2021-04-27T20:38:19.437Z",
        |    "last_edited_time": "2021-04-27T20:38:19.437Z",
        |    "parent": {
        |        "type": "database_id",
        |        "database_id": "8e2c2b76-9e1d-47d2-87b9-ed3035d607ae"
        |    },
        |    "archived": false,
        |    "properties": {
        |        "Type": {
        |            "id": "/7eo",
        |            "type": "select",
        |            "select": {
        |                "id": "f96d0d0a-5564-4a20-ab15-5f040d49759e",
        |                "name": "Article",
        |                "color": "default"
        |            }
        |        },
        |        "Publisher": {
        |            "id": ">$Pb",
        |            "type": "select",
        |            "select": {
        |                "id": "c5ee409a-f307-4176-99ee-6e424fa89afa",
        |                "name": "NYT",
        |                "color": "default"
        |            }
        |        },
        |        "Summary": {
        |            "id": "?\\25",
        |            "type": "text",
        |            "text": [
        |                {
        |                    "type": "text",
        |                    "text": {
        |                        "content": "Some think chief ethics officers could help technology companies navigate political and social questions.",
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
        |                    "plain_text": "Some think chief ethics officers could help technology companies navigate political and social questions.",
        |                    "href": null
        |                }
        |            ]
        |        },
        |        "Publishing/Release Date": {
        |            "id": "?ex+",
        |            "type": "date",
        |            "date": {
        |                "start": "2018-10-21",
        |                "end": null
        |            }
        |        },
        |        "Link": {
        |            "id": "VVMi",
        |            "type": "url",
        |            "url": "https://www.nytimes.com/2018/10/21/opinion/who-will-teach-silicon-valley-to-be-ethical.html"
        |        },
        |        "Read": {
        |            "id": "_MWJ",
        |            "type": "checkbox",
        |            "checkbox": false
        |        },
        |        "Status": {
        |            "id": "`zz5",
        |            "type": "select",
        |            "select": {
        |                "id": "8c4a056e-6709-4dd1-ba58-d34d9480855a",
        |                "name": "Ready to Start",
        |                "color": "yellow"
        |            }
        |        },
        |        "Author": {
        |            "id": "qNw_",
        |            "type": "multi_select",
        |            "multi_select": [
        |                {
        |                    "id": "833e2c78-35ed-4601-badc-50c323341d76",
        |                    "name": "Kara Swisher",
        |                    "color": "default"
        |                }
        |            ]
        |        },
        |        "Name": {
        |            "id": "title",
        |            "type": "title",
        |            "title": [
        |                {
        |                    "type": "text",
        |                    "text": {
        |                        "content": "Who Will Teach Silicon Valley to Be Ethical? ",
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
        |                    "plain_text": "Who Will Teach Silicon Valley to Be Ethical? ",
        |                    "href": null
        |                }
        |            ]
        |        }
        |    }
        |}
        |""".stripMargin

    val expected = Page(
      `object` = "page",
      id = "a1712d54-53e4-4893-a69d-4d581cd2c845",
      created_time = "2021-04-27T20:38:19.437Z",
      last_edited_time = "2021-04-27T20:38:19.437Z",
      archived = Some(false),
      parent = Parent(database_id = "8e2c2b76-9e1d-47d2-87b9-ed3035d607ae"),
      properties = Map(
        "Name" -> TitleProperty(
          id = "title",
          title = Seq(
            TitlePropertyValue(
              `type` = "text",
              text = Text(
                content = "Who Will Teach Silicon Valley to Be Ethical? ",
                link = None
              ),
              NotionAnnotation(
                bold = false,
                italic = false,
                strikethrough = false,
                code = false,
                color = "default"
              ),
              plain_text = "Who Will Teach Silicon Valley to Be Ethical? ",
              None,
              None
            )
          )
        ),
        "Type" -> SelectProperty(
          id = "/7eo",
          select = SelectPropertyValue(
            id = "f96d0d0a-5564-4a20-ab15-5f040d49759e",
            name = "Article",
            color = "default"
          )
        ),
        "Publisher" -> SelectProperty(
          id = ">$Pb",
          select = SelectPropertyValue(
            id = "c5ee409a-f307-4176-99ee-6e424fa89afa",
            name = "NYT",
            color = "default"
          )
        ),
        "Summary" -> TextProperty(
          id = "?\\25",
          text = Seq(
            TextPropertyValue(
              `type` = "text",
              text = TextObj(
                content =
                  "Some think chief ethics officers could help technology companies navigate political and social questions.",
                link = None
              ),
              annotations = NotionAnnotation(
                bold = false,
                italic = false,
                strikethrough = false,
                code = false,
                color = "default"
              ),
              plain_text =
                "Some think chief ethics officers could help technology companies navigate political and social questions.",
              href = None
            )
          )
        ),
        "Publishing/Release Date" -> DateProperty(
          id = "?ex+",
          `type` = PropertyType.date,
          DatePropertyValue(
            start = "2018-10-21",
            end = None,
            time_zone = None
          )
        ),
        "Link" -> URLProperty(
          id = "VVMi",
          url =
            "https://www.nytimes.com/2018/10/21/opinion/who-will-teach-silicon-valley-to-be-ethical.html"
        ),
        "Read" -> CheckboxProperty(
          id = "_MWJ",
          checkbox = false
        ),
        "Status" -> select.SelectProperty(
          id = "`zz5",
          select = SelectPropertyValue(
            id = "8c4a056e-6709-4dd1-ba58-d34d9480855a",
            name = "Ready to Start",
            color = "yellow"
          )
        ),
        "Author" -> MultiSelectProperty(
          id = "qNw_",
          multi_select = Seq(
            SelectPropertyValue(
              id = "833e2c78-35ed-4601-badc-50c323341d76",
              name = "Kara Swisher",
              color = "default"
            )
          )
        )
      )
    )
    val actual = decode[Page](resultJson)
    assert(actual.isRight)
    // TODO
    assertEquals(actual, Right(expected))
  }

  /*
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
        "Property 1" -> PropertyValue(
          `type` = "files",
          id = Some(":=<q"),
          files = Some(Seq(File("rrrrrrrrr.png")))
        )
      )
    )
    val actual = decode[Page](resultJson)
    assert(actual.isRight)
    // TODO
    assertEquals(actual, Right(expected))
  }
   */
}
