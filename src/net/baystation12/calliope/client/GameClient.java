package net.baystation12.calliope.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.baystation12.calliope.network.Login;
import net.baystation12.calliope.network.NetworkMessage;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.artemis.Entity;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class GameClient extends Listener {
	public static GameClient client;
	public static Client connection;
	
	private static Map<Integer, Entity> entity_by_id;
	private static boolean running = false;
	private static boolean logged_in = false;

	private static int present_tick = 0;
	private static long last_server_tick = 0;
	
	public static void main(String[] args) throws LWJGLException, InterruptedException
	{
		client = new GameClient();

		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();

		GameClient.running = true;
		while(GameClient.running)
		{
			present_tick = (present_tick + 1) % 3600; //Resets every minute.
			if(Display.isCloseRequested())
			{
				GameClient.running = false;
			}

			Display.update();
			Thread.sleep(1000/60);
		}
		Display.destroy();
	}
	
	private GameClient()
	{
		this("127.0.0.1", 9000, 8000);
	}
	
	private GameClient(String server_address, int tcp_address, int udp_address)
	{
		connection = new Client();
		connection.addListener(this);
		connection.start();
		try {
			connection.connect(5000, server_address, tcp_address, udp_address);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Register the classes.
		NetworkMessage.registerClasses(connection.getKryo());
		
		//Set up the map of IDs.
		entity_by_id = new HashMap<Integer, Entity>();
	}
	
	@Override
	public void received(Connection connection, Object object)
	{
		//Handle login logic.
		if(!logged_in)
		{
			if(object instanceof Login)
			{
				logged_in = true;
			}
			return;
		}
		
		
	}
}
