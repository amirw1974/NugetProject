package presenter;

import view.MyView;
import view.View;

public class ExitCommand implements Command {

	private View v;
	
	public ExitCommand(View v) {
		this.v = v;
	}
	
	@Override
	public void doCommand(String[] args) {
		((MyView)v).displayMessage("Shutdown in progress...\nPlease wait... \n");

	}

}
