#!/usr/bin/env bash
OS_TYPE=android8 mvn clean test
OS_TYPE=android6 mvn clean test
OS_TYPE=ios11 mvn clean test
OS_TYPE=ios12 mvn clean test