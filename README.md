Qdrakeboo
======

The private streaming service for geeks.

[![Travis CI](https://travis-ci.com/bbortt/qdrakeboo.svg?branch=master)](https://travis-ci.com/bbortt/qdrakeboo)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bbortt_qdrakeboo&metric=alert_status)](https://sonarcloud.io/dashboard?id=bbortt_qdrakeboo)
[![codecov](https://codecov.io/gh/bbortt/qdrakeboo/branch/master/graph/badge.svg)](https://codecov.io/gh/bbortt/qdrakeboo)
[![Blazing Fast](https://img.shields.io/badge/speed-blazing%20%F0%9F%94%A5-brightgreen.svg?style=flat-square)](https://twitter.com/acdlite/status/974390255393505280)
[![License: Apache 2](https://img.shields.io/badge/License-Apache2-blue.svg)](https://opensource.org/licenses/MIT)

# What this is about?

A streaming application with which you can upload and watch your videos and
movies. You should even be able to share it with your friends in a future
version. At your own risk, of course.

# Project setup

The "application" consists of three separate projects:
* [bbortt/qdrakeboo](https://github.com/bbortt/qdrakeboo) - This project, the
core source
* [bbortt/qdrakeboo-kubectl](https://github.com/bbortt/qdrakeboo-kubeclt) - The
Docker image used at build time
* [bbortt/qdrakeboo-deployment](https://github.com/bbortt/qdrakeboo-deployment) -
All Kubernetes stuff such as deployment yamls and required scripts

# Some TODO's accross all projects

* Global error handling (concept/implementation) in frontend
* Read user-id in federated-user-management
* Check jwt scopes in edge-gateway
* Is `contextful-winston-logger` useful? can it be published?
* Actuators for nginx
* Resource configurations for k8s
* Tracing (Zipkin vs Jaeger)
* Monitoring (Logs as well as Metrics)

# License

This project is licensed under the terms of the [Apache 2.0 License](https://github.com/bbortt/qdrakeboo/blob/master/LICENSE).
