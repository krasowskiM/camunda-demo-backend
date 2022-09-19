package com.example.workflow.delegate

import com.example.workflow.controller.quote.QuoteRequest
import com.example.workflow.service.APPLICATION_PREVIOUSLY_REJECTED
import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("RejectionChecker")
class PastRejectionChecker : JavaDelegate {
    private val logger = LoggerFactory.getLogger(PastRejectionChecker::class.java)

    override fun execute(execution: DelegateExecution?) {
        logger.info("step ONE: checking past rejections")
        val quoteRequest = execution?.getVariable("quoteRequest") as QuoteRequest
        val rejected = quoteRequest.name == "rejected"
        execution.setVariable("rejected", rejected)
        logger.info("application rejected previously: $rejected")
        if (rejected) {
            logger.info("sending code 499 - application previously rejected")
            execution.setVariable("code", APPLICATION_PREVIOUSLY_REJECTED)
        }
    }
}
