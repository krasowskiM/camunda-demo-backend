package com.example.workflow.delegate

import com.example.workflow.controller.quote.Car
import com.example.workflow.controller.quote.Owner
import com.example.workflow.controller.quote.QuoteRequest
import com.example.workflow.controller.quote.QuoteResponse
import com.example.workflow.service.serializedValue
import com.fasterxml.jackson.databind.ObjectMapper
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.bpm.engine.variable.Variables
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("AdditionalInformationEnricher")
class AdditionalInformationEnricher : JavaDelegate {
    private val logger = LoggerFactory.getLogger(AdditionalInformationEnricher::class.java)

    override fun execute(execution: DelegateExecution?) {
        logger.info("step THREE: enrich model")
        execution?.setVariable(
            "quoteResponse",
            (execution.getVariable("quoteRequest") as QuoteRequest)
                .let {
                    logger.info("providing additional information on customer - street: test1, number: test2, city: test3")
                    logger.info("providing additional information on car - engine power: 120hp")
                    QuoteResponse(
                        Owner(it.name, it.surname, "test1", "test2", "test3"),
                        Car(it.registrationNumber, it.engineType, "120hp")
                    ).serializedValue()
                }
        )
    }
}
