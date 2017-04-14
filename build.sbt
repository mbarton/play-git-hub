organization := "com.madgag"

name := "play-git-hub"

description := "Group of library code for Play, Git, and GitHub"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.4.11",
  "com.squareup.okhttp3" % "okhttp" % "3.6.0",
  "com.lihaoyi" %% "fastparse" % "0.4.2",
  "com.madgag.scala-git" %% "scala-git" % "3.4",
  "com.madgag.scala-git" %% "scala-git-test" % "3.4" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

resolvers += Resolver.sonatypeRepo("releases")

updateOptions := updateOptions.value.withCachedResolution(true)

scmInfo := Some(ScmInfo(
  url("https://github.com/rtyley/play-git-hub"),
  "scm:git:git@github.com:rtyley/play-git-hub.git"
))

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)
