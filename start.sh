CLASSPATH=.

for lib_jar in ./*.jar
do
CLASSPATH=$CLASSPATH:${lib_jar}
done

for lib_jar in lib/*.jar
do
CLASSPATH=$CLASSPATH:${lib_jar}
done

for lib_jar in deploy/*.jar
do
CLASSPATH=$CLASSPATH:${lib_jar}
done

java  -Dlog4j.configuration=conf/log4j.properties  -classpath $CLASSPATH -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8787 server.Container
