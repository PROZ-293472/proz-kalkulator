package application.model;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.List;

public class JshellCalc {
    public String output = "0";

    public void compute(String input) {

        JShell jshell = JShell.create();
        try (jshell){
            List<SnippetEvent> events = jshell.eval(input);
            for (SnippetEvent e: events) {
                if(e.causeSnippet() == null) {
                    switch (e.status()) {
                        case VALID:
                            if (e.value() != null) {
                                output = e.value();
                            }
                            break;
                        default :
                            output = "Error";
                    }
                }
            }
        }
    }

}
