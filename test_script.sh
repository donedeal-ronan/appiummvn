#!/usr/bin/env bash
OS_TYPE=android27 mvn clean test
OS_TYPE=android23 mvn clean test
OS_TYPE=ios mvn clean test
