//package boot;
//
//import model.MyModel;
//import presenter.Presenter;
//import view.MazeWindow;
//
//public class Run {
//
//	public static void main(String[] args) {
//		
//		MyModel model = new MyModel();
//		
//		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		//PrintWriter out = new PrintWriter(System.out);
//		//MyView view = new MyView(in, out);
//		MazeWindow view = new MazeWindow();
//		
//		Presenter presenter = new Presenter(model, view);
//		model.addObserver(presenter);
//		view.addObserver(presenter);
//		view.start();		
//	}
//}
//
//
//
package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Model;
import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;
import view.MyView;
import view.View;


public class Run {

	public static void main(String[] args) {
		MazeWindow view = new MazeWindow();
		view.run();
	}

}


