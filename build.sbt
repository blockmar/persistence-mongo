organization := "com.blockmar.persistence"

name := "mongo"

version := "1.0-SNAPSHOT"

// Also require morphia-0.99.jar. See http://code.google.com/p/morphia/ Not in any known repo.

libraryDependencies ++= Seq(
	"org.mongodb" % "mongo-java-driver" % "2.7.3",
	"com.blockmar.persistence" % "core" % "1.0-SNAPSHOT" )