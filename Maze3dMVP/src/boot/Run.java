package boot;

import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;

public class Run {

	public static void main(String[] args) {
		
		MyModel model = new MyModel();
		
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		//MyView view = new MyView(in, out);
		MazeWindow view = new MazeWindow();
		
		Presenter presenter = new Presenter(model, view);
		model.addObserver(presenter);
		view.addObserver(presenter);
		view.start();		
	}
}
