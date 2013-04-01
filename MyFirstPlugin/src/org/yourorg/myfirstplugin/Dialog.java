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
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
/**
 *
 * @author Administrator
 */

@ActionID(category = "File",
id = "org.gephi.desktop.filters.Dialog")
@ActionRegistration(displayName = "#CTL_Dialog")
@ActionReferences({
    @ActionReference(path = "Menu/Plugins", position = 3333)
})
@NbBundle.Messages("CTL_Dialog=Dialog...")



/**
 * Example of an action accessible from the "Plugins" menu un the menubar.
 * <p>
 * The annotations on the class defines the menu's name, position and class.
 * 
 * @author Mathieu Bastian
 */

public final class Dialog implements ActionListener {

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

    

