pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'Build starts here'
                sh 'mvn --version'
                sh 'mvn clean install'
                echo 'Build ends here'
            }
        }
    }
}