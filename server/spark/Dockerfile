FROM ubuntu

# Install maven
RUN apt-get update
RUN apt-get upgrade -y


# install java 8
RUN apt-get install -y openjdk-8-jdk

# set java 8 as default version
RUN update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java

# install maven
#RUN apt-get install -y maven

# install gradle tool manager
RUN apt-get install gradle -y

RUN apt-get install vim -y

WORKDIR /code

# RUN gradle init --type=java-application

# Prepare by downloading dependencies
ADD build.gradle /code
# RUN ["mvn", "dependency:resolve"]
# RUN ["mvn", "verify"]

RUN gradle build

# Adding source, compile and package into a fat jar
#ADD src /code/src
# RUN ["mvn", "package"]

EXPOSE 80
# CMD ["java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]


CMD tail -f /dev/null
# CMD ["gradle", "run"]