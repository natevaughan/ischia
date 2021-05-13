package io.ischia

class BadRequestException(message: String): Exception(message)
class NotFoundException(message: String): Exception(message)
class UnauthorizedException(message: String): Exception(message)