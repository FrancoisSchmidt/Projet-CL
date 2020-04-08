package launchPattern;

import servPattern.ServeurTCP;
import context.LeContext;

public class MainServeur {

	public static void main(String[] args) {
		ServeurTCP myServ = new ServeurTCP(new LeContext() , new MonGrosProtocole() , 6666 );
		//a remplacer définitivement par MonGrosProtocole()

		myServ.start();

	}
}