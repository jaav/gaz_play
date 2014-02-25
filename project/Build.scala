import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "tick5"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "play-aws"      % "play-aws_2.10"      % "0.1",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.file("Local Play Repository", file("/Users/jefw/Java/play-2.1.0/repository/local"))
  )

}
