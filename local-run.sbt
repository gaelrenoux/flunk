
/* Makes run command include the provided dependencies */
Compile / run  := Defaults.runTask(
  Compile / fullClasspath,
  Compile / run / mainClass,
  Compile / run / runner
).evaluated

Compile / run / fork := true

lazy val mainRunner = project.in(file("mainRunner")).dependsOn(RootProject(file("."))).settings(
  // we set all provided dependencies to none, so that they are included in the classpath of mainRunner
  libraryDependencies := (libraryDependencies in RootProject(file("."))).value.map{
    module => module.configurations match {
      case Some("provided") => module.withConfigurations(None)
      case _ => module
    }
  }
)
