#!/bin/bash
cd source
mvn clean deploy -Prelease-sign-artifact
