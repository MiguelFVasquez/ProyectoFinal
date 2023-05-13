package application;

import java.io.IOException;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import Exceptions.AdministradorException;
import Exceptions.ClienteException;
import Exceptions.EmpleadoException;
import Model.Administrador;
import Model.Cliente;
import Model.Concesionario;
import Model.Empleado;
import controllers.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Aplicacion extends Application{

	//Solo puede existir una instancia de la clase principal, en este caso tenemos una sola intancia
	//de la clase concesionario
	private Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage= primaryStage;
		primaryStage.setTitle("Concesionario");
		mostrarVentanaPrincipal();

	}

	private void mostrarVentanaPrincipal() throws IOException {
		//Se establece la ruta de la ventana que desea ejecutar
		try{
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("../Views/PrincipalView.fxml"));
			AnchorPane anchorPane= (AnchorPane)loader.load();
			PrincipalController principalController = loader.getController();
			principalController.setAplicacion(this);

			Scene scene= new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
			PrincipalController controller= loader.getController();
			controller.setStage(primaryStage);
		}catch(IOException e){

			}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
