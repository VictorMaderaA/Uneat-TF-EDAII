package vicand.finaleda;

import java.io.File;

import org.apache.log4j.BasicConfigurator;

import vicand.finaleda.controllers.AppController;

/**
 * Hello world!
 *
 */
public class App 
{

	static AppController appController = new AppController();

	public static void main( String[] args )
	{
		File file = new File("ProyectTestFolder");
		System.out.println(file.getAbsolutePath());

		BasicConfigurator.configure();

		System.out.println( "Hello World!" );
		MainWindow window = new MainWindow(appController);

	}
}
