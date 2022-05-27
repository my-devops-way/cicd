# AWS REACT
Pipelines here are designed to build, ...,  test and  deploy **REACT** applications in  **AWS S3/CloudFront** configurations (see Architecture [diagram](#architecture))

## Index
- [Architecture](#architecture)
- [Pipelines](#pipelines)
### Architecture

![Architecture Diagram](/svg/front/aws-s3-cloudfront.svg)

### Pipelines
- [aws-react-s3-cloudfront.yaml](./react-aws-s3-cloudfront.yaml) -> test, buil and deploy (single branch)
- [aws-react-s3-cloudfront-sonarqube.yaml](./react-aws-s3-cloudfront-sonarqube.yaml) -> scan code, test, build, and deploy (single branch)
- [pr.yaml](./pr.yaml) -> test and build on pull_request (multibranch)
- [pr-sonarqube.yaml](./pr-sonarqube.yaml) -> scan (sonarqube), test and build on pull_request (multibranch)

