package org.scala_vienna.vaadin_flow.playground

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.{ Icon, VaadinIcon }
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

@Route("icon")
class IconDemo extends VerticalLayout {

  val vaadin: Icon = VaadinIcon.VAADIN_H.create
  val smiley: Icon = VaadinIcon.SMILEY_O.create
  smiley.setSize("64px")
  smiley.setColor("orange")

  val button = new Button("Smile!", vaadin)
  button.addClickListener(_ => button.setIcon(smiley))

  add(button)

}
