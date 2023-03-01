# Multi Stage build

# Base Image and name stage as "builder"
FROM maven:3-openjdk-17 AS builder

# Create App Directory inside our container
WORKDIR /payments_company/app/src/

# Copy files
COPY src ./
COPY pom.xml ../

RUN mvn -f /payments_company/app/pom.xml clean package

#### 2nd Stage ####

FROM openjdk:17.0.1

WORKDIR /payments_company/lib/

COPY --from=builder /payments_company/app/target/transactions-management-0.0.1-SNAPSHOT.jar ./transactions-management.jar

# Expose the port to the inner container communication network
EXPOSE 3000

# Run the Java Application
ENTRYPOINT [ "java", "-jar", "/payments_company/lib/transactions-management.jar" ]