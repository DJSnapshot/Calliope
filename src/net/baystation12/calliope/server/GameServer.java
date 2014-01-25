package net.baystation12.calliope.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.baystation12.calliope.network.Login;
import net.baystation12.calliope.network.NetworkMessage;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class GameServer extends Listener {
	public static GameServer server;
	public static Server connection;
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException
	{
		server = new GameServer();
		
		while(true)
		{
			Thread.sleep(50000);
		}
	}
	
	public GameServer()
	{
		this(9000, 8000);
	}
	
	/**
	 * Starts server process and begins listening on the passed ports
	 */
	public GameServer(int tcp_port, int udp_port)
	{
		connection = new Server();
		connection.addListener(this);
		connection.start();
		try {
			connection.bind(9000, 8000);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		NetworkMessage.registerClasses(connection.getKryo());
	}
	
	@Override
	public void received (Connection connection, Object object) 
	{
		if(object instanceof Login)
		{
			
		}
	}
}
