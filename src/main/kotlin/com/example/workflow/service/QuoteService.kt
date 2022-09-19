package com.example.workflow.service

import com.example.workflow.controller.quote.QuoteRequest
import com.example.workflow.controller.quote.QuoteResponse
import com.example.workflow.exception.ApplicationPreviouslyRejected
import com.example.workflow.exception.QuoteScoreTooLow
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats
import org.camunda.bpm.engine.variable.value.ObjectValue
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

const val CREDIT_SCORE_TOO_LOW = 470
const val APPLICATION_PREVIOUSLY_REJECTED = 499
const val PROCESS_SUCCESSFUL = 201

@Service
class QuoteService(
    private val runtimeService: RuntimeService
) {
    private val logger = LoggerFactory.getLogger(QuoteService::class.java)

    fun processQuote(quoteRequest: QuoteRequest): QuoteResponse {
        logger.info("starting process instance")
        val processInstanceWithVariables = runtimeService.createProcessInstanceByKey("calculate_insurance_proposition")
            .setVariables(mapOf("quoteRequest" to quoteRequest.serializedValue())).executeWithVariablesInReturn()

        val processVariables = processInstanceWithVariables.variables
        return when (processVariables["code"]) {
            CREDIT_SCORE_TOO_LOW -> throw QuoteScoreTooLow
            APPLICATION_PREVIOUSLY_REJECTED -> throw ApplicationPreviouslyRejected
            PROCESS_SUCCESSFUL -> processVariables.getValue("quoteResponse", QuoteResponse::class.java)
            else -> throw RuntimeException("unknown status code")
        }
    }
}

fun Any.serializedValue(): ObjectValue? =
    Variables.objectValue(this).serializationDataFormat(SerializationDataFormats.JAVA).create()