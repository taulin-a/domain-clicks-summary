#!/bin/bash

mvn clean package > /dev/null 2>&1 && java -jar ./target/domain-clicks-summary-1.0-SNAPSHOT-jar-with-dependencies.jar -f "$1"