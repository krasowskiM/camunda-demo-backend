package com.example.workflow.config

import org.camunda.bpm.engine.impl.history.HistoryLevel
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource


@Configuration
class CamundaConfiguration {

    @Bean
    fun engineConfiguration(
        dataSource: DataSource?,
        transactionManager: PlatformTransactionManager?,
        @Value("classpath*:*.bpmn") deploymentResources: Array<Resource?>?
    ): SpringProcessEngineConfiguration? = SpringProcessEngineConfiguration().apply {
        processEngineName = "engine"
        this.dataSource = dataSource
        this.transactionManager = transactionManager
        databaseSchemaUpdate = "true"
//        isJobExecutorActivate = true
        this.deploymentResources = deploymentResources
        historyLevel = HistoryLevel.HISTORY_LEVEL_FULL
    }
}