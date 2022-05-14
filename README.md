# CICD

This repository contains a collection of generic CI/CD files that can be used to create common pipelines on diferent tools such as github actions, gitlab cicd, jenkins and bitbucket pipelines.

## Introduction

When a new project is started usually it is based on a common configuration. Here We call common configuration a set of tools often used to create a product. For example, a common configuration to create a static web page could be as follows:

- CI/CD provider:  Github Actions
- Frontend framework: React
- Cloud services:
  - AWS s3
  - AWS CloudFront
- Code Analysis: Sonarquebe

The above setup is very common and the pipeline needed to deploy this project is also very common: a pipeline with general steps to scan code, install dependencies, build project, test project, copy files to S3, and clear cache in Cloudfront. Like this common configuration there are many more for example:

- github/fronend/angular/aws/s3-cloudfront-sonarcloud
- jenkins/backend/express/aws/ecs-fargate
- gitlab-cicd/backend/laravel/aws/ec2
- ...

And all of them also have a common pipeline to make things work. So if You are working on a new project perhaps the pipeline you need already exists and you don't need to start from scratch.

This repository was created to store these commons pipelines on the same site.


## Use

The folder structure has the next pattern:

[CICD provider]/[Porject Kind]/[Languaje or Framework]/[Cloud Provider]/

Just check if the pipeline exists, copy it to your project, and modify it as necessary.

**NOTE**

As the pipelines are created, they will be added to the repository.
