pipeline {
    /**
    IMPORTANT:
    to get this pipeline works, you have to install nodejs, npm  and aws-cli in your nodes.
    */
    /**
    agent {
        kubernetes {
            defaultContainer 'jenkins-slave'
            inheritFrom 'jenkins-slave-aws-nodejs'
        }
    }
    */
    agent any
    environment {
        //here your environment variables logic, this is only an exaple.
        BUCKET_NAME = "${GIT_BRANCH == 'dev' ? 'bucker_name_dev' : 'bucker_name_prod'}"
        DISTRIBUTION_ID = "${GIT_BRANCH == 'dev' ? 'ABCDEFGHIJKLMN' : 'OPQRSTUVXYZ'}"
        AWS_REGION = 'us-east-1'
    }

    stages {
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
                /* groovylint-disable-next-line LineLength */
                withCredentials([usernamePassword(credentialsId: 'xxxx-xxxx-xxxx-xxxx-xxxx', usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh "aws s3 sync ./build s3://${BUCKET_NAME} --delete --region=${AWS_REGION}"
                    sh "aws cloudfront create-invalidation --distribution-id ${DISTRIBUTION_ID} --paths '/*'"
                }
            }
        }
    }
}
