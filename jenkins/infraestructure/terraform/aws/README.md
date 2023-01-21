# AWS TERRAFORM

Pipelines here are designed to validate terraform resources on pull_request events and to deploy
these resources to aws on push events.

To use these pipelines you need to create a
[Terraform Remote Backend](https://www.terraform.io/language/settings/backends/s3) before.
To create the remote backend resources  you could use
[this terraform module.](https://registry.terraform.io/modules/my-devops-way/s3-dynamodb-remote-backend/aws/latest) 

## Index

- [Architecture](#architecture)
- [Pipelines](#pipelines)

### Architecture

![Architecture Diagram](/svg/infrastructure/cicd_terraform_aws_jenkins_flow.svg)

### Pipelines

- [terraform.groovy](./terraform.groovy) ->
  validate, scan and deploy.
