#!/usr/bin/env groovy
import com.cloudbees.groovy.cps.NonCPS
import groovy.transform.Field

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;
import java.util.regex.Pattern
import static groovy.io.FileType.FILES

// This will work only with multibranch pipelines.

@Field def buildTimestampSlack, commitId, appVersion, projectName = 'product-services-autotests', simpleBranchName

def branchName = env.BRANCH_NAME

properties([
        buildDiscarder([
                $class               : 'EnhancedOldBuildDiscarder',
                artifactDaysToKeepStr: '7',
                artifactNumToKeepStr : '10',
                daysToKeepStr        : '7',
                discardOnlyOnSuccess : false,
                numToKeepStr         : '20',
        ]),
        disableConcurrentBuilds(),
        [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
        parameters([
                choice(
                        choices: ['dev', 'stg'],
                        description: '''
            Description
      ''',
                        name: 'environment'),
                choice(
                        choices: ['smoke','regression'],
                        description: '''
        Tags description
      ''',),
                choice(
                       choices: ['local','grid'],
                        description: '',
                        name: 'testbed'),

                booleanParam(
                        name: 'NO_GUI',
                        defaultValue: true,
                        description: 'Run tests without GUI'
                ),
        ])
])

node {
    currentBuild.displayName = "#" + (currentBuild.number + "-${params.environment}" + "-${params.groups}")

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// ----- Stage: Build && Run tests
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    try {
        stage('Build && Run tests') {
            cleanWs()
            checkout scm
            bat "mvn clean"
            bat "mvn test -Denv=${params.environment} -Dgroups=${params.groups} -Dno.gui=${params.NO_GUI}, -Dtestbed=${params.testbed}"

        }
    } catch(err){
        throw err
    }
    finally {
        stage('Reports') {
            bat "mvn allure:report"
            publishHTML(
                    target: [
                            reportName           : "Allure Report",
                            reportDir            : "${WORKSPACE}/target/site/allure-maven-plugin",
                            reportFiles          : "index.html",
                            keepAll              : true,
                            alwaysLinkToLastBuild: true,
                            allowMissing         : false
                    ]
            )
        }
    }
}