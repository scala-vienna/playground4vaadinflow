package org.scala_vienna.vaadin_flow.playground

import GridDemo._
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route

import scala.collection.JavaConverters._

object GridDemo {

  case class Person(name: String, yearOfBirth: Int)

  val people = Seq(
    Person("Nicolaus Copernicus", 1543),
    Person("Galileo Galilei", 1564),
    Person("Johannes Kepler", 1571)
  )

}

@Route("grid")
class GridDemo extends VerticalLayout {

  val grid = new Grid[Person]()
  grid.setItems(people.asJava)
  grid.addColumn(_.name).setHeader("Name")
  grid.addColumn(_.yearOfBirth.toString).setHeader("Year of birth")

  add(grid)

}
