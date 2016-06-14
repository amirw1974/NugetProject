//package boot;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//
//import model.MyModel;
//import presenter.Presenter;
//import view.MyView;
//
//public class Run {
//
//	public static void main(String[] args) {
//		
//		Settings set = new Settings();
//		set.load();
//		MyModel model = new MyModel(set);
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
//		MyView view = new MyView(in, out);
////		MazeWindow view = new MazeWindow();
//		
//		Presenter presenter = new Presenter(model, view);
//		model.addObserver(presenter);
//		view.addObserver(presenter);
//		view.start();		
//	}
//}


//
package boot;




import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;


public class Run {

	public static void main(String[] args) {
		Settings s = new Settings();
		MazeWindow view = new MazeWindow();
		MyModel model = new MyModel(s);
		Presenter presenter = new Presenter(model, view);
		
		view.addObserver(presenter);
		model.addObserver(presenter);
		
		view.run();
	}

}


