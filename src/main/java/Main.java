import entity.Component;
import entity.ComponentType;
import entity.Train;
import log.Logging;
import models.ServiceProvider;
import gui.Gui;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;

/**
 * Created by Chung on 27-Nov-17.
 */
public class Main {
    public static void main(String [] args){
        Logging.createLoggingFile();
        Application.launch(Gui.class, args);

    }
}