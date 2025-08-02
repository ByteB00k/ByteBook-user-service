#!/bin/bash

PASSWORD=secret
DNAME="CN=localhost, OU=Dev, O=ByteBook, L=Moscow, S=Moscow, C=RU"

mkdir -p certs

# Keystore
keytool -genkey -keystore certs/kafka.keystore.jks -alias localhost -keyalg RSA -storepass $PASSWORD -keypass $PASSWORD -dname "$DNAME"

# Truststore
keytool -export -keystore certs/kafka.keystore.jks -alias localhost -file certs/ca-cert -storepass $PASSWORD
keytool -import -keystore certs/kafka.truststore.jks -alias localhost -file certs/ca-cert -storepass $PASSWORD -noprompt