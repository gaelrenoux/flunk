organization := "gaelrenoux/flunk"
name := "flunk"
version := "0.1-SNAPSHOT"

scalaVersion := V.scala

resolvers ++= Seq(
    "Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
    Resolver.mavenLocal
)

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % V.flink % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % V.flink % "provided"
)

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= flinkDependencies
  )

/* Assembly configuration */
assembly / mainClass := Some("gaelrenoux.flunk.Job")
assembly / assemblyOption  := (assembly / assemblyOption).value.copy(includeScala = false) // exclude Scala library from assembly

/* Testing configuration  */
Test / fork := true
Test / testForkedParallel := true // run tests in parallel on the forked JVM
Test / testOptions += Tests.Argument("-oD") // show the time taken by each test

/* Makes processes is SBT cancelable without closing SBT */
Global / cancelable := true
