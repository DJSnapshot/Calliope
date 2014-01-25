package net.baystation12.calliope.components;

import com.artemis.Component;

public class Sprite extends Component {
	public int sprite_id;
	public byte icon_state;

	@Override
	public void reset()
	{
		sprite_id = -1;
		icon_state = -1;
	}

	@Override
	public Component copy()
	{
		Sprite sprite = new Sprite();
		sprite.sprite_id = this.sprite_id;
		sprite.icon_state = this.icon_state;
		return sprite;
	}
}
