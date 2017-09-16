A [Giter8][g8] template for Akka HTTP in a docker image

Prerequisites:
- JDK 8
- sbt 0.13.13 or higher
- Docker 

Featuring:
- Latest version of the libraries (akka, scala, spec2 json4s)
- Json4s library with native backend
- Spec2 for the test with an example API spec
- Scalariform formatting according to Scala Style Guide
- Optimized dockerization using alpine with openjdk8 (image is ~120MB)
- Slf4j, TypesafeConfig and sbt revolver for code hot reloading
- Example graylog_gelf configuration (disabled by default)


Open a console and run the following command to apply this template:
 ```
sbt -Dsbt.version=0.13.15 new https://github.com/araspitzu/akka-http-docker.g8
 ```

This template will prompt for the following parameters. Press `Enter` if the default values suit you:
- `name`: Becomes the name of the project.
- `scala_version`: Specifies the Scala version for this project.
- `akka_http_version`: Specifies which version of Akka HTTP should be used for this project.
- `akka_version`: Specifies which version of Akka should be used for this project.
- `json4sVersion`: Specifies which version of Json4s should should be used for this project.
- `organization`: Specifies the organization for this project.

To publish the docker image of the template you can `cd` into the `name` folder and run 
```
sbt docker:publishLocal     
```
the resulting image will have version tag 0.0.1 appended

Verify the image has been published with:
```
docker image ls 
```

To run the example webserver execute:
```
docker run --rm -p8080:8080 <project_name>:0.0.1
```