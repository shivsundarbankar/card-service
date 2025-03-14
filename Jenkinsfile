pipeline {
    agent any

    environment {
        APP_NAME = "card-service"
        DOCKER_REPO = "shivsundar21/${APP_NAME}"
        IMAGE_TAG = "1.0.4"
        BRANCH_NAME = "build-to-jenkins"
        KUBERNETES_FILE = "k8s-deployment.yaml"
        KUBE_NAMESPACE = "card-service-info"
    }

    tools {
        maven "maven"
    }

    stages {
        stage("Checkout Code") {
            steps {
                script {
                    checkout scmGit(
                        branches: [[name: "refs/heads/${BRANCH_NAME}"]],
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
                    sh "docker build -t ${DOCKER_REPO}:${IMAGE_TAG} ."
                }
            }
        }

        stage("Push to Docker Hub") {
            steps {
                withDockerRegistry(credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/') {
                    sh "docker push ${DOCKER_REPO}:${IMAGE_TAG}"
                }
            }
        }

        stage("Deploy to Kubernetes") {
            steps {
                script {
                    // Correctly set up Minikube Docker environment
                    if (isUnix()) {
                        sh "eval \$(minikube -p minikube docker-env)"
                    } else {
                        bat "powershell.exe -Command \"& { minikube -p minikube docker-env --shell powershell }\""
                    }

                    // Deploy application to Kubernetes
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
