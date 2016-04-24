package org.cytoscape.scala.cyapp.internal

import org.cytoscape.application.swing.AbstractCyAction
import java.awt.event.ActionEvent
import javax.swing.JOptionPane
import org.cytoscape.application.CyApplicationManager
import org.cytoscape.application.swing.CySwingApplication
import com.escalatesoft.subcut.inject.BindingModule
import com.escalatesoft.subcut.inject.Injectable
import javax.swing.JFrame

class MenuAction(implicit val bindingModule: BindingModule) extends AbstractCyAction("Scala Action") with Injectable {
  
  setPreferredMenu("Apps")
  
  override def actionPerformed(e: ActionEvent) = {
    val jframe = inject [JFrame]
    
    val words = List("Hello", "Cytoscape", "Scala", "World!")
    JOptionPane.showMessageDialog(jframe, words.mkString(" "))
  }
  
  
}