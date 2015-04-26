import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbteclipse.core.EclipsePlugin._
import scalariform.formatter.preferences._
import scoverage.ScoverageSbtPlugin
import sbtbuildinfo.Plugin._

object $name;format="Camel"$Build extends Build {

  object Dependencies {
    val scalazVersion = "7.1.0"

    val scalaz = "org.scalaz" %% "scalaz-core" % scalazVersion withSources () withJavadoc ()

    val scalatest = "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources () withJavadoc ()

    val scalacheck = Seq(
      "org.scalacheck" %% "scalacheck" % "1.11.4" % "test" withSources () withJavadoc (),
      "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test" withSources () withJavadoc ()      
    )

  }

  lazy val $name;format="camel"$ScalariformSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := defaultPreferences
      .setPreference( AlignSingleLineCaseStatements, true )
      .setPreference( SpaceBeforeColon, true )
      .setPreference( SpaceInsideParentheses, true )    
  )

  lazy val sharedSettings =
    Seq(
      organization := "$organisation$",
      scalaVersion := "$scalaVersion$")

  lazy val $name;format="camel"$Settings = 
    Defaults.coreDefaultSettings ++
    SbtBuildInfo.buildSettings("$package$") ++
    SbtEclipse.buildSettings ++
    $name;format="camel"$ScalariformSettings ++
    sharedSettings ++
    Seq(
      libraryDependencies ++= Seq(
          Dependencies.scalatest,
          Dependencies.scalaz ) ++ 
          Dependencies.scalacheck,
        scalacOptions ++= Seq( "-feature", "-deprecation" ),
        unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil,
        unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil
    )

  lazy val $name;format="camel"$ = Project(
    id = "$name;format="norm"$",
    base = file( "." ),
    settings = Defaults.coreDefaultSettings ++
      sharedSettings ++
      Seq( name := "$name;format="norm"$" )
  ).aggregate(
    $module;format="camel"$
  )

  lazy val $module;format="camel"$ = Project(
    id = "$module;format="norm"$",
    base = file( "$module;format="norm"$" ),
    settings = $name;format="camel"$Settings ++
      Seq(
        name := "$module;format="norm"$",
        mainClass := Some("$package$.Main"),
        initialCommands := "import $package$",
        buildInfoObject := "$module;format="camel"$BuildInfo"
      )
  )
}
