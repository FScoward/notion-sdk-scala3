package com.github.fscoward.notion.databases

import com.github.fscoward.notion.databases.read.*
import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.{Text, TitlePropertyValue}
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class DatabaseObjectSuite extends munit.FunSuite {
  test("decode json") {
    val resultJson =
      """
{
   "object":"database",
   "id":"8e2c2b76-9e1d-47d2-87b9-ed3035d607ae",
   "created_time":"2021-04-27T20:38:19.437Z",
   "last_edited_time":"2021-04-27T21:15:00.000Z",
   "title":[
      {
         "type":"text",
         "text":{
            "content":"Media",
            "link":null
         },
         "annotations":{
            "bold":false,
            "italic":false,
            "strikethrough":false,
            "underline":false,
            "code":false,
            "color":"default"
         },
         "plain_text":"Media",
         "href":null
      }
   ],
   "properties":{
      "Score /5":{
         "id":")Y7\"",
         "type":"select",
         "select":{
            "options":[
               {
                  "id":"5c944de7-3f4b-4567-b3a1-fa2c71c540b6",
                  "name":"⭐️⭐️⭐️⭐️⭐️",
                  "color":"default"
               },
               {
                  "id":"b7307e35-c80a-4cb5-bb6b-6054523b394a",
                  "name":"⭐️⭐️⭐️⭐️",
                  "color":"default"
               },
               {
                  "id":"9b1e1349-8e24-40ba-bbca-84a61296bc81",
                  "name":"⭐️⭐️⭐️",
                  "color":"default"
               },
               {
                  "id":"66d3d050-086c-4a91-8c56-d55dc67e7789",
                  "name":"⭐️⭐️",
                  "color":"default"
               },
               {
                  "id":"d3782c76-0396-467f-928e-46bf0c9d5fba",
                  "name":"⭐️",
                  "color":"default"
               }
            ]
         }
      },
      "Type":{
         "id":"/7eo",
         "type":"select",
         "select":{
            "options":[
               {
                  "id":"f96d0d0a-5564-4a20-ab15-5f040d49759e",
                  "name":"Article",
                  "color":"default"
               },
               {
                  "id":"4ac85597-5db1-4e0a-9c02-445575c38f76",
                  "name":"TV Series",
                  "color":"default"
               },
               {
                  "id":"2991748a-5745-4c3b-9c9b-2d6846a6fa1f",
                  "name":"Film",
                  "color":"default"
               },
               {
                  "id":"82f3bace-be25-410d-87fe-561c9c22492f",
                  "name":"Podcast",
                  "color":"default"
               },
               {
                  "id":"861f1076-1cc4-429a-a781-54947d727a4a",
                  "name":"Academic Journal",
                  "color":"default"
               },
               {
                  "id":"9cc30548-59d6-4cd3-94bc-d234081525c4",
                  "name":"Essay Resource",
                  "color":"default"
               }
            ]
         }
      },
      "Publisher":{
         "id":">$Pb",
         "type":"select",
         "select":{
            "options":[
               {
                  "id":"c5ee409a-f307-4176-99ee-6e424fa89afa",
                  "name":"NYT",
                  "color":"default"
               },
               {
                  "id":"1b9b0c0c-17b0-4292-ad12-1364a51849de",
                  "name":"Netflix",
                  "color":"blue"
               },
               {
                  "id":"f3533637-278f-4501-b394-d9753bf3c101",
                  "name":"Indie",
                  "color":"brown"
               },
               {
                  "id":"e70d713c-4be4-4b40-a44d-fb413c8b9d7e",
                  "name":"Bon Appetit",
                  "color":"yellow"
               },
               {
                  "id":"9c2bd667-0a10-4be4-a044-35a537a14ab9",
                  "name":"Franklin Institute",
                  "color":"pink"
               },
               {
                  "id":"6849b5f0-e641-4ec5-83cb-1ffe23011060",
                  "name":"Springer",
                  "color":"orange"
               },
               {
                  "id":"6a5bff63-a72d-4464-a5d0-1a601af2adf6",
                  "name":"Emerald Group",
                  "color":"gray"
               },
               {
                  "id":"01f82d08-aa1f-4884-a4e0-3bc32f909ec4",
                  "name":"The Atlantic",
                  "color":"red"
               }
            ]
         }
      },
      "Summary":{
         "id":"?\\25",
         "type":"text",
         "text":{
            
         }
      },
      "Publishing/Release Date":{
         "id":"?ex+",
         "type":"date",
         "date":{
            
         }
      },
      "Link":{
         "id":"VVMi",
         "type":"url",
         "url":{
            
         }
      },
      "Read":{
         "id":"_MWJ",
         "type":"checkbox",
         "checkbox":{
            
         }
      },
      "Status":{
         "id":"`zz5",
         "type":"select",
         "select":{
            "options":[
               {
                  "id":"8c4a056e-6709-4dd1-ba58-d34d9480855a",
                  "name":"Ready to Start",
                  "color":"yellow"
               },
               {
                  "id":"5925ba22-0126-4b58-90c7-b8bbb2c3c895",
                  "name":"Reading",
                  "color":"red"
               },
               {
                  "id":"59aa9043-07b4-4bf4-8734-3164b13af44a",
                  "name":"Finished",
                  "color":"blue"
               },
               {
                  "id":"f961978d-02eb-4998-933a-33c2ec378564",
                  "name":"Listening",
                  "color":"red"
               },
               {
                  "id":"1d450853-b27a-45e2-979f-448aa1bd35de",
                  "name":"Watching",
                  "color":"red"
               }
            ]
         }
      },
      "Author":{
         "id":"qNw_",
         "type":"multi_select",
         "multi_select":{
            "options":[
               {
                  "id":"15592971-7b30-43d5-9406-2eb69b13fcae",
                  "name":"Spencer Greenberg",
                  "color":"default"
               },
               {
                  "id":"b80a988e-dccf-4f74-b764-6ca0e49ed1b8",
                  "name":"Seth Stephens-Davidowitz",
                  "color":"default"
               },
               {
                  "id":"0e71ee06-199d-46a4-834c-01084c8f76cb",
                  "name":"Andrew Russell",
                  "color":"default"
               },
               {
                  "id":"5807ec38-4879-4455-9f30-5352e90e8b79",
                  "name":"Lee Vinsel",
                  "color":"default"
               },
               {
                  "id":"4cf10a64-f3da-449c-8e04-ce6e338bbdbd",
                  "name":"Megan Greenwell",
                  "color":"default"
               },
               {
                  "id":"833e2c78-35ed-4601-badc-50c323341d76",
                  "name":"Kara Swisher",
                  "color":"default"
               },
               {
                  "id":"82e594e2-b1c5-4271-ac19-1a723a94a533",
                  "name":"Paul Romer",
                  "color":"default"
               },
               {
                  "id":"ae3a2cbe-1fc9-4376-be35-331628b34623",
                  "name":"Karen Swallow Prior",
                  "color":"default"
               },
               {
                  "id":"da068e78-dfe2-4434-9fd7-b7450b3e5830",
                  "name":"Judith Shulevitz",
                  "color":"default"
               }
            ]
         }
      },
      "Name":{
         "id":"title",
         "type":"title",
         "title":{
            
         }
      }
   }
}
      """

    import com.github.fscoward.notion.pages.property.propertyTypeDecoder
    val actual = decode[DatabaseObject](resultJson)
    val expected =
      DatabaseObject(
        id = "8e2c2b76-9e1d-47d2-87b9-ed3035d607ae",
        created_time = "2021-04-27T20:38:19.437Z",
        last_edited_time = "2021-04-27T21:15:00.000Z",
        title = Seq(
          TitlePropertyValue(
            text = Text(
              content = "Media",
              link = None
            ),
            annotations = NotionAnnotation(
              bold = false,
              italic = false,
              strikethrough = false,
              code = false,
              color = "default"
            ),
            plain_text = "Media",
            href = None,
            inline_object = None
          )
        ),
        properties = Map(
          "Score /5" -> SelectOptionsProperty(
            id = ")Y7\"",
            `type` = "select",
            select = SelectOptions(
              Seq(
                SelectPropertyValue(
                  id = "5c944de7-3f4b-4567-b3a1-fa2c71c540b6",
                  name = "⭐️⭐️⭐️⭐️⭐️",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "b7307e35-c80a-4cb5-bb6b-6054523b394a",
                  name = "⭐️⭐️⭐️⭐️",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "9b1e1349-8e24-40ba-bbca-84a61296bc81",
                  name = "⭐️⭐️⭐️",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "66d3d050-086c-4a91-8c56-d55dc67e7789",
                  name = "⭐️⭐️",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "d3782c76-0396-467f-928e-46bf0c9d5fba",
                  name = "⭐️",
                  color = "default"
                )
              )
            )
          ),
          "Type" -> SelectOptionsProperty(
            id = "/7eo",
            `type` = "select",
            select = SelectOptions(
              Seq(
                SelectPropertyValue(
                  id = "f96d0d0a-5564-4a20-ab15-5f040d49759e",
                  name = "Article",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "4ac85597-5db1-4e0a-9c02-445575c38f76",
                  name = "TV Series",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "2991748a-5745-4c3b-9c9b-2d6846a6fa1f",
                  name = "Film",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "82f3bace-be25-410d-87fe-561c9c22492f",
                  name = "Podcast",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "861f1076-1cc4-429a-a781-54947d727a4a",
                  name = "Academic Journal",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "9cc30548-59d6-4cd3-94bc-d234081525c4",
                  name = "Essay Resource",
                  color = "default"
                )
              )
            )
          ),
          "Publisher" -> SelectOptionsProperty(
            id = ">$Pb",
            `type` = "select",
            select = SelectOptions(
              Seq(
                SelectPropertyValue(
                  id = "c5ee409a-f307-4176-99ee-6e424fa89afa",
                  name = "NYT",
                  color = "default"
                ),
                SelectPropertyValue(
                  id = "1b9b0c0c-17b0-4292-ad12-1364a51849de",
                  name = "Netflix",
                  color = "blue"
                ),
                SelectPropertyValue(
                  id = "f3533637-278f-4501-b394-d9753bf3c101",
                  name = "Indie",
                  color = "brown"
                ),
                SelectPropertyValue(
                  id = "e70d713c-4be4-4b40-a44d-fb413c8b9d7e",
                  name = "Bon Appetit",
                  color = "yellow"
                ),
                SelectPropertyValue(
                  id = "9c2bd667-0a10-4be4-a044-35a537a14ab9",
                  name = "Franklin Institute",
                  color = "pink"
                ),
                SelectPropertyValue(
                  id = "6849b5f0-e641-4ec5-83cb-1ffe23011060",
                  name = "Springer",
                  color = "orange"
                ),
                SelectPropertyValue(
                  id = "6a5bff63-a72d-4464-a5d0-1a601af2adf6",
                  name = "Emerald Group",
                  color = "gray"
                ),
                SelectPropertyValue(
                  id = "01f82d08-aa1f-4884-a4e0-3bc32f909ec4",
                  name = "The Atlantic",
                  color = "red"
                )
              )
            )
          ),
          "Summary" -> TextProperty(
            id = "?\\25"
          ),
          "Publishing/Release Date" -> DateProperty(
            id = "?ex+"
          ),
          "Link" -> URLProperty(
            id = "VVMi"
          ),
          "Read" -> CheckboxProperty(
            id = "_MWJ",
            `type` = "checkbox"
          ),
          "Status" -> SelectOptionsProperty(
            id = "`zz5",
            select = SelectOptions(
              Seq(
                SelectPropertyValue(
                  id = "8c4a056e-6709-4dd1-ba58-d34d9480855a",
                  name = "Ready to Start",
                  color = "yellow"
                ),
                SelectPropertyValue(
                  id = "5925ba22-0126-4b58-90c7-b8bbb2c3c895",
                  name = "Reading",
                  color = "red"
                ),
                SelectPropertyValue(
                  id = "59aa9043-07b4-4bf4-8734-3164b13af44a",
                  name = "Finished",
                  color = "blue"
                ),
                SelectPropertyValue(
                  id = "f961978d-02eb-4998-933a-33c2ec378564",
                  name = "Listening",
                  color = "red"
                ),
                SelectPropertyValue(
                  id = "1d450853-b27a-45e2-979f-448aa1bd35de",
                  name = "Watching",
                  color = "red"
                )
              )
            )
          ),
          "Author" ->
            MultiSelectOptionsProperty(
              id = "qNw_",
              multi_select = MultiSelectOptions(
                options = Seq(
                  MultiSelectPropertyValue(
                    id = "15592971-7b30-43d5-9406-2eb69b13fcae",
                    name = "Spencer Greenberg",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "b80a988e-dccf-4f74-b764-6ca0e49ed1b8",
                    name = "Seth Stephens-Davidowitz",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "0e71ee06-199d-46a4-834c-01084c8f76cb",
                    name = "Andrew Russell",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "5807ec38-4879-4455-9f30-5352e90e8b79",
                    name = "Lee Vinsel",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "4cf10a64-f3da-449c-8e04-ce6e338bbdbd",
                    name = "Megan Greenwell",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "833e2c78-35ed-4601-badc-50c323341d76",
                    name = "Kara Swisher",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "82e594e2-b1c5-4271-ac19-1a723a94a533",
                    name = "Paul Romer",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "ae3a2cbe-1fc9-4376-be35-331628b34623",
                    name = "Karen Swallow Prior",
                    color = "default"
                  ),
                  MultiSelectPropertyValue(
                    id = "da068e78-dfe2-4434-9fd7-b7450b3e5830",
                    name = "Judith Shulevitz",
                    color = "default"
                  )
                )
              )
            ),
          "Name" -> TitleProperty(
            id = "title"
          )
        )
      )
    assertEquals(actual, Right(expected))
  }
}
