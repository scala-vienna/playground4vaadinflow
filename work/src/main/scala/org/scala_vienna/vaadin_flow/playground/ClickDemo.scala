package org.scala_vienna.vaadin_flow.playground

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("click")
class ClickDemo extends VerticalLayout {

  val label = new Label
  val button = new Button("Click me", _ => label.setText("Clicked!"))

  add(button, label)

}
