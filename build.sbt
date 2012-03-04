organization := "com.blockmar.persistence"

name := "mongo"

version := "1.0-SNAPSHOT"

// The Morphia repository
resolvers += "Morphia repository" at "http://morphia.googlecode.com/svn/mavenrepo/"

libraryDependencies ++= Seq(
	"org.mongodb" % "mongo-java-driver" % "2.7.3",
	"com.google.code.morphia" % "morphia" % "0.99",
	"com.blockmar.persistence" % "core" % "1.0-SNAPSHOT" )
	
crossPaths := false