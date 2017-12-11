name := "stalker"

version := "0.1"

scalaVersion := "2.12.4"

resolvers += Resolver.bintrayRepo("hseeberger", "maven")

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http" % "10.0.11",
    "com.typesafe.play" %% "play-json" % "2.6.7",
    "de.heikoseeberger" %% "akka-http-play-json" % "1.18.0"
  )
}