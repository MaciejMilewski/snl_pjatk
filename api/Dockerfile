# Multi Stage build

# Base Image and name stage as "builder"
FROM maven:3-openjdk-17 AS builder

# Create App Directory inside our container
WORKDIR /maciek/app/src/

# Copy files
COPY src ./
COPY pom.xml ../


RUN mvn -f /maciek/app/pom.xml clean package -DskipTests

#### 2nd Stage ####

FROM openjdk:17.0.1

WORKDIR /maciek/lib/

# Copy the Jar from the first Stage (builder) to the 2nd stage working directory
COPY --from=builder /maciek/app/target/snl-0.0.1-SNAPSHOT.jar ./pjatk_dyplomowa_backend.jar
COPY --from=builder /maciek/app/src/main/resources ./resources

# Expose the port to the inner container communication network
EXPOSE 8080

# Run the Java Application
ENTRYPOINT [ "java","-jar","/maciek/lib/pjatk_dyplomowa_backend.jar"]
