pipeline {
    agent any

    tools{
        maven "maven"
    }

    stages {
        stage("Checkout Project") {
            steps {
               checkout scmGit(branches: [[name: '*/jenkins-docker-test']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/shivsundarbankar/docker-test-app.git']])
            }
        }

        stage("Build"){
            steps{
                script{
                    sh 'mvn clean install'
                }
            }
        }

        stage("Build Image"){
            steps{
                script{
                    sh 'docker build -t shivsundar21/docker-test-app-copy:1.0.0 .' // build docker image
                }
            }
        }

        stage("Push to docker hub"){
            steps{
                withDockerRegistry(credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/') {
                 sh 'docker push shivsundar21/docker-test-app-copy:1.0.0'
                }
            }
        }
    }
}

//checkout
//Build
//deploy
//email