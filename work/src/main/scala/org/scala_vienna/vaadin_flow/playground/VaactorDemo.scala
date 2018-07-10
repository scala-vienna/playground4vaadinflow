package org.scala_vienna.vaadin_flow.playground

import java.time.LocalTime

import VaactorDemo._
import org.vaadin.addons.vaactor.Vaactor
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.router.Route
import com.vaadin.flow.shared.communication.PushMode
import com.vaadin.flow.shared.ui.Transport
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.lumo.Lumo

import akka.actor.Actor.Receive
import akka.actor.{ ActorSystem, Cancellable }

import scala.concurrent.duration._

object VaactorDemo {

  case object Tick

  case object Clear

  val system = ActorSystem("vaactor-demo")

}

@Route("clock/vaactor")
@Theme(value = classOf[Lumo], variant = Lumo.DARK)
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
class VaactorDemo extends VerticalLayout with Vaactor.HasActor {

  var cancellable: Option[Cancellable] = None
  val label = new Label()

  add(
    new Button("Start", _ => {
      for (c <- cancellable) c.cancel()
      cancellable = Some(system.scheduler.schedule(0.seconds, 10.millis, self, Tick)(system.dispatcher))
    }),
    new Button("Stop", { _ =>
      for (c <- cancellable) c.cancel()
      cancellable = None
      self ! Clear
    }),
    label
  )

  override def receive: Receive = {
    case Tick => label.setText(LocalTime.now().toString)
    case Clear => label.setText("")
  }

}
