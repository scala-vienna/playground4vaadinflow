package org.scala_vienna.vaadin_flow.playground

import java.time.LocalTime

import ClockDemo._
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.component.{ AttachEvent, DetachEvent, UI }
import com.vaadin.flow.router.Route

import akka.actor.{ Actor, ActorRef, ActorSystem, Cancellable, PoisonPill, Props }

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object ClockDemo {

  case object Start

  case object Stop

  class Timer(ui: UI, label: Label) extends Actor {

    val waiting: Receive = {
      case Start =>
        implicit val executionContext: ExecutionContext = system.dispatcher
        val cancellable = system.scheduler.schedule(0.seconds, 10.millis) {
          ui.access(() => label.setText(LocalTime.now().toString))
        }
        context.become(running(cancellable))
    }

    def running(cancellable: Cancellable): Receive = {
      case Stop =>
        cancellable.cancel()
        ui.access(() => label.setText(""))
        context.become(waiting)
    }

    override def receive: Receive = waiting

  }

  val system = ActorSystem("clock-demo")

}

@Push
@Route("clock")
class ClockDemo extends VerticalLayout {

  var timer: Option[ActorRef] = None
  val label = new Label()

  add(
    new Button("Start", _ => send(Start)),
    new Button("Stop", _ => send(Stop)),
    label
  )

  def send(msg: Any): Unit = for (t <- timer) t ! msg

  override def onAttach(attachEvent: AttachEvent): Unit = {
    super.onAttach(attachEvent)
    timer = Some(system.actorOf(Props(new Timer(attachEvent.getUI, label))))
  }

  override def onDetach(detachEvent: DetachEvent): Unit = {
    send(Stop)
    send(PoisonPill)
    timer = None
    super.onDetach(detachEvent)
  }

}
