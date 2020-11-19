#!/bin/sh
export CLASSPATH=$CLASSPATH:/usr/share/java/postgresql.jar
javac PgsqlConnection.java
javac MainMeniu.java
javac MokinioMeniu.java
javac AdministratoriausMeniu.java
java PgsqlConnection