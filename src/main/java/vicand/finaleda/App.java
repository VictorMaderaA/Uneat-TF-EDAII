package vicand.finaleda;

import java.io.File;

import org.apache.log4j.BasicConfigurator;

import vicand.finaleda.Views.MainWindow;
import vicand.finaleda.controllers.AppController;

public class App {
	public static void main(String[] args) {
		BasicConfigurator.configure();

		AppController.TryToLoadSearchData();
		
		MainWindow window = new MainWindow();
	}
}
