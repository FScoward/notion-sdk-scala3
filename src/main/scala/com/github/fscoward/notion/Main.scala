package com.github.fscoward.notion

import sttp.client3._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

@main def hello: Unit =
  val httpClient = new HttpClient()
  println(httpClient.listAllUsers)