package presenter;

public class DirCommand implements Command {

	@Override
	public void doCommand(String[] args) {
		ClassLoader loader = DirCommand.class.getClassLoader();
        System.out.println(loader.getResource(""));

	}

}
