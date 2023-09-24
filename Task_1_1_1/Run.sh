javac src/main/java/ru/nsu/yakovleva/Heapsort.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.yakovleva
java -cp ./build ru.nsu.yakovleva.Heapsort