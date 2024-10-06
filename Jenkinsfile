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
          sh "docker build -t=omarpixelogic/docker-aws:latest ."

    }
      }


  stage("Push Image"){
      environment{
          DOCKER_HUB=credentials('dockerhub-creds')
      }
  steps{
        sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin '
        sh "docker push omarpixelogic/docker-aws:latest"

  }
    }
}

   post{
   always{
       sh "docker logout"
   }
   }


    }