# waits

## How to run
### java
``` bash
./gradlew jvmJar
java -jar ./app/build/libs/app-jvm.jar
```

### linuxX64
``` bash
./gradlew linuxX64MainBinaries

./app/build/bin/linuxX64/debugExecutable/app.kexe
./app/build/bin/linuxX64/releaseExecutable/app.kexe
./app/build/bin/linuxX64/debugTest/test.kexe
```

## How to format codes
``` bash
./gradlew ktfmtFormat
```
