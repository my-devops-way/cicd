
# AWS FRONTEND

Pipelines here are designed to build, ...,  test and  deploy **COMMON FRONEND** (react, angular, vue...) applications
in  **AWS S3/CloudFront** configurations (see Architecture [diagram](#architecture))

## Index

- [Architecture](#architecture)
- [Pipelines](#pipelines)

### Architecture

![Architecture Diagram](/svg/front/aws-s3-cloudfront.svg)

### Pipelines

- [frontend-aws-s3-cloudfront.groovy](./frontend-aws-s3-cloudfront.groovy) ->
  test, buil and deploy
- [./frontend-aws-s3-cloudfront-sonarqube.groovy](./frontend-aws-s3-cloudfront-sonarqube.groovy) ->
  scan code, test, build, and deploy

### Prerequisites

- Jenkins server  already running.
- (optional) Sonarqube server already running.
- aws-cli, nodejs and npm installed:
  - **jobs running in docker/kubernetes:** use
    [jenkins/inbound-agent](https://hub.docker.com/r/jenkins/inbound-agent/) as
    based image and install aws-cli, node, and npm on it.
  - **jobs running in master node (jenkins single server):**
    [install](./Dockerfile) aws-cli, node and npm in the jenkins machine and be
    sure that they are in the $PATH for jenkins user.
- plugins:
  - [sonarqube scanner](https://plugins.jenkins.io/sonar/)

