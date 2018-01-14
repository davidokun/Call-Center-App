FROM sonarqube:latest

MAINTAINER David Cuellar, davidokun99@gmail.com

ENV SONAR_JAVA_PLUGIN_VER=10.0.10260
ENV JAR_NAME=sonar-java-plugin-4.${SONAR_JAVA_PLUGIN_VER}.jar

WORKDIR /opt/sonarqube/

RUN wget https://sonarsource.bintray.com/Distribution/sonar-java-plugin/${JAR_NAME}

RUN mv /opt/sonarqube/${JAR_NAME} /opt/sonarqube/extensions/plugins/${JAR_NAME}