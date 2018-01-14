FROM develar/java

MAINTAINER David Cuellar, davidokun99@gmail.com

RUN mkdir /usr/local/call-center-app

WORKDIR /usr/local/call-center-app

ENTRYPOINT ["java", "-jar", "call-center-app.jar", "--acceptLicense", "/usr/local/call-center-app"]