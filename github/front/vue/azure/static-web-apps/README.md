# AZURE VUE 
Pipelines here are designed to build, ...,  test and  deploy **VUE** applications in  **AZURE Static Web Apps** configurations (see Architecture [diagram](#architecture)).

## Index
- [Architecture](#architecture)
- [Pipelines](#pipelines)
### Architecture

![Architecture Diagram](/svg/front/azure_static_web_apps.svg)

### Pipelines
- [vue-azure-static-web-apps.yaml](./vue-azure-static-web-apps.yaml) -> test, buil and deploy.
- [vue-azure-static-web-apps-sonarqube.yaml](./vue-azure-static-web-apps-sonarqube.yaml) -> scan code, test, build, and deploy.
- [pr.yaml](../../../common-pull-requests/pr.yaml) -> test and build on pull_request (multibranch).
- [pr-sonarqube.yaml](../../../common-pull-requests/pr-sonarqube.yaml) -> scan (sonarqube), test and build on pull_request (multibranch).
