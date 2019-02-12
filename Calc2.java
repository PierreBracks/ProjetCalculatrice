import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javafx.scene.chart.*;


public class Calc2 extends Application{

    public static boolean isValidFloat(String t){
	try{
	    Double.parseDouble(t);
	} catch(NumberFormatException nfe){
	    return false;
	}
	return true;
    }

    
    
    
    @Override
    public void start(Stage stage) throws Exception{
	
	LinkedList<Expression> stock = new LinkedList<Expression>();
	LinkedList<Expression> historic= new LinkedList<Expression>();

	MenuItem aide=new MenuItem("aide");
	MenuItem exit = new MenuItem("Exit");
	exit.setOnAction(e-> System.exit(0));
     
	Menu file = new Menu("File");
	Menu aides = new Menu("aide");
	file.getItems().add(exit);
	aides.getItems().add(aide);
	
	MenuBar menu = new MenuBar();
	menu.getMenus().add(file);
	menu.getMenus().add(aides);
	
	BorderPane root = new BorderPane();
	root.setTop(menu);
	//Text text=new Text("Exemple de texte");

      
	NumberAxis xAxis = new NumberAxis();
	NumberAxis yAxis = new NumberAxis();
	LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);

	root.setRight(lineChart);
      
	TextArea text =new TextArea("pour obtenir les commandes cliquer sur aide\n"+
				    "<");
	text.setEditable(false);
	// text.setPrefWidth(150);
	root.setCenter(text);
	/*
	 *MenuBar menu2=new MenuBar();
	 *root.setBottom(menu2);
	 *menu2.getMenus().add(file);
	 */
	
	TextField textf=new TextField();
	root.setBottom(textf);

      
	textf.setOnAction(e->{
		String t=textf.getText();
	  
		text.appendText(t+"\n");

		if (isValidFloat(t)){
		    Expression test =new ConstExpr(Double.parseDouble(t));
		    stock.push(test);
		    historic.push(test);
		}
		else if (t.equals("+")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			if(stock.peek()!=null){
			    Expression x2=stock.pop();
			    Expression rep=PlusExpr.Of(x1,x2);
			    // if (rep instanceof ConstExpr)
			    //  rep=rep.getVal();
			    stock.push(rep);
			    historic.push(rep);
			    
			}
			else{
			    stock.push(x1);
			    text.appendText("erreur: il n'y a qu'une valeur dans la pile\n");
			}
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
		else if (t.equals("-")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			if(stock.peek()!=null){
			    Expression x2=stock.pop();
			    Expression rep=MoinsExpr.Of(x2,x1);
			    // if (rep instanceof ConstExpr)
			    //  rep=rep.getVal();
			    stock.push(rep);
			    historic.push(rep);
			}
			else{
			    stock.push(x1);
			    text.appendText("erreur: il n'y a qu'une valeur dans la pile\n");
			}
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
		else if (t.equals("*")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			if(stock.peek()!=null){
			    Expression x2=stock.pop();
			    Expression rep=MultiExpr.Of(x1,x2);
			    // if (rep instanceof ConstExpr)
			    //  rep=rep.getVal();
			    stock.push(rep);
			    historic.push(rep);
			}
			else{
			    stock.push(x1);
			    text.appendText("erreur: il n'y a qu'une valeur dans la pile\n");
			}
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
	      
		else if (t.equals("/")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			if(stock.peek()!=null){
			    Expression x2=stock.pop();
			    Expression rep=DivExpr.Of(x2,x1);
			    // if (rep instanceof ConstExpr)
			    //  rep=rep.getVal();
			    stock.push(rep);
			    historic.push(rep);
			}
			else{
			    stock.push(x1);
			    text.appendText("erreur: il n'y a qu'une valeur dans la pile\n");
			}
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
		else if (t.equals("opp")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			Expression rep=OppExpr.Of(x1);
			// if (rep instanceof ConstExpr)
			//  rep=rep.getVal();
			stock.push(rep);
			historic.push(rep);
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
		else if (t.equals("inv")){
		    if(stock.peek()!=null){
			Expression x1=stock.pop();
			Expression rep=InvExpr.Of(x1);
			stock.push(rep);
			historic.push(rep);
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
		    
		else if (t.equals("=")){
		    if(stock.peek()!=null){
			Expression val=stock.pop();
			if(stock.peek()!=null){
			    Expression x=stock.pop();
			    if(stock.peek()!=null){
				Expression expr=stock.pop();
				stock.push(expr.subst(x.toString(),val));
			    }
			}
			else{
			    text.appendText("erreur:voir aide pour l'utilisation de =\n");
			}
		    }
		    else{
			text.appendText("erreur: il n'y a aucune valeur dans la pile\n");
		    }
		}
	      
	      
		else if(t.equals("clear")){
		    text.clear();
		}

		else if(t.equals("reset")){
		    text.clear();
		    stock.clear();
		}
	      
		else{
		  
		    Expression test=new VarExpr(t);
		    stock.push(test);
		    historic.push(test);
		}
	  
		String sp =" ";
		if(stock.peek()!=null)
		    sp=stock.peek()+"";
		text.appendText(">"+sp+"\n");
		textf.clear();
		text.appendText("<");
	         
	    });
	Scene scene= new Scene(root, 1000, 500);
	stage.setScene(scene);
	stage.setTitle("Calculatrice");
	stage.show();
      
     
	aide.setOnAction(e->{
		Stage help=new Stage();
		BorderPane root1=new BorderPane();
		Scene scene2=new Scene(root1,500,300);
		TextArea texth =new TextArea("-utiliser la forme polonaise inversee pour cette calculatrice\n\n"+
					     "-opp: commande pour obtenir l'oppose de la derniere expression\n\n"+
					     "-inv: commande pour obtenir l'inverse de la derniere expression\n\n"+
					     "-clear: commande pour nettoyer l'ecran\n\n"+
					     "-reset; commande pour reinitialiser la calculatrice\n\n"+
					     "-pour substituer une inconnue, taper le nom de l'inconnue puis sa valeur puis '='\n\n"
					     );
		texth.setEditable(false);
		// text.setPrefWidth(150);
		root1.setCenter(texth);
		help.setScene(scene2);
		help.setTitle("aide");
		help.show();});
     
    }
    
    public static void main(String[] args){
	Application.launch(args);
    }
}
