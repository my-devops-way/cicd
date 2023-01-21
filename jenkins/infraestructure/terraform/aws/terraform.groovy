pipeline {
    agent {
        kubernetes {
            defaultContainer 'jenkins-terraform'
            yamlFile 'pod.yaml'
        }
    }
    environment {
        AWS_DEFAULT_REGION = 'us-east-1'
        TF_WORSPACE = "${GIT_BRANCH}"
        AWS_CREDENTIAL_ID = "${GIT_BRANCH == 'main' ? 'production_credentials_id' : 'development_credentials_id'}"
    }
    stages {
        stage('validation') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'; branch pattern: 'PR-\\d+', comparator: 'REGEXP'
                }
            }
            steps {
                sh 'terraform fmt -check -recursive'
                withCredentials([
                        usernamePassword(
                        credentialsId: "${AWS_CREDENTIAL_ID}",
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY'
                        )
                ]) {
                    sh '''
                        terraform init
                        terraform validate
                        '''
                }
            }
        }
        stage('plan') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'
                }
            }
            steps {
                withCredentials([
                        usernamePassword(
                        credentialsId: "${AWS_CREDENTIAL_ID}",
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY'
                        )
                ]) {
                    sh 'terraform init'
                    sh  "terraform workspace select ${TF_WORSPACE} || terraform workspace new ${TF_WORSPACE}"
                    sh  "terraform plan -var-file=vars/${GIT_BRANCH} -out tfplan"
                }
            }
        }
        stage('deploy') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'
                }
            }
            steps {
                withCredentials([
                        usernamePassword(
                        credentialsId: "${AWS_CREDENTIAL_ID}",
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY'
                        )
                ]) {
                    sh 'terraform init'
                    sh   "terraform workspace select ${TF_WORSPACE} || terraform workspace new ${TF_WORSPACE}"
                    sh 'terraform show -json tfplan'
                    sh 'terraform apply tfplan'
                }
            }
        }
    }
}
