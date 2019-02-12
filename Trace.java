import UnOp.*;
import BinOp.*;

import java.util.Observable;
import java.util.Observer;


import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class Trace {

	
	static void trace(ConstExpr a,ConstExpr b, VarExpr var, Expression exp, LineChart<Number, Number> view) {

	        
		XYChart.Series<Number, Number> points = new XYChart.Series<>();
	        points.setName("" +exp.toString());

	        

		for (double x = a.getVal(); x < b.getVal(); x += (b.getVal() - a.getVal()) / 400) {
		    Number y = ((ConstExpr) exp.subst(var.toString(), new ConstExpr(x))).getVal();

		        points.getData().add(new XYChart.Data<Number, Number>(x, y));

		}
		view.setCreateSymbols(false);
		view.getData().add(points);
	}

}
