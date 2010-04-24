package com.shorrockin.narrator

import java.util.concurrent.TimeUnit
import scala.Range.Inclusive

/**
 * defines some interval implicits for easily creating interval
 */
trait IntervalCreator {
  implicit def intToInterval(i:Int) = new Interval(i)
  implicit def inclusiveToInterval(i:Inclusive) = {
    val out = new Interval(i.start)
    out.end = Some(i.end)
    out
  }
}

/**
 * interval builder is constructed through the implicits until it
 * needs to be converted into an actual interval.
 */
class Interval(val start:Int) {
  var end:Option[Int] = None
  var unit:TimeUnit = TimeUnit.MILLISECONDS

  def minutes = asChained { unit = TimeUnit.MINUTES }
  def mins = asChained { unit = TimeUnit.MINUTES }
  def msecs = asChained { unit = TimeUnit.MILLISECONDS }
  def milliseconds = asChained { unit = TimeUnit.MILLISECONDS }

  private def asChained[E](f: => Unit) = { f ; this }
}