package com.lib


import groovy.json.JsonSlurper


/**

*/
class Rules implements Serializable {

  private final context

  Rules(final context) {
    this.context = context
  }

  def helloWorld(){
    this.context.println "Hola Mundo"
  }  

}

