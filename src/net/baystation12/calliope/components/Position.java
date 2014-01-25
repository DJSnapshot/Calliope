package net.baystation12.calliope.components;

import com.artemis.Component;

public class Position extends Component {
	public int location;
	
	@Override
	public void reset()
	{
		location = -1;
	}

	@Override
	public Component copy()
	{
		Position position = new Position();
		position.location = this.location;
		return position;
	}
}
