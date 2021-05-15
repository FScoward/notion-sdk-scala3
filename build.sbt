val scala3Version = "3.0.0-RC3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "notion-sdk-scala3",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "com.softwaremill.sttp.client3" %% "core" % "3.3.1",
      "io.circe" %% "circe-core" % "0.14.0-M6",
      "com.softwaremill.sttp.client3" %% "circe" % "3.3.1",
      "io.circe" %% "circe-generic" % "0.14.0-M6",
      "org.scalameta" %% "munit" % "0.7.26" % Test    )
  )
