javac src/main/java/ru/nsu/yakovleva/heapsort/Heapsort.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.yakovleva.heapsort
java -cp ./build ru.nsu.yakovleva.heapsort.Heapsort