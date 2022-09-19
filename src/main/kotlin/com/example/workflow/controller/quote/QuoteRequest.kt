package com.example.workflow.controller.quote

import org.camunda.bpm.engine.variable.type.ValueType
import org.camunda.bpm.engine.variable.value.TypedValue

data class QuoteRequest(
    var name: String? = null,
    var surname: String? = null,
    var registrationNumber: String? = null,
    var engineType: String? = null
): TypedValue {
    override fun getValue(): Any = this

    override fun getType(): ValueType = ValueType.OBJECT

    override fun isTransient(): Boolean = false
}