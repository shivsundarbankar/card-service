pipeline {
    agent any

     environment {
        APP_NAME = "card-service"
        DOCKER_REPO = "shivsundar21/${APP_NAME}"
        IMAGE_TAG = "release:1.0.1"
        BRANCH_NAME = "build-to-jenkins"
     }

    tools{
        maven "maven"
    }

    stages {
        stage("Checkout Code") {
            steps {
               checkout scmGit(branches: [[name: '*/${BRANCH_NAME}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/shivsundarbankar/docker-test-app.git']])
            }
        }

        stage("Compile, Test and Build jar"){
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }

        stage("Build Docker Image"){
            steps{
                script{
                    sh 'docker build -t ${DOCKER_REPO}:${IMAGE_TAG} .' // build docker image
                }
            }
        }

        stage("Push to Docker Hub"){
            steps{
                withDockerRegistry(credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/') {
                 sh 'docker push ${DOCKER_REPO}:${IMAGE_TAG}'
                }
            }
        }
    }
}

//checkout
//Build
//deploy
//email