package com.BookHub_project_tushar.bookhub

class user {
    var email: String? = null
    var pass: String? = null
    var id: String? = null

    constructor() {}
    constructor(id: String?, email: String?, pass: String?) {
        this.email = email
        this.pass = pass
        this.id = id
    }
}