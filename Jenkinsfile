pipeline {

    agent any

    environment {
        JAVA_HOME = 'C:\\Users\\d.mane\\.jdks\\ms-21.0.10'
        PATH = "${JAVA_HOME}\\bin;${PATH}"
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvnw.cmd test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvnw.cmd package -DskipTests'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'demo-service build completed successfully'
        }

        failure {
            echo 'demo-service build failed'
        }
    }
}