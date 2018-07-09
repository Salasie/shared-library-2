package com.git


import groovy.json.JsonSlurper


/**

*/
class GitlabApi implements Serializable {

  private final context

  private final APPLICATION_JSON = "APPLICATION_JSON"
  private final TEXT_PLAIN ="TEXT_PLAIN"

  private final PRIVATE_TOKEN = "PRIVATE-TOKEN"
  private final POST = "POST"
  private final VALID_RESPONSE = "100:499"

  private final STATUS_SUCCESS = "success"
  private final STATUS_RUNNING = "running"
  private final STATUS_FAILED = "failed"
  private final STATUS_CANCELED = "canceled"

  GitlabApi(final context) {
    this.context = context
  }

  def getRepoIdByName(host, token, name){
    def apiRepoId = context.httpRequest validResponseCodes: "$VALID_RESPONSE", contentType: "$APPLICATION_JSON", customHeaders: [[ name: "$PRIVATE_TOKEN", value: token ]], url: "${host}/api/v4/projects/${name}"
    def objectJson = getJsonFromText(apiRepoId.content)
    return objectJson.id    
  }

  def getLastCommit(host, token, repoId, branch){
    def urlApi = context.httpRequest validResponseCodes: "$VALID_RESPONSE", contentType: "$APPLICATION_JSON", customHeaders: [[ name: "$PRIVATE_TOKEN", value: token ]], url: "${host}/api/v4/projects/${repoId}/repository/commits/?ref_name=${branch}&per_page=1"
    def objectJson = getJsonFromText(urlApi.content)
    return objectJson[0].id
  }

  def getFileContent(host, token, repoId, branch, file){
    def urlApi = context.httpRequest validResponseCodes: "$VALID_RESPONSE", contentType: "$TEXT_PLAIN", customHeaders: [[ name: "$PRIVATE_TOKEN", value: token ]], url: "${repoId}/api/v4/projects/${repoId}/repository/files/${repoId}?ref=${branch}"
    def objectJson = getJsonFromText(urlApi.content)
    return objectJson[0].id
  }

  def helloWorld(){
    this.context.println "Hola Mundo"
  }  

}

