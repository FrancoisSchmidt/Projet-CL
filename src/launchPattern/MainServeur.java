package launchPattern;

import login.ProtocoleLogin;
import login.ReceptionLogin;
import servPattern.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(new UnContexte() , new ProtocoleLogin() , 6666 );

		myServ.start();

	}
}


