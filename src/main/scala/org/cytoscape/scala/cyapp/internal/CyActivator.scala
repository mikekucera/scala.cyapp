package org.cytoscape.scala.cyapp.internal

import java.util.Properties

import org.cytoscape.application.CyApplicationManager
import org.cytoscape.application.swing.CySwingApplication
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory
import org.osgi.framework.BundleContext

import com.escalatesoft.subcut.inject.NewBindingModule.newBindingModule
import com.escalatesoft.subcut.inject.bindingIdToString


class CyActivator extends ScalaCyActivator {
  
  override def startup(implicit bc: BundleContext) = {
    
    // Configure subcut dependency injection
    implicit val bindingModule = newBindingModule(module => {
      import module._
      
      // Standard service lookup
      bind [CyApplicationManager] toProvider service[CyApplicationManager]
      bind [CySwingApplication]   toProvider service[CySwingApplication]
      
      // Using OSGi filters
      bind [VisualMappingFunctionFactory] idBy "continuous"  toProvider service[VisualMappingFunctionFactory]("(mapping.type=continuous)")
      bind [VisualMappingFunctionFactory] idBy "discrete"    toProvider service[VisualMappingFunctionFactory]("(mapping.type=discrete)")
      bind [VisualMappingFunctionFactory] idBy "passthrough" toProvider service[VisualMappingFunctionFactory]("(mapping.type=passthrough)")
    });
    
    
    // Create services
    val action = new MenuAction
    registerAllServices(bc, action, new Properties)
  }
  
}