import sbt._
import Keys._
import play.Play.autoImport._
import PlayKeys._
import java.text.SimpleDateFormat
import sys.process.stringSeqToProcess
import com.typesafe.sbt.web.SbtWeb
import com.typesafe.sbt.web.SbtWeb.autoImport._
import com.typesafe.sbt.less.SbtLess.autoImport._
import com.typesafe.sbt.rjs.SbtRjs.autoImport._
import com.typesafe.sbt.digest.SbtDigest.autoImport._
import com.typesafe.sbt.gzip.SbtGzip.autoImport._


object ApplicationBuild extends Build {


  val name = "play-angular-require-seed"
  val organization = "your.organization"
  val appVersion = "2.3.1"
  val SCALA_VERSION = "2.11.1"


  ///lazy val root = (project in file(".")).enablePlugins(PlayScala)

  // Dependencies
  val appDependencies = Seq(
    filters,
    cache,
    // WebJars (i.e. client-side) dependencies
    "org.webjars" % "requirejs" % "2.1.11-1",
    "org.webjars" % "underscorejs" % "1.6.0-3",
    "org.webjars" % "jquery" % "1.11.1",
    "org.webjars" % "bootstrap" % "3.1.1-1" exclude("org.webjars", "jquery"),
    "org.webjars" % "angularjs" % "1.2.16-2" exclude("org.webjars", "jquery")
  )


  val SCALAC_OPTIONS = Seq(
    "-target:jvm-1.7",
    "-encoding", "UTF-8",
    "-deprecation", // warning and location for usages of deprecated APIs
    "-feature", // warning and location for usages of features that should be imported explicitly
    "-unchecked", // additional warnings where generated code depends on assumptions
    "-Xlint", // recommended additional warnings
    "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
    "-Ywarn-inaccessible",
    "-Ywarn-dead-code"
  )

  val SeedProject = Project(name, file(".")).enablePlugins( play.PlayScala ).settings(
    version := appVersion,
    scalaVersion := SCALA_VERSION,
    libraryDependencies ++= appDependencies,
    scalacOptions := SCALAC_OPTIONS,
    pipelineStages := Seq(rjs, digest, gzip),
    RjsKeys.paths += ("jsRoutes" -> ("/jsroutes" -> "empty:"))
  )

  override def rootProject = Some(SeedProject)

}