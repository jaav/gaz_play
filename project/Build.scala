import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "ga-z"
  val appVersion      = "0.9"

  val appDependencies = Seq(
    "com.amazonaws" % "aws-java-sdk" % "1.5.0",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.file("Local Play Repository", file("/Users/jefw/Java/play-2.1.0/repository/local"))
  )

}
