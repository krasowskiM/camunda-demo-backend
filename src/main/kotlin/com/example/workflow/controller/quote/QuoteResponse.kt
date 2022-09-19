package com.example.workflow.controller.quote

import com.fasterxml.jackson.annotation.JsonIgnore
import org.camunda.bpm.engine.variable.type.ValueType
import org.camunda.bpm.engine.variable.value.TypedValue

data class QuoteResponse(
    var owner: Owner? = null,
    var car: Car? = null
): TypedValue {
    @JsonIgnore
    override fun getValue(): Any = this

    override fun getType(): ValueType = ValueType.OBJECT

    override fun isTransient(): Boolean = false
}

data class Owner(
    var name: String? = null,
    var surname: String? = null,
    var street: String? = null,
    var number: String? = null,
    var city: String? = null
): TypedValue {
    @JsonIgnore
    override fun getValue(): Any = this

    override fun getType(): ValueType = ValueType.OBJECT

    override fun isTransient(): Boolean = false
}

data class Car(
    var registrationNumber: String? = null,
    var engineType: String? = null,
    var enginePower: String? = null
): TypedValue {
    @JsonIgnore
    override fun getValue(): Any = this

    override fun getType(): ValueType = ValueType.OBJECT

    override fun isTransient(): Boolean = false
}