pipeline {
    agent any

    environment {
        APP_NAME = "card-service"
        DOCKER_REPO = "shivsundar21/${APP_NAME}"
        IMAGE_TAG = "release-1.0.1"  // Use '-' instead of ':' in tags
        BRANCH_NAME = "build-to-jenkins"
    }

    tools {
        maven "maven"  // Ensure 'maven' is properly configured in Jenkins tools
    }

    stages {
        stage("Checkout Code") {
            steps {
                script {
                    checkout scmGit(
                        branches: [[name: "origin/${BRANCH_NAME}"]],  // Fix branch syntax
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/shivsundarbankar/docker-test-app.git']]
                    )
                }
            }
        }

        stage("Compile, Test, and Build JAR") {
            steps {
                script {
                    sh "mvn clean install"
                }
            }
        }

        stage("Build Docker Image") {
            steps {
                script {
                    sh "docker build -t \"${DOCKER_REPO}:${IMAGE_TAG}\" ."
                }
            }
        }

        stage("Push to Docker Hub") {
            steps {
                withDockerRegistry(credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/') {
                    sh "docker push \"${DOCKER_REPO}:${IMAGE_TAG}\""
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully! 🎉"
        }
        failure {
            echo "Pipeline failed! Check the logs. ❌"
        }
    }
}
