package launchPattern;

import Register.ProtocoleRegister;
import login.ProtocoleLogin;
import login.ReceptionLogin;
import servPattern.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(new UnContexte() , new ProtocoleRegister() , 6666 );

		myServ.start();

	}
}


