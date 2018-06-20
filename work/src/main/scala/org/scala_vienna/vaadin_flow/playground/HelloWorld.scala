package org.scala_vienna.vaadin_flow.playground

import com.vaadin.flow.component.html.Label
import com.vaadin.flow.router.Route

@Route("hello")
class HelloWorld extends Label("Welcome to Vaadin Flow!")
