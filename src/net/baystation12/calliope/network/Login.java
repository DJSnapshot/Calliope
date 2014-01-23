package net.baystation12.calliope.network;

import java.math.BigInteger;

import net.baystation12.calliope.network.NetworkMessage;

public class Login extends NetworkMessage{
	public static enum ResponseMode {Request, Response};

	public String name;
	public BigInteger buffer;
	public ResponseMode mode;
}
