# zcafe

# build
- ./gradlew clean
- ./gradlew buildFatJar   =>  build/libs/zcafe-all.jar

# run
- local : java -jar build/libs/zcafe-all.jar -config=application.conf -config=application-local.conf -port=7070
- prod : java -jar build/libs/zcafe-all.jar -config=application.conf -config=application-prod.conf -port=9090