pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                println 'Iniciando clone do projeto ...'
                git branch: 'jenkins', url: 'https://github.com/Arach-Corp/SmartKitchen-Backend.git'
            }

            post {
                success {
                    println 'Projeto clonado com sucesso!'
                }
            }
        }
        
        stage('Build') {
            
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                success {
                    println 'Project Build Successful!'
                }
            }
        }
        
        stage('Permissao de Deploy') {
            steps {
                script {
                    prosseguirDeploy = input(
                        id: 'userInput', message: 'Prosseguir com o deploy (Y/N)?', parameters: [
                            [$class: 'TextParameterDefinition', defaultValue: 'Y', description: 'Realizar Deploy?', name: 'Executar', trim:true] 
                        ]
                    )
                    if (prosseguirDeploy == 'Y') {
                        echo 'Deploy Aprovado'
                    } else {
                        echo 'Deploy Abortado'
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo("Iniciando deploy ...")
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8080/')], contextPath: 'smartkitchen', onFailure: false, war: 'target/*.war'
            }

            post {
                
                success {
                    println 'Deploy concluído com sucesso!'
                }
            }
        }
    }
}
