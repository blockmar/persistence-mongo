Persistence Mongo
=================

Persistence Mongo is an implementation of Persistence Core, a simple Object Repository implementation for Java.
This project contains the MongoDB implementation. See Persistence-Core for skeleton interfaces.

Building/Installing
-------------------

Persistence Mongo is build using SBT (https://github.com/harrah/xsbt/wiki). The super fast instruction:

     $ sbt
     > compile
     > package
     > publish-local

The *publish-local* command installs the jar into your local Ivy repository. See the SBT manual for Maven instructions (hint: publishTo)

### Dependencies

Persistence Mongo is dependent on Persistence Core, download and build Core first.

### Why SBT? Is Persistence Mongo dependent on Scala?

No there is no dependency between the Persistence-projects and Scala. The build tolls add Scala as a dependency in the Maven pom.xml files but it is not used.
The choice of SBT as the build tool was made because the Persistence-projects are designed to work with the Play Framework 2.0 that also uses SBT.

Maven Repository
----------------

A SNAPSHOT-build of Persistence Mongo is available from http://hostingbyblockmar.com/maven/public/

    <dependency>
        <groupId>com.blockmar.persistence</groupId>
        <artifactId>mongo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>

The POM-file incorrectly contains a dependency to *Scala* this is due to the use of SBT and not actually used.

License
-------

Code is licensed under the Simplified BSD License

Copyright 2011 Anders Blockmar. All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are
permitted provided that the following conditions are met:

   1. Redistributions of source code must retain the above copyright notice, this list of
      conditions and the following disclaimer.

   2. Redistributions in binary form must reproduce the above copyright notice, this list
      of conditions and the following disclaimer in the documentation and/or other materials
      provided with the distribution.

THIS SOFTWARE IS PROVIDED BY ANDERS BLOCKMAR ''AS IS'' AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ANDERS BLOCKMAR OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those of the
authors and should not be interpreted as representing official policies, either expressed
or implied, of Anders Blockmar.