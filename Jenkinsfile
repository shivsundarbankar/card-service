pipeline {
    agent any

    environment {
        APP_NAME = "card-service"
        DOCKER_REPO = "shivsundar21/${APP_NAME}"
        IMAGE_TAG = "1.0.2"  // Use '-' instead of ':' in tags
        BRANCH_NAME = "build-to-jenkins"
        KUBERNETES_FILE = "k8s-deployment.yaml"
        KUBE_NAMESPACE = "card-service-info"  // Update this if your app is in another namespace
    }

    tools {
        maven "maven"  // Ensure 'maven' is configured in Jenkins
    }

    stages {
        stage("Checkout Code") {
            steps {
                script {
                    checkout scmGit(
                        branches: [[name: "refs/heads/${BRANCH_NAME}"]],  // Corrected branch syntax
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

        stage("Deploy to Kubernetes") {
            steps {
                script {
                    // Ensure kubectl is using the correct context
                    sh "kubectl config current-context"

                    // Set the new image in the existing deployment
                    sh "kubectl set image deployment/${APP_NAME} ${APP_NAME}=${DOCKER_REPO}:${IMAGE_TAG} -n ${KUBE_NAMESPACE}"

                    // Apply any other changes in the YAML file
                    sh "kubectl apply -f ${KUBERNETES_FILE} -n ${KUBE_NAMESPACE}"
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
