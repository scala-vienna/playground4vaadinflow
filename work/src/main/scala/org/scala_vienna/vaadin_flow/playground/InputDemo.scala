package org.scala_vienna.vaadin_flow.playground

import InputDemo._
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.{ Component, ComponentEvent, ComponentEventListener, DomEvent, Tag }
import com.vaadin.flow.router.Route
import com.vaadin.flow.shared.Registration

object InputDemo {

  @Tag("input")
  class TextField(value: String) extends Component {

    setValue(value)

    import com.vaadin.flow.component.Synchronize

    @Synchronize(Array("change"))
    def getValue: String = getElement.getProperty("value")

    def setValue(value: String): Unit = {
      getElement.setProperty("value", value)
      fireEvent(new ChangeEvent(this, false))
    }

    def addChangeListener(listener: ComponentEventListener[ChangeEvent]): Registration =
      addListener(classOf[ChangeEvent], listener)
  }

  @DomEvent("change")
  class ChangeEvent(source: TextField, fromClient: Boolean) extends ComponentEvent[TextField](source, fromClient)

}

@Route("input")
class InputDemo extends VerticalLayout {

  val label = new Label()
  val text = new TextField("initial text")
  text.addChangeListener(_ => display())
  val change = new Button("Change", _ => text.setValue("changed text"))
  val show = new Button("Show", _ => display())

  add(text, change, show, label)

  def display(): Unit = label.setText(text.getValue)

}
