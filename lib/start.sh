CLASSPATH=.
for lib_jar in ./*.jar
do
CLASSPATH=$CLASSPATH:${lib_jar}
done

java -classpath $CLASSPATH -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8787 server.Container 

