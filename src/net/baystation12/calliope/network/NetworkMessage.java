package net.baystation12.calliope.network;

import com.esotericsoftware.kryo.Kryo;

public class NetworkMessage {

	public static void registerClasses(Kryo kryo)
	{
		kryo.register(Login.class);
		//kryo.register(StringMessage.class);
	}
}
