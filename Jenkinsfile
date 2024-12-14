pipeline {
    agent any
    
    stages {
       stage('Checkout') {
          steps {
                git clone 'https://github.com/fancypandaa/ibn-firnas'
	   }
	}

       stage('Build') {
          steps {
              sh 'mvn clean install'
	   }
	}
      
       stage('Test') {
          steps {
              sh 'mvn test'
	   }
	}
	 
       stage('Package') {
          steps {
               sh 'mvn pacakge'
	   }
	}
     }
     
     post {
        success {
            echo 'Build and Deploy succeded!'
	}
        failure {
           echo 'Build or Deploy failed!'
	}
    }
}
