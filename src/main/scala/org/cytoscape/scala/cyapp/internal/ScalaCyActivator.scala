package org.cytoscape.scala.cyapp.internal

import org.cytoscape.service.util.AbstractCyActivator
import org.osgi.framework.BundleContext

/**
 * Convenience methods to provide a more Scala-like API on top of AbstractCyActivator
 */
abstract class ScalaCyActivator extends AbstractCyActivator {
  
  override def start(bc: BundleContext) = startup(bc)
  
  def startup(implicit bc: BundleContext)
  
  def service[T](implicit bc: BundleContext, m: scala.reflect.Manifest[T]) = getService(bc, m.runtimeClass).asInstanceOf[T]
  
  def service[T](filter: String)(implicit bc: BundleContext, m: scala.reflect.Manifest[T]) = getService(bc, m.runtimeClass, filter).asInstanceOf[T]
  
}