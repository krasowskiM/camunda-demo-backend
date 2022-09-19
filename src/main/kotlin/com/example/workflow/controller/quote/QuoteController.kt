package com.example.workflow.controller.quote

import com.example.workflow.service.QuoteService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quoteApi/v1")
class QuoteController(
    private val quoteService: QuoteService
) {

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun processQuote(@RequestBody quoteRequest: QuoteRequest): QuoteResponse =
        quoteService.processQuote(quoteRequest)
}