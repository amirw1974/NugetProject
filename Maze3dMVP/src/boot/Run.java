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
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
        
		Model model = new MyModel();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		View view = new MyView(in, out);
		view.start();
		Presenter presenter = new Presenter(model, view);
		
		
		

	}

}

