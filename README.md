Griddly
=======

A small Java library to make working with grids just a little more fun.

# Release Process

Need to specify JAVA_HOME build. Easiest way to get this variable is with `/usr/libexec/java_home`

Also need `gpg` installed on command line... run brew install gpg
Then generated a signature


	mvn release:clean
	mvn release:prepare
	mvn release:perform
	
