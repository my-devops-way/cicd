# AWS VUE 
Pipelines here are designed to build, ...,  test and  deploy **VUE** applications in  **AWS S3/CloudFront** configurations (see Architecture [diagram](#architecture)).

## Index
- [Architecture](#architecture)
- [Pipelines](#pipelines)
### Architecture

![Architecture Diagram](/svg/front/aws-s3-cloudfront.svg)

### Pipelines
- [vue-aws-s3-cloudfront.yaml](./vue-aws-s3-cloudfront.yaml) -> test, buil and deploy. 
- [vue-aws-s3-cloudfront-sonarqube.yaml](./vue-aws-s3-cloudfront-sonarqube.yaml) -> scan code, test, build, and deploy.
- [pr.yaml](../../../common-pull-requests/pr.yaml) -> test and build on pull_request (multibranch).
- [pr-sonarqube.yaml](../../../common-pull-requests/pr-sonarqube.yaml) -> scan (sonarqube), test and build on pull_request (multibranch).
