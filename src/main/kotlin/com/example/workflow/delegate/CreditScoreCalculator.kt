package com.example.workflow.delegate

import com.example.workflow.controller.quote.QuoteRequest
import com.example.workflow.service.CREDIT_SCORE_TOO_LOW
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

private const val CREDIT_SCORE_THRESHOLD = 1000

@Component("QuotationScoreCalculator")
class CreditScoreCalculator : JavaDelegate {
    private val logger = LoggerFactory.getLogger(CreditScoreCalculator::class.java)

    override fun execute(execution: DelegateExecution?) {
        logger.info("step TWO: check credit score")
        val quoteRequest = execution?.getVariable("quoteRequest") as QuoteRequest
        val creditScore = if (quoteRequest.surname == "quote") {
            2000
        } else {
            500
        }
        execution.setVariable("quotationScore", creditScore)
        logger.info("credit score is $creditScore")
        if (creditScore < CREDIT_SCORE_THRESHOLD) {
            execution.setVariable("code", CREDIT_SCORE_TOO_LOW)
            logger.info("sending code 470 - quotation score too low")
        }
    }
}