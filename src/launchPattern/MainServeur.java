package launchPattern;

import servPattern.ServeurTCP;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(new UnContexte() , new MonGrosProtocole() , 6666 );
		//a remplacer définitivement par MonGrosProtocole()

		myServ.start();

	}
}