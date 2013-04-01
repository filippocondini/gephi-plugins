/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorg.myfirstplugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        
        category = "Edit",
        
        id = "org.yourorg.myfirstplugin.MiFirstPlugin")
       
@ActionRegistration(
        displayName = "#CTL_MiFirstPlugin")
@ActionReference(path = "Menu/Plugins", position = 3333)
@Messages("CTL_MiFirstPlugin=MiFirstPlugin")
public final class MiFirstPlugin implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        //Do something - for instance display a dialog
      NotifyDescriptor d = new NotifyDescriptor.Message("No active workspace", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notify(d);
        
      DialogDescriptor dd = new DialogDescriptor(new JPanel(), "My Dialog", false, null);
      DialogDisplayer.getDefault().notify(dd);
       
    }
    
  
}
/*
 import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;


@ActionID(category = "File",
id = "org.gephi.desktop.filters.TestAction")
@ActionRegistration(displayName = "#CTL_TestAction")
@ActionReferences({
    @ActionReference(path = "Menu/Plugins", position = 3333)
})
@Messages("CTL_TestAction=Testing...")
public final class TestAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //Do something, display a message
        NotifyDescriptor d = new NotifyDescriptor.Message("Hello...now trying to display a dialog", NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d);

        //Do something - for instance display a dialog
        //Dialogs API documentation: http://bits.netbeans.org/dev/javadoc/org-openide-dialogs/index.html?overview-summary.html
        DialogDescriptor dd = new DialogDescriptor(new JPanel(), "My Dialog", false, null);
        DialogDisplayer.getDefault().notify(dd);
    }
}
 
 */
 