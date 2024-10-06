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
        sh "docker tag omarpixelogic/docker-aws:latest omarpixelogic/docker-aws:${env.BUILD_NUMBER}"
        sh "docker push omarpixelogic/docker-aws:${env.BUILD_NUMBER}"

  }
    }
}

   post{
   always{
       sh "docker logout"

   }
   }


    }