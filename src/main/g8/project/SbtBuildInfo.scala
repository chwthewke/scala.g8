import sbt._
import sbt.Keys._
import sbtbuildinfo.Plugin._
import sbtrelease.Version

object SbtBuildInfo {
  val shortVersion = SettingKey[String]("short-version")

  def buildSettings(targetPackage: String = "") = buildInfoSettings ++ Seq(
    sourceGenerators in Compile <+= buildInfo,
    buildInfoPackage := targetPackage,
    shortVersion := shortenVersion(version.value),
    buildInfoKeys := Seq(version, shortVersion),
    managedSourceDirectories in Compile += (sourceManaged in Compile).value / "sbt-buildinfo")

  private def shortenVersion(version: String): String = Version(version) match {
    case Some(Version(major, minor, _, _)) => Version(major, minor, None, None).string
    case _                                 => throw new IllegalArgumentException(s"Could not parse version \$version")
  }
}
