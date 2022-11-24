def call(body) {
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()
  
  pipeline {
    agent any
    stages {
      stage('Echo from the other side!!!') {
        steps {
          echo "${config.message}"
          dir ('foo') {
            writeFile file:'foo.txt', text:'hello world'
            dir ('bar') {
              writeFile file:'bar.txt', text:'goodbye world'
            }
          }
        }
      }
    }
  }
}
