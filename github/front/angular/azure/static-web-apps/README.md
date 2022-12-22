
# AZURE ANGULAR

Pipelines here are designed to build, ...,  test and  deploy **ANGULAR** applications in
**AZURE Static Web Apps** configurations (see Architecture [diagram](#architecture)).

## Index

- [Architecture](#architecture)
- [Pipelines](#pipelines)

### Architecture

![Architecture Diagram](/svg/front/azure_static_web_apps.svg)

### Pipelines

- [angular-azure-static-web-apps.yaml](./angular-azure-static-web-apps.yaml) ->
  test, buil and deploy.
- [angular-azure-static-web-apps-sonarqube.yaml](./angular-azure-static-web-apps-sonarqube.yaml) ->
  scan code, test, build, and deploy.
- [pr.yaml](../../../common-pull-requests/pr.yaml) ->
  test and build on pull request (multibranch)
- [pr-sonarqube.yaml](../../../common-pull-requests/pr-sonarqube.yaml) ->
  scan (sonarqube), test and build on pull request (multibranch).
