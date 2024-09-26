pipeline{
    agent {
    label 'docker'
    }

    stages{
    stage("Building JAR"){
    steps{
          sh "mvn clean package -DskipTests=true"

    }
      }


    stage("Building Image"){
    steps{
          sh "docker build -t=omarpixelogic/docker-aws ."

    }
      }


  stage("Push Image"){
  steps{
        sh "docker push -t=omarpixelogic/docker-aws"

  }
    }
}

   post{
   always{

archiveArtifacts artifacts: 'index.html', followSymlinks: false
   }
   }


    }