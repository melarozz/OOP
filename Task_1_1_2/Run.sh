javac src/main/java/ru/nsu/yakovleva/polynomial/Polynomial.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.yakovleva.polynomial
java -cp ./build ru.nsu.yakovleva.polynomial.Polynomial