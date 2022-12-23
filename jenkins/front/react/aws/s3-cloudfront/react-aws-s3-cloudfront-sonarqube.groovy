/** the path where sonar-scanner will be installed */
def sonnarPath = ''
pipeline {
    /**
    * IMPORTANT:
    * to get this pipeline works, you have to install nodejs,
    * npm and aws-cli and add they to the PATH environment variable.
    */
    agent any
    environment {
        /**
        * This is an example to manage BUCKET_NAME, and DISTRIBUTION_ID values depending on GIT_BRANCH,
        * but you could manage this values with your own approach.
        */
        BUCKET_NAME = "${GIT_BRANCH == 'dev' ? 'bucker_name_dev' : 'bucker_name_prod'}"
        DISTRIBUTION_ID = "${GIT_BRANCH == 'dev' ? 'ABCDEFGHIJKLMN' : 'OPQRSTUVXYZ'}"
        AWS_REGION = 'us-east-1'
    }

    stages {
        /**
        * To run this stage you need to install SonarQube Scanner pluging and configure it
        * read this link:
        * https://docs.sonarqube.org/latest/analyzing-source-code/scanners/jenkins-extension-sonarqube/
        */
        stage('sonar-scanner') {
            steps {
                /**
                * install sonar-scanner
                */
                script {
                    sonnarPath = tool('sonar-scanner')
                }
                withSonarQubeEnv(installationName: 'mydevopsway') {
                    echo 'running scanner'
                    sh "${sonnarPath}/bin/sonar-scanner"
                }
            }
        }
        stage('install dependencies') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'; branch pattern: 'PR-\\d+', comparator: 'REGEXP'
                }
            }

            steps {
                echo 'installing dependencies'
                sh '''
                    node --version && npm --version
                    npm ci
                '''
            }
        }
        stage('test') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'; branch pattern: 'PR-\\d+', comparator: 'REGEXP'
                }
            }
            steps {
                echo 'testing'
                sh '''
                    CI=true npm test --if-present
                '''
            }
        }
        stage('build') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'; branch pattern: 'PR-\\d+', comparator: 'REGEXP'
                }
            }
            steps {
                echo 'building'
                sh '''
                    npm run build
                '''
            }
        }
        stage('deploy') {
            when {
                anyOf {
                    branch 'main'; branch 'dev'
                }
            }
            steps {
                echo 'deploy'
                withCredentials([
                    usernamePassword(
                        credentialsId: 'aws_credentials_id',
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY')
                    ]) {
                    sh "aws s3 sync ./build s3://${BUCKET_NAME} --delete --region=${AWS_REGION}"
                    sh "aws cloudfront create-invalidation --distribution-id ${DISTRIBUTION_ID} --paths '/*'"
                    }
            }
        }
    }
}
