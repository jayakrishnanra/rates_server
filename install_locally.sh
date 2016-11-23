#!/usr/bin/env bash
mvn install:install-file -Dfile=lib/rates_stream_api-1.0-SNAPSHOT.jar -DgroupId='com.eprotech.rates.server' \
        -DartifactId=rates_stream_api -Dversion=1.0-SNAPSHOT -Dpackaging=jar