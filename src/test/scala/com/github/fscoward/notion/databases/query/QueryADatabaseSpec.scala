package com.github.fscoward.notion.databases.query

class QueryADatabaseSpec extends munit.FunSuite {
  test("decode json") {
    val json = """
{
  "object": "list",
  "results": [
    {
      "object": "page",
      "id": "8d620812-0232-42af-903f-f6368615e58c",
      "created_time": "2022-02-17T15:52:00.000Z",
      "last_edited_time": "2022-02-17T15:53:00.000Z",
      "created_by": {
        "object": "user",
        "id": "b57cb0da-2e34-48bf-a48c-2f55e3c3c567"
      },
      "last_edited_by": {
        "object": "user",
        "id": "b57cb0da-2e34-48bf-a48c-2f55e3c3c567"
      },
      "cover": null,
      "icon": null,
      "parent": {
        "type": "database_id",
        "database_id": "55f89aa7-739e-44dc-b55b-b4d38acf4dbf"
      },
      "archived": false,
      "properties": {
        "Status": {
          "id": "Ewpy",
          "type": "select",
          "select": {
            "id": "biin",
            "name": "Backlog",
            "color": "default"
          }
        },
        "Sprint": {
          "id": "G%3EAA",
          "type": "relation",
          "relation": [
            {
              "id": "1e6fa199-6306-4a3d-ae0c-618892849eb3"
            }
          ]
        },
        "タグ": {
          "id": "%5ELA%5E",
          "type": "multi_select",
          "multi_select": []
        },
        "作成日時": {
          "id": "_%5EVR",
          "type": "created_time",
          "created_time": "2022-02-17T15:52:00.000Z"
        },
        "名前": {
          "id": "title",
          "type": "title",
          "title": [
            {
              "type": "text",
              "text": {
                "content": "backlog item 1",
                "link": null
              },
              "annotations": {
                "bold": false,
                "italic": false,
                "strikethrough": false,
                "underline": false,
                "code": false,
                "color": "default"
              },
              "plain_text": "backlog item 1",
              "href": null
            }
          ]
        }
      },
      "url": "https://www.notion.so/backlog-item-1-8d620812023242af903ff6368615e58c"
    }
  ],
  "next_cursor": null,
  "has_more": false
}
        """
  }
}
