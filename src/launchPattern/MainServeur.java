package launchPattern;

import login.ReceptionLogin;
import servPattern.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		//ServeurTCP myServ = new ServeurTCP(new UnContexte() , new ProtocolePingPong() , 6666 );
		//myServ.start();

		ReceptionLogin myServ = new ReceptionLogin( 6666);
		myServ.run();
	}
}


