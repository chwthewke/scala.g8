import sbt._
import sbt.Keys._
import sbtbuildinfo._, BuildInfoKeys._
import sbtrelease.Version

object SbtBuildInfo {
  val shortVersion = SettingKey[String]( "short-version" )

  def buildSettings( targetPackage: String = "" ) = BuildInfoPlugin.projectSettings ++ Seq(
    buildInfoPackage := targetPackage,
    buildInfoObject := upperCamel( name.value ),
    shortVersion := shortenVersion( version.value ),
    buildInfoKeys := Seq( version, shortVersion ) )

  private def shortenVersion( version: String ): String = Version( version ) match {
    case Some( Version( major, subs, _ ) ) =>
      Version( major, subs.take( 1 ), None ).string
    case _ => 
      throw new IllegalArgumentException( s"Could not parse version \$version" )
  }

  def startCase( s : String ) = s.toLowerCase.split( " " ).map( _.capitalize ).mkString( " " )
  def wordOnly( s : String ) = s.replaceAll( """\W""", "" )
  def upperCamel( s : String ) = wordOnly( startCase( s ) )
}
