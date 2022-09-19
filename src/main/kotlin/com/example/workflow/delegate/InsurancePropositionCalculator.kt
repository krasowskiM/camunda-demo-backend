package com.example.workflow.delegate

import com.example.workflow.service.PROCESS_SUCCESSFUL
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component("InsurancePropositionCalculator")
class InsurancePropositionCalculator : JavaDelegate {
    private val logger = LoggerFactory.getLogger(InsurancePropositionCalculator::class.java)


    override fun execute(execution: DelegateExecution?) {
        logger.info("calculating insurance proposition")
        execution?.setVariable("code", PROCESS_SUCCESSFUL)
    }
}