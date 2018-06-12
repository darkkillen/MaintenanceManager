package com.darkkillen.maintenancemanager

class MaintenanceException : Exception {

    constructor() {}

    constructor(message: String, throwable: Throwable) : super(message, throwable) {}

    constructor(message: String) : super(message) {}
}