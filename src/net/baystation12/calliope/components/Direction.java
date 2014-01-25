package net.baystation12.calliope.components;

import com.artemis.Component;

public class Direction extends Component { 
	public static final byte NORTH = 2 << 0; //1
	public static final byte SOUTH = 2 << 1; //2
	public static final byte EAST = 2 << 2; //4
	public static final byte WEST = 2 << 3; //8
	public static final byte UP = 2 << 4; //16
	public static final byte DOWN = 2 << 5; //32
	public static final byte ALL_DIRS = (2 << 6) - 1; //63, or NORTH|SOUTH|EAST|WEST|UP|DOWN

	public static final byte[] cardinal = {NORTH, SOUTH, EAST, WEST};
	public static final byte[] cardinal_3d = {NORTH, SOUTH, EAST, WEST, UP, DOWN};
	public static final byte[] moore_neighborhood = {NORTH, SOUTH, EAST, NORTH|EAST, SOUTH|EAST, WEST, NORTH|WEST, SOUTH|WEST};

	public static final byte[][] turn_array = {
		//The first value is the direction being rotated towards.
		{NORTH, SOUTH, EAST, WEST, UP, DOWN},
		{SOUTH, NORTH, WEST, EAST, UP, DOWN},
		{EAST, WEST, SOUTH, NORTH, UP, DOWN},
		{WEST, EAST, NORTH, SOUTH, UP, DOWN},
		{UP, DOWN, EAST, WEST, SOUTH, NORTH},
		{DOWN, UP, EAST, WEST, NORTH, SOUTH}
	};
	
	public byte direction = NORTH;
	
	public void Reverse()
	{
		Rotate(SOUTH);
	}
	
	public void Rotate(byte offset)
	{
		//Neither of these cases need any work done.
		if(offset == NORTH || direction == ALL_DIRS || direction == 0)
		{
			return;
		}
		for(byte index = 0; index < turn_array.length; index++)
		{
			if((offset & cardinal[index]) != 0)
			{
				byte last_turn = direction;
				direction = 0;
				for(byte turn_index = 0; turn_index < turn_array[index].length; turn_index++)
				{
					if((last_turn & cardinal[turn_index]) != 0)
					{
						direction |= turn_array[index][turn_index];
					}
				}
			}
		}
	}

	@Override
	public void reset()
	{
		direction = NORTH;
	}

	@Override
	public Component copy()
	{
		Direction direction = new Direction();
		direction.direction = NORTH;
		return direction;
	}
}