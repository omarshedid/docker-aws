pipeline{
    agent {
    docker{
    image "alpine"
    }}
    environment{
    NUMBER=4
    }


   post{
   always{

archiveArtifacts artifacts: 'index.html', followSymlinks: false
   }
   }


    }