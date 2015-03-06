package com.assistanz.app

class Config {
    String name
    String value
    String description
    static constraints = {
        description(nullable: true)
    }
}
